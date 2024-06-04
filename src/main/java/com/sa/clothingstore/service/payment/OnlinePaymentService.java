package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.model.payment.PaymentMethod;
import com.sa.clothingstore.service.user.service.UserService;
import com.sa.clothingstore.util.vnpay.VNPayService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@AllArgsConstructor
@Service
public class OnlinePaymentService {

    public void paymentMethod(Integer id, int orderTotal, String orderInfo, HttpServletRequest request){

    }
}
