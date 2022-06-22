package com.Mr.AlexanderSobko.admin_panel.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telegramId;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String userName;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] photo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    private String deliveryMethod = "\"Самовывоз\"";

    private String deliveryAddress = "Адресс: г. Краснодар, ул. 40 лет Победы 33/6";

    @Override
    public String toString() {
        return """
                Customer
                id = %s
                Name = %s %s
                UserName = @%s
                DeliveryMethod = %s
                deliveryAddress = %s
                """.formatted(id,firstName,lastName, userName, deliveryMethod,deliveryAddress);
    }
}
