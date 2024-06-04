package com.sa.clothingstore.service.category.category;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.category.CategoryRequest;
import com.sa.clothingstore.dto.response.category.CategoryResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.model.product.ProductStatus;
import com.sa.clothingstore.repository.category.CategoryRepository;
import com.sa.clothingstore.repository.category.ProductGenderRepository;
import com.sa.clothingstore.repository.product.ProductRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductGenderRepository productGenderRepository;
    private final ModelMapper modelMapper;
    private final UserDetailService userDetailService;
    private final ProductRepository productRepository;

    @Override
    public List<Category> getAllCategory() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return categoryRepository.findAll(sort);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(!productGenderRepository.existsById(categoryRequest.getProductGender())){
            new BusinessException(APIStatus.PRODUCT_GENDER_NOT_FOUND);
        }
        var productGender = productGenderRepository.getById(categoryRequest.getProductGender());
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .productGender(productGender)
                .build();
        category.setCommonCreate(userDetailService.getIdLogin());
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public Category modifyCategory(UUID categoryId, CategoryRequest categoryRequest) {
        if(!categoryRepository.existsById(categoryId)){
            new BusinessException(APIStatus.CATEGORY_NOT_FOUND);
        }
        if(!productGenderRepository.existsById(categoryRequest.getProductGender())){
            new BusinessException(APIStatus.PRODUCT_GENDER_NOT_FOUND);
        }
        var productGender = productGenderRepository.getById(categoryRequest.getProductGender());
        Category category = categoryRepository.getById(categoryId);
        category.setName(categoryRequest.getName());
        category.setProductGender(productGender);
        category.setCommonUpdate(userDetailService.getIdLogin());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            new BusinessException(APIStatus.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<ProductResponse> getAllProductByCategoryId(UUID categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            new BusinessException(APIStatus.CATEGORY_NOT_FOUND);
        }
        List<Object[]> objects = productRepository.getAllProductByCategoryId(categoryId);

        List<ProductResponse> productResponseList = new ArrayList<>();

        for (Object[] objArray : objects) {
            UUID id = (UUID) objArray[0];
            String productName = (String) objArray[1];
            BigDecimal price = (BigDecimal) objArray[2];

            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(id);
            productResponse.setProduct_Name(productName);
            productResponse.setPrice(price);
            productResponse.setCategory((String) objArray[3]);
            productResponse.setBranch((String) objArray[4]);
            productResponse.setDescription((String) objArray[5]);
            productResponse.setProductStatus((ProductStatus) objArray[6]);
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }
}
