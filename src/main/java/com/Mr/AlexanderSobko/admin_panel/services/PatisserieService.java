package com.Mr.AlexanderSobko.admin_panel.services;

import com.Mr.AlexanderSobko.admin_panel.models.entities.Patisserie;
import com.Mr.AlexanderSobko.admin_panel.repositories.PatisserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatisserieService {

    private final OrderService orderService;
    private final PatisserieRepository patisserieRepo;

    public List<Patisserie> getPatisseries() {
        return patisserieRepo.findAll();
    }


    @Autowired
    public PatisserieService(OrderService orderService, PatisserieRepository patisserieRepo) {
        this.orderService = orderService;
        this.patisserieRepo = patisserieRepo;
    }
}
