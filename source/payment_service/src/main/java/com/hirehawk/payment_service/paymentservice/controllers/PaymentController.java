package com.hirehawk.payment_service.paymentservice.controllers;

import com.hirehawk.payment_service.paymentservice.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentRepository repository;

    @PostMapping("/addWallet")
    public String addWallet() {
        // Implementation
        return "";
    }

    @PostMapping("/sendMoney")
    public String sendMoney() {
        // Implementation
        return "";
    }
}
