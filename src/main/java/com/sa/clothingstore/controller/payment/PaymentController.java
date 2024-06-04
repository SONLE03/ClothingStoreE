package com.sa.clothingstore.controller.payment;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.payment.PaymentRequest;
import com.sa.clothingstore.service.payment.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(APIConstant.PAYMENTS)
@RestController
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPaymentMethod(@RequestBody @Valid PaymentRequest paymentRequest) throws IOException {
        paymentService.createPaymentMethod(paymentRequest);
        return "Payment method was created successfully";
    }
    @PutMapping(APIConstant.PAYMENT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updatePaymentMethod(@PathVariable Integer paymentId, @RequestBody @Valid PaymentRequest paymentRequest) throws IOException {
        paymentService.updatePaymentMethod(paymentId, paymentRequest);
        return "Payment method was modified successfully";
    }
    @DeleteMapping(APIConstant.PAYMENT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deletePaymentMethod(@PathVariable Integer paymentId){
        paymentService.deletePaymentMethod(paymentId);
        return "Payment method was deleted successfully";
    }
}
