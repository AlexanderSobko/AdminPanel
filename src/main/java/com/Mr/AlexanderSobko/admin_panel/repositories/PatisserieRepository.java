package com.Mr.AlexanderSobko.admin_panel.repositories;

import com.Mr.AlexanderSobko.admin_panel.models.entities.Patisserie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatisserieRepository extends JpaRepository<Patisserie, Long> {
}
