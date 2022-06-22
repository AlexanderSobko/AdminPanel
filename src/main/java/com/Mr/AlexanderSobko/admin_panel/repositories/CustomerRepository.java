package com.Mr.AlexanderSobko.admin_panel.repositories;

import com.Mr.AlexanderSobko.admin_panel.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
