package com.hirehawk.payment_service.paymentservice.controllers;

import com.hirehawk.payment_service.paymentservice.dao.Payment;
import com.hirehawk.payment_service.paymentservice.dao.PaymentRepository;
import com.hirehawk.payment_service.paymentservice.dao.Wallet;
import com.hirehawk.payment_service.paymentservice.dao.WalletRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;

@RestController
public class WalletController {

    private final WalletRepository repository;

    private final PaymentRepository paymentRepository;

    WalletController(WalletRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/addWallet")
    public ResponseEntity<String> addWallet(@RequestParam("user_id") Long userId) {
        if (repository.existsByUserId(userId)) {
            return new ResponseEntity<>(
                    String.format("Wallet for userId [%d] already exists", userId),
                    HttpStatus.CONFLICT);
        } else {
            Wallet newWallet = new Wallet();
            newWallet.setUserId(userId);
            repository.save(newWallet);
            return ResponseEntity.ok(String.format("Wallet was for userId [%d] was added!", userId));
        }
    }

    @PostMapping("/addMoney")
    public ResponseEntity<String> addMoney(@RequestParam("user_id") Long userId, @RequestParam("amount") BigDecimal amount) {
        if (repository.existsByUserId(userId)) {
            Wallet wallet = repository.findByUserId(userId);
            BigDecimal newAmount = wallet.getMoneyAmount().add(amount);
            wallet.setMoneyAmount(newAmount);
            repository.save(wallet);
            return ResponseEntity.ok(String.format("Money amount for userId [%d] was updated. New amount [%s]", userId,
                    newAmount.toString()));
        } else {
            return new ResponseEntity<>(
                    getMessageWalletNotFound(userId),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getMoney")
    public ResponseEntity<String> getMoney(@RequestParam("user_id") Long userId) throws JSONException {
        if (repository.existsByUserId(userId)) {
            Wallet wallet = repository.findByUserId(userId);
            BigDecimal amount = wallet.getMoneyAmount();
            JSONObject obj = new JSONObject();
            obj.put("amount", amount);
            String res = obj.toString();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    getMessageWalletNotFound(userId),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sendMoney")
    public ResponseEntity<String> sendMoney(@RequestParam("user_id_from") Long userIdFrom,
                                            @RequestParam("user_id_to") Long userIdTo,
                                            @RequestParam("amount") BigDecimal amount,
                                            @RequestParam("order_id") Long orderId) {
        ResponseEntity resp = checkExistsByUserId(userIdFrom);
        if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
            return resp;
        }
        resp = checkExistsByUserId(userIdTo);
        if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
            return resp;
        }

        if(paymentRepository.existsByOrderId(orderId)){
            return new ResponseEntity<>(String.format("The payment from user [%d] to user [%d] for order [%d] in amount [%s]" +
            " has already been made", userIdFrom,userIdTo,orderId,amount.toString()),HttpStatus.ACCEPTED);
        }

        resp = checkUserHasEnoughMoney(userIdFrom, amount);
        if (resp.getStatusCode() == HttpStatus.EXPECTATION_FAILED) {
            return resp;
        }

        addMoneyHelper(userIdFrom, amount.negate());
        addMoneyHelper(userIdTo, amount);

        java.util.Date date = new java.util.Date(Calendar.getInstance().getTime().getTime());
        Payment newPayment = new Payment(userIdFrom, userIdTo, amount, date, orderId);
        paymentRepository.save(newPayment);

        return ResponseEntity.ok(String.format("Payment was made from [%d] to [%d]: payment amount [%s]",
                userIdFrom, userIdTo, amount.toString()));
    }

    @PostMapping("/isPaidOrder")
    public ResponseEntity<String> sendMoney(@RequestParam("order_id") Long orderId) {
        if(paymentRepository.existsByOrderId(orderId)){
            return new ResponseEntity<>(String.format("The payment with id [%d] was paid for",orderId),HttpStatus.OK);
        }

        return new ResponseEntity<>(String.format("The payment with id [%d] was not paid for",orderId),HttpStatus.NOT_FOUND);
    }

    private String getMessageWalletNotFound(Long userId) {
        return String.format("Wallet for userId [%d] not found", userId);
    }

    private ResponseEntity<String> checkExistsByUserId(Long userId) {
        if (!repository.existsByUserId(userId)) {
            return new ResponseEntity<>(
                    getMessageWalletNotFound(userId),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(
                    "",
                    HttpStatus.FOUND);
        }
    }

    private ResponseEntity<String> checkUserHasEnoughMoney(Long userId, BigDecimal neededMoney) {
        Wallet wallet = repository.findByUserId(userId);
        int comparisonRes = wallet.getMoneyAmount().compareTo(neededMoney);
        if (comparisonRes >= 0) {
            return new ResponseEntity<>(
                    "",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    String.format("User with userId [%d] doesnt have enough money: requested money [%s]", userId, neededMoney.toString()),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    private void addMoneyHelper(Long userId, BigDecimal amount) {
        Wallet wallet = repository.findByUserId(userId);
        BigDecimal newAmount = wallet.getMoneyAmount().add(amount);
        wallet.setMoneyAmount(newAmount);
        repository.save(wallet);
    }
}
