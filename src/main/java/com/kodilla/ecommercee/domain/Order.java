package com.kodilla.ecommercee.domain;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Order name cannot be blank")
    @Size(min = 3, max = 255, message = "Order name must be between 3 and 255 characters")
    @Column(name = "order_name")
    private String orderName;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 5, max = 1000, message = "Product description must be between 5 and 1000 characters")
    @Column(name = "product_description")
    private String productDescription;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}