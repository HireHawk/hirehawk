package com.hirehawk.payment_service.paymentservice.dao;

import com.hirehawk.payment_service.paymentservice.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends
        JpaRepository<Payment, Long> {
    Payment findByUserId(Long userId);
}
