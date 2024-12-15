package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.CurrencyEnum;

@Getter
@Setter
public class CartRequest {
    private long userId;
    private CurrencyEnum currency;
}
