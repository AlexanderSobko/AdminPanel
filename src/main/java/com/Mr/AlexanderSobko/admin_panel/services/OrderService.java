package com.Mr.AlexanderSobko.admin_panel.services;

import com.Mr.AlexanderSobko.admin_panel.exceptions.ResourceNotFoundException;
import com.Mr.AlexanderSobko.admin_panel.models.entities.Order;
import com.Mr.AlexanderSobko.admin_panel.models.dtos.OrderDTO;
import com.Mr.AlexanderSobko.admin_panel.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream()
                .map(order -> orderToOrderDTO(order))
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderDTO(Long id) {
        return orderToOrderDTO(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found " + id)));
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrder(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found " + id)
        );
        orderRepository.delete(order);
    }

    private OrderDTO orderToOrderDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer().getFirstName(),
                order.getCustomer().getLastName(),
                order.getDate(),
                order.toString(),
                order.getPatisseries(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                order.getCustomer().getDeliveryMethod(),
                order.getCustomer().getDeliveryAddress());
    }
}
