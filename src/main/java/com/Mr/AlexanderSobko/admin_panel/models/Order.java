package com.Mr.AlexanderSobko.admin_panel.models;

import com.Mr.AlexanderSobko.admin_panel.enums.OrderStatus;
import com.Mr.AlexanderSobko.admin_panel.enums.PatisserieType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NamedNativeQuery(
        name = "find_order_dto",
        query = "SELECT customers.first_name,\n" +
                "        customers.last_name,\n" +
                "        customers.delivery_method,\n" +
                "        customers.delivery_address,\n" +
                "        orders.id,\n" +
                "        orders.date," +
                "        orders.total_price,\n" +
                "        orders.order_status\n" +
                "FROM orders \n" +
                "JOIN customers\n" +
                "    ON orders.customer_id = customers.id",
        resultSetMapping = "order_dto"
)
@SqlResultSetMapping(
        name = "order_dto",
        classes = @ConstructorResult(
                targetClass = OrderDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "date", type = LocalDateTime.class),
                        @ColumnResult(name = "order_status", type = String.class),
                        @ColumnResult(name = "total_price", type = Integer.class),
                        @ColumnResult(name = "delivery_method", type = String.class),
                        @ColumnResult(name = "delivery_address", type = String.class)
                }
        )
)
@Data
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDateTime date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<Patisserie> patisseries = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private Integer totalPrice;

    @Override
    public String toString() {
        String chocolates = patisseries.stream()
                .filter(p -> p.getPatisserieType().equals(PatisserieType.CHOCOLATE))
                .map(Patisserie::toString)
                .collect(Collectors.joining());
        String cakes = patisseries.stream()
                .filter(p -> p.getPatisserieType().equals(PatisserieType.CAKE))
                .map(Patisserie::toString)
                .collect(Collectors.joining());
        String chocolateList = chocolates.isEmpty() ? "" : "Шоколад:\n" + chocolates;
        String cakeList = cakes.isEmpty() ? "" : "Торты:\n" + cakes;
        return chocolateList + cakeList;
    }

}
