package com.kodilla.ecommercee.domain;
import lombok.*;
import javax.persistence.*;
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

    @Column(name = "order_name")
    private String orderName;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
