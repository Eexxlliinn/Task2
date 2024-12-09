package me.eexxlliinn.task2.entities;

import jakarta.persistence.*;
import lombok.*;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "products")
@Entity
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
}
