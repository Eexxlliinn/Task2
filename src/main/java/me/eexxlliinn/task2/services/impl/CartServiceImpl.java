package me.eexxlliinn.task2.services.impl;

import me.eexxlliinn.task2.dtos.CartRequest;
import me.eexxlliinn.task2.dtos.CartResponse;
import me.eexxlliinn.task2.entities.CartEntity;
import me.eexxlliinn.task2.mappers.CartMapper;
import me.eexxlliinn.task2.repositories.CartRepository;
import me.eexxlliinn.task2.repositories.impl.CartRepositoryImpl;
import me.eexxlliinn.task2.services.CartService;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository = new CartRepositoryImpl();
    private final CartMapper cartMapper = new CartMapper();

    @Override
    public CartResponse createCart(CartRequest cartRequest) {
        CartEntity cartEntity = cartMapper.toEntity(cartRequest);
        return cartMapper.toDto(cartRepository.createCart(cartEntity));
    }

    @Override
    public CartResponse updateCart(long cartId, CartRequest cartRequest) {
        CartEntity cartEntity = cartRepository.getCartById(cartId);
        cartEntity.setUserId(cartRequest.getUserId());
        cartEntity.setCurrency(cartRequest.getCurrency());
        return cartMapper.toDto(cartRepository.updateCart(cartEntity));
    }

    @Override
    public CartResponse getCartById(long cartId) {
        return cartMapper.toDto(cartRepository.getCartById(cartId));
    }

    @Override
    public CartResponse getActiveCart(long userId) {
        return cartMapper.toDto(cartRepository.getActiveCart(userId));
    }

    @Override
    public void addItemToCart(long cartId, long productId, int amount) {
        cartRepository.addItemToCart(cartId, productId, amount);
    }

    @Override
    public void removeItemFromCart(long cartId, long productId, int amount) {
        cartRepository.removeItemFromCart(cartId, productId, amount);
    }

    @Override
    public void clearCart(long cartId) {
        cartRepository.clearCart(cartId);
    }
}
