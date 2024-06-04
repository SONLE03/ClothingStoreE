package com.sa.clothingstore.constant.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class FutureOrTodayValidator implements ConstraintValidator<FutureOfDateFormat, Date> {
    @Override
    public void initialize(FutureOfDateFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }

        try {
            // Chuyển đổi ngày hiện tại thành LocalDate
            LocalDate currentDate = LocalDate.now();

            // Chuyển đổi Date thành chuỗi và sau đó parse thành LocalDate
            LocalDate checkedDate = LocalDate.parse(new SimpleDateFormat("dd/MM/yyyy").format(date), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Kiểm tra xem ngày kiểm tra có là ngày hiện tại hoặc tương lai không
            return !checkedDate.isBefore(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}