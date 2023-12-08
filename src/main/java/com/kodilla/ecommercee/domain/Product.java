package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Column(name = "product_name")
    private String productName;

    @NotNull(message = "Product price cannot be null")
    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @ManyToMany
    @JoinColumn(name = "order_id")
    private List<Order> orders;

    @ManyToOne
    private Group group;

}
