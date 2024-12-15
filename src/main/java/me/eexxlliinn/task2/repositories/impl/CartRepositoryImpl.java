package me.eexxlliinn.task2.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.entities.CartEntity;
import me.eexxlliinn.task2.entities.CartProductEntity;
import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.enums.CurrencyEnum;
import me.eexxlliinn.task2.repositories.CartRepository;
import me.eexxlliinn.task2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class CartRepositoryImpl implements CartRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public CartEntity getCartById(long cartId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CartEntity.class, cartId);
        } catch (Exception e) {
            log.error("Error while getting cart by id {}", cartId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartEntity getActiveCart(long userId) {
        try (Session session = sessionFactory.openSession()) {
            CartEntity cart = session.createQuery("FROM CartEntity WHERE userId =: userId", CartEntity.class)
                    .setParameter("userId", userId)
                    .uniqueResult();
            if (cart == null) {
                cart = new CartEntity();
                cart.setUserId(userId);
                cart.setCurrency(CurrencyEnum.USD);
                session.beginTransaction();
                session.save(cart);
                session.getTransaction().commit();
            }
            return cart;
        }
    }

    @Override
    public CartEntity createCart(CartEntity cart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
            return cart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while creating cart ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartEntity updateCart(CartEntity cart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cart);
            transaction.commit();
            return cart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while updating cart ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCart(long cartId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CartEntity cart = session.get(CartEntity.class, cartId);
            if (cart != null) {
                session.delete(cart);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while deleting cart ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItemToCart(long cartId, long productId, int amount) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CartEntity cart = session.get(CartEntity.class, cartId);
            ProductEntity product = session.get(ProductEntity.class, productId);
            if (cart != null && product != null) {
                CartProductEntity existingCartProduct = cart.getProducts()
                        .stream()
                        .filter(cp -> cp.getProduct().getId() == productId)
                        .findFirst()
                        .orElse(null);
                if (existingCartProduct != null) {
                    existingCartProduct.setAmount(existingCartProduct.getAmount() + amount);
                } else {
                    CartProductEntity newCartProduct = new CartProductEntity();
                    newCartProduct.setProduct(product);
                    newCartProduct.setAmount(amount);
                    cart.addProduct(newCartProduct);
                }
                product.setAmount(product.getAmount() - amount);
                session.update(cart);
                session.update(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while adding item to cart ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItemFromCart(long cartId, long productId, int amount) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CartEntity cart = session.get(CartEntity.class, cartId);
            ProductEntity product = session.get(ProductEntity.class, productId);
            if (cart != null) {
                CartProductEntity existingCartProduct = cart.getProducts()
                        .stream()
                        .filter(cp -> cp.getProduct().getId() == productId)
                        .findFirst()
                        .orElse(null);
                if (existingCartProduct != null) {
                    if (existingCartProduct.getAmount() > amount) {
                        existingCartProduct.setAmount(existingCartProduct.getAmount() - amount);
                    } else {
                        cart.removeProduct(existingCartProduct);
                        session.delete(existingCartProduct);
                    }
                    product.setAmount(product.getAmount() + amount);
                    session.update(cart);
                    session.update(product);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while removing item from cart ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartEntity clearCart(long cartId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CartEntity cart = session.get(CartEntity.class, cartId);
            if (cart != null) {
                for (CartProductEntity cartProduct : cart.getProducts()) {
                    ProductEntity product = cartProduct.getProduct();
                    product.setAmount(product.getAmount() + cartProduct.getAmount());
                    session.update(product);
                }
                cart.getProducts().clear();
                session.update(cart);
            }
            transaction.commit();
            return cart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error while clearing cart ", e);
            throw new RuntimeException(e);
        }
    }
}
