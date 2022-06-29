package com.Mr.AlexanderSobko.admin_panel.controlers;

import com.Mr.AlexanderSobko.admin_panel.exceptions.ResourceNotFoundException;
import com.Mr.AlexanderSobko.admin_panel.models.Customer;
import com.Mr.AlexanderSobko.admin_panel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://178.154.195.111:3000")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id) {
        Customer customer = customerService.getCustomer(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@RequestBody Customer newData, @PathVariable(value = "id") Long id){
        System.out.println(newData);
//        ------------------------------------------------------------------------------------------------------
        return newData;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return customerService.save(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeCustomer(@PathVariable(value = "id") Long id) {
        Customer customer = customerService.getCustomer(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found " + id)
        );

        customerService.deleteCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
