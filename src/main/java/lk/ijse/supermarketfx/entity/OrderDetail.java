package lk.ijse.supermarketfx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:28 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/
@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    private String orderId;

    @ManyToOne
    @JoinColumn(name = "item_code")
    private Item item;
//    private String itemId;

    private int quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal price;


}
