package me.eexxlliinn.task2.dtos;

import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

import java.util.UUID;

@Getter
@Setter
public class ProductResponse {
    private UUID id;
    private String name;
    private ColorEnum color;
    private SizeEnum size;
    private double price;
}
