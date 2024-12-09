package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private ColorEnum color;
    private SizeEnum size;
    private double price;
}
