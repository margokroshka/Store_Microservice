package com.university.order.repository;

import com.university.order.entity.Order;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Column(name="customer_id")
    List<Order> findByCustomerId (Integer customerId);
}
