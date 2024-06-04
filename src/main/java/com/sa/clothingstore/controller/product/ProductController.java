package com.sa.clothingstore.controller.product;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.product.ProductItemRequest;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.response.product.ProductItemResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.service.product.ProductService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(APIConstant.PRODUCTS)
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping(APIConstant.PRODUCT_ID)
    public List<ProductItemResponse> getDetailProduct(@PathVariable UUID productId){
        return productService.getDetailProduct(productId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestParam("images") @Nullable List<MultipartFile> image, @Valid @ModelAttribute ProductRequest productRequest) throws IOException {
        productService.createProduct(image, productRequest);
        return "Product was created successfully";
    }
    @PostMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public String addExistedProduct(@PathVariable UUID productId, @RequestBody List<@Valid ProductItemRequest> productItemRequests) throws IOException {
        productService.addProductExisted(productId, productItemRequests);
        return "Product was created successfully";
    }
    @PutMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable UUID productId, @RequestParam("images") @Nullable List<MultipartFile> image, @ModelAttribute @Valid ProductRequest productRequest) throws IOException {
        productService.updateProduct(productId, image, productRequest);
        return "Product was modified successfully";
    }
    @DeleteMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);
        return "Product was deleted successfully";
    }
}
