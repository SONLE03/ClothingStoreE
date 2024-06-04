package com.sa.clothingstore.controller.category;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.category.CategoryRequest;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.service.category.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.CATEGORIES)
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategory();
    }
    @GetMapping(APIConstant.CATEGORY_ID)
    public List<ProductResponse> getAllProductByCategory(@PathVariable UUID categoryId){
        return categoryService.getAllProductByCategoryId(categoryId);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProductGender(@RequestBody @Valid CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return "Category was created successfully";
    }
    @PutMapping(APIConstant.CATEGORY_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyProductGender(@PathVariable UUID categoryId, @RequestBody @Valid CategoryRequest categoryRequest){
        categoryService.modifyCategory(categoryId, categoryRequest);
        return "Category was modified successfully";
    }
    @DeleteMapping(APIConstant.CATEGORY_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductGender(@PathVariable UUID categoryId){
        categoryService.deleteCategory(categoryId);
        return "Category was delete successfully";
    }
}


