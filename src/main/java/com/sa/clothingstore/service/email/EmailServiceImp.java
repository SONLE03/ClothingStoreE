package com.sa.clothingstore.service.email;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.email.EmailRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderItem;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.importInvoice.ImportItemRepository;
import com.sa.clothingstore.repository.order.OrderItemRepository;
import com.sa.clothingstore.repository.user.ForgotPasswordRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {
    @Autowired
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final OrderItemRepository orderItemRepository;
    private final ImportItemRepository importItemRepository;
    @Override
    public void sendSimpleMailMessage(EmailRequest request) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(request.to());
        simpleMailMessage.setSubject(request.subject());
        simpleMailMessage.setText(request.text());
        javaMailSender.send(simpleMailMessage);
    }
    @Transactional
    @Override
    public String verifyEmail(String email){
        User user =  userRepository.findByEmail(email)
                                    .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
        int otp = generateOtp();
        EmailRequest emailRequest = EmailRequest.builder()
                                    .to(email)
                                    .text("This is the OTP for your Forgot Password request: " + otp)
                                    .subject("OTP for Forgot Password request")
                                    .build();
        ForgotPassword fp = ForgotPassword.builder()
                            .otp(otp)
                            .expiryDate(new Date(System.currentTimeMillis() + 70 * 1000))
                            .user(user)
                            .build();
        sendSimpleMailMessage(emailRequest);
        forgotPasswordRepository.save(fp);
        return "Email sent for verification!";
    }
    @Override
    public Integer generateOtp(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    @Override
    public void sendOrder(Order order) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            User user = userRepository.findById(order.getCreatedBy()).orElseThrow(
                    () -> new BusinessException(APIStatus.USER_NOT_FOUND));
            List<OrderItem> orderItem = orderItemRepository.getOrderItemByOrder(order);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(order.getCustomer().getEmail());
            helper.addTo(user.getEmail());
            helper.setSubject("Hóa đơn bán hàng: " + order.getId());
            Context context = new Context();
            context.setVariable("order", order);
            context.setVariable("user", user);
            context.setVariable("orderItem", orderItem);
            String htmlContent = templateEngine.process("invoiceTemplate", context);

            helper.setText(htmlContent, true); // Đặt nội dung là HTML

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendImportProduct(ImportInvoice importInvoice) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            User user = userRepository.findById(importInvoice.getUpdatedBy()).orElseThrow(
                    () -> new BusinessException(APIStatus.USER_NOT_FOUND));
            List<ImportItem> importItems = importItemRepository.findByImportInvoice(importInvoice);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setSubject("Hóa đơn nhập hàng: " + importInvoice.getId());
            Context context = new Context();
            context.setVariable("importInvoice", importInvoice);
            context.setVariable("user", user);
            context.setVariable("importItems", importItems);
            String htmlContent = templateEngine.process("importTemplate", context);

            helper.setText(htmlContent, true); // Đặt nội dung là HTML

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
