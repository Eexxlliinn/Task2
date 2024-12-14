package me.eexxlliinn.task2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private ColorEnum color;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private long amount;
}
