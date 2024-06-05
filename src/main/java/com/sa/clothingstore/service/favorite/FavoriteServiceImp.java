package com.sa.clothingstore.service.favorite;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.favorite.FavoriteRequest;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.favorite.FavoriteItem;
import com.sa.clothingstore.model.favorite.FavoriteItemKey;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.repository.customer.CustomerRepository;
import com.sa.clothingstore.repository.favorite.FavoriteItemRepository;
import com.sa.clothingstore.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImp implements FavoriteService{
    private final ProductRepository productRepository;
    private final FavoriteItemRepository favoriteItemRepository;
    private final CustomerRepository customerRepository;
    @Override
    public List<ProductResponse> getAllFavoriteProducts(UUID customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new BusinessException(APIStatus.CUSTOMER_NOT_FOUND);
        }
        List<Object[]> objects = favoriteItemRepository.getAllFavoriteProducts(customerId);

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

            // Chuyển đổi chuỗi các URL thành danh sách URL
            String urlsString = (String) objArray[6];
            List<String> urls = Arrays.asList(urlsString.split(","));
            productResponse.setImages(urls);

            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    @Override
    public void addProductToFavoriteList(UUID customerId, FavoriteRequest favoriteRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        for(UUID productId : favoriteRequest.getProductIds()) {
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_NOT_FOUND));
            FavoriteItemKey favoriteItemKey = new FavoriteItemKey();
            favoriteItemKey.setProductId(productId);
            favoriteItemKey.setCustomerId(customerId);
            FavoriteItem favoriteItem = FavoriteItem.builder()
                    .id(favoriteItemKey)
                    .customer(customer)
                    .product(product)
                    .build();
            favoriteItemRepository.save(favoriteItem);
        }
    }

    @Override
    public void deleteProductInFavoriteList(UUID customerId, FavoriteRequest favoriteRequest) {
        if(!customerRepository.existsById(customerId)){
            throw new BusinessException(APIStatus.CUSTOMER_NOT_FOUND);
        }
        for(UUID productId : favoriteRequest.getProductIds()){
            if(!productRepository.existsById(productId)){
                throw new BusinessException(APIStatus.PRODUCT_NOT_FOUND);
            }
            favoriteItemRepository.deleteFavoriteProduct(customerId, productId);
        }

    }
}
