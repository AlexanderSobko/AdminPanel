package com.Mr.AlexanderSobko.admin_panel.repositories;

import com.Mr.AlexanderSobko.admin_panel.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query( name = "find_order_dto", nativeQuery = true)
//    List<OrderDTO> findAllOrderDTO();
}
