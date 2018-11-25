package com.hirehawk.payment_service.paymentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends
        JpaRepository<Wallet, Long> {
    boolean existsByUserId(Long userId);
    Wallet findByUserId(Long userId);
}
