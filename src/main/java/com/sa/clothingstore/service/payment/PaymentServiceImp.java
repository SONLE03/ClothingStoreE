package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.payment.PaymentRequest;
import com.sa.clothingstore.dto.response.payment.PaymentResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.payment.PaymentMethod;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.util.fileUpload.FileUploadImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService{
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final UserDetailService userDetailService;
    private final FileUploadImp fileUploadImp;
    @Override
    public List<PaymentResponse> getAllPaymentMethod() {
        return null;
    }

    @Override
    public PaymentResponse getPaymentMethodById() {
        return null;
    }

    @Override
    @Transactional
    public void createPaymentMethod(PaymentRequest paymentRequest) throws IOException {
//        PaymentMethod paymentMethod = new PaymentMethod();
//        paymentMethod.setName(paymentMethod.getName());
//        var paymentImage = paymentRequest.getImage();
//        if(paymentImage != null){
//            BufferedImage bi = ImageIO.read(paymentImage.getInputStream());
//            if (bi == null) {
//                throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
//            }
//            Map result = fileUploadImp.upload(paymentImage, "payments");
//            Image image =  Image.builder()
//                    .name((String) result.get("original_filename"))
//                    .url((String) result.get("url"))
//                    .cloudinaryId((String) result.get("public_id"))
//                    .build();
//            imageRepository.save(image);
//            paymentMethod.setImage(image);
//        }
//        paymentMethod.setCommonCreate(userDetailService.getIdLogin());
//        paymentRepository.save(paymentMethod);
    }

    @Override
    public void updatePaymentMethod(Integer paymentId, PaymentRequest paymentRequest) throws IOException {
//        PaymentMethod paymentMethod = paymentRepository.findById(paymentId).orElseThrow(
//                () -> new BusinessException(APIStatus.PAYMENT_NOT_FOUND));
//        paymentMethod.setName(paymentMethod.getName());
//        var paymentImage = paymentRequest.getImage();
//        if(paymentImage != null){
//            fileUploadImp.delete(paymentMethod.getImage().getCloudinaryId());
//            BufferedImage bi = ImageIO.read(paymentImage.getInputStream());
//            if (bi == null) {
//                throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
//            }
//            Map result = fileUploadImp.upload(paymentImage, "payments");
//            Image image =  Image.builder()
//                    .name((String) result.get("original_filename"))
//                    .url((String) result.get("url"))
//                    .cloudinaryId((String) result.get("public_id"))
//                    .build();
//            imageRepository.save(image);
//            paymentMethod.setImage(image);
//        }
//        paymentMethod.setCommonUpdate(userDetailService.getIdLogin());
//        paymentRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Integer paymentId) {
//        if(!paymentRepository.existsById(paymentId)){
//            throw new BusinessException(APIStatus.PAYMENT_NOT_FOUND);
//        }
//        paymentRepository.deleteById(paymentId);
    }
}
