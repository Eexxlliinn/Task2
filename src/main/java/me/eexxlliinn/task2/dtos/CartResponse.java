package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.CurrencyEnum;

import java.util.List;

@Getter
@Setter
public class CartResponse {
    private long cartId;
    private long userId;
    private CurrencyEnum currency;
    private List<CartProductResponse> cartProducts;
}
