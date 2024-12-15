package me.eexxlliinn.task2.repositories;

import me.eexxlliinn.task2.entities.CartEntity;

public interface CartRepository {
    CartEntity getCartById(long cartId);
    CartEntity getActiveCart(long userId);
    CartEntity createCart(CartEntity cart);
    CartEntity updateCart(CartEntity cart);
    void deleteCart(long cartId);
    void addItemToCart(long cartId, long itemId, int amount);
    void removeItemFromCart(long cartId, long itemId, int amount);
    CartEntity clearCart(long cartId);
}
