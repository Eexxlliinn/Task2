package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductResponse {
    private long productId;
    private int amount;
}
