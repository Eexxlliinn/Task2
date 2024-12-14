package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

@Getter
@Setter
public class ProductResponse {
    private long id;
    private String name;
    private ColorEnum color;
    private SizeEnum size;
    private double price;
    private long amount;
}
