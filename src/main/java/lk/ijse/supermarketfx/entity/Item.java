package lk.ijse.supermarketfx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:27 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @Column(name = "item_code")
    private String id;

    @Column(length = 100)//varchar(100)
    private String name;

    private int quantity;
//    private double price;

    //100.66
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal price;
}
