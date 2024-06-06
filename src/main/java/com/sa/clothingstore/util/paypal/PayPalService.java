package com.sa.clothingstore.util.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final APIContext apiContext;

    public String createPayment(
            Integer total,
            String currency,
            String method,
            String intent,
            String description
    ) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total)); // 9.99$ - 9,99€

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Gửi yêu cầu tạo thanh toán đến PayPal
        Payment createdPayment = payment.create(apiContext);

        // Trích xuất và trả về đường dẫn phê duyệt thanh toán (nếu có)
        for (Links links: createdPayment.getLinks()) {
            if ("approval_url".equals(links.getRel())) {
                return links.getHref();
            }
        }

        // Nếu không có liên kết phê duyệt, trả về null hoặc xử lý khác tùy theo yêu cầu của bạn
        return null;
    }

    public Payment executePayment(
            String paymentId,
            String payerId
    ) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}
