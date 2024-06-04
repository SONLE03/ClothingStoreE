package com.sa.clothingstore.controller.category;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.request.category.ProductGenderRequest;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.service.category.branch.BranchService;
import com.sa.clothingstore.service.category.productGender.ProductGenderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.PRODUCT_GENDERS)
public class ProductGenderController {
    private final ProductGenderService productGenderService;
    @GetMapping
    public List<ProductGender> getAll() {
        return productGenderService.getAllProductGender();
    }
    @GetMapping(APIConstant.SEARCH)
    public List<ProductGender> searchProductGender(@RequestParam("keyword") String keyword){
        return productGenderService.searchProductGender(keyword);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProductGender(@RequestBody @Valid ProductGenderRequest productGenderRequest) {
        productGenderService.createProductGender(productGenderRequest);
        return "Product Gender was created successfully";
    }
    @PutMapping(APIConstant.PRODUCT_GENDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyProductGender(@PathVariable UUID productGenderId, @RequestBody @Valid ProductGenderRequest productGenderRequest){
        productGenderService.modifyProductGender(productGenderId, productGenderRequest);
        return "Product Gender was modified successfully";
    }
    @DeleteMapping(APIConstant.PRODUCT_GENDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductGender(@PathVariable UUID productGenderId){
        productGenderService.deleteProductGender(productGenderId);
        return "Product Gender was delete successfully";
    }
}
