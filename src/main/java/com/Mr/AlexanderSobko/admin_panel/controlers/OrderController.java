package com.Mr.AlexanderSobko.admin_panel.controlers;

import com.Mr.AlexanderSobko.admin_panel.models.entities.Order;
import com.Mr.AlexanderSobko.admin_panel.models.dtos.OrderDTO;
import com.Mr.AlexanderSobko.admin_panel.services.CustomerService;
import com.Mr.AlexanderSobko.admin_panel.services.OrderService;
import com.Mr.AlexanderSobko.admin_panel.services.PatisserieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final PatisserieService patisserieService;

    @GetMapping("/recent")
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(orderService.getOrderDTO(id));
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order newOrder, @PathVariable(value = "id") Long id){
        System.out.println(newOrder);
        return newOrder;
//        return customerService.getCustomer(id)
//                .map(customer -> {
//                    customer.setFirstName(newData.getFirstName());
//                    customer.setLastName(newData.getLastName());
//                    customer.setDeliveryMethod(newData.getDeliveryMethod());
//                    customer.setDeliveryAddress(newData.getDeliveryAddress());
//                    customer.setPhoneNumber(newData.getPhoneNumber());
//                    customer.setPhoto(newData.getPhoto());
//                    return customerService.save(customer);
//                })
//                .orElseGet(()->{
//                    newData.setId(id);
//                    return customerService.save(newData);
//                });
    }

    @PostMapping
    public Order saveCustomer(@RequestBody Order order) {
        System.out.println(order);
        return orderService.save(order);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeCustomer(@PathVariable(value = "id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public OrderController(CustomerService customerService, OrderService orderService, PatisserieService patisserieService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.patisserieService = patisserieService;
    }
}
