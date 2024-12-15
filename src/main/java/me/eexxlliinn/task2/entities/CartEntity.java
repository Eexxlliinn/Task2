package me.eexxlliinn.task2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.eexxlliinn.task2.enums.CurrencyEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "cart")
@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProductEntity> products = new ArrayList<>();

    public void addProduct(CartProductEntity cartProduct) {
        this.products.add(cartProduct);
        cartProduct.setCart(this);
    }

    public void removeProduct(CartProductEntity cartProduct) {
        this.products.remove(cartProduct);
        cartProduct.setCart(null);
    }
}
