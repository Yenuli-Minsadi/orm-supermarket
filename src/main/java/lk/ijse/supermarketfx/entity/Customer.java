package lk.ijse.supermarketfx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data // - getter, setter, to_String
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String nic;

    @Column(unique = true)
    private String email;

    @Column(length = 15)//varchar (15)
    private String phone;
}
