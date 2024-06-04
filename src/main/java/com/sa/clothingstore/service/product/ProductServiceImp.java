package com.sa.clothingstore.service.product;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.product.ProductItemRequest;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.response.product.ProductItemResponse;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.product.ProductStatus;
import com.sa.clothingstore.repository.attribute.ColorRepository;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.attribute.SizeRepository;
import com.sa.clothingstore.repository.category.BranchRepository;
import com.sa.clothingstore.repository.category.CategoryRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.repository.product.ProductRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.util.fileUpload.FileUploadImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ProductItemRepository productItemRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final UserDetailService userDetailService;
    private final FileUploadImp fileUploadImp;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Object[]> objects = productRepository.getAllProduct();

        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Object[] objArray : objects) {
            UUID id = (UUID) objArray[0];
            String productName = (String) objArray[1];
            BigDecimal   price = (BigDecimal) objArray[2];

            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(id);
            productResponse.setProduct_Name(productName);
            productResponse.setPrice(price);
            productResponse.setCategory((String) objArray[3]);
            productResponse.setBranch((String) objArray[4]);
            productResponse.setDescription((String) objArray[5]);
            productResponse.setProductStatus((ProductStatus) objArray[6]);
            productResponse.setImage((String) objArray[7]);
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }
    @Override
    public List<ProductItemResponse> getDetailProduct(UUID productId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ProductItemResponse> list = productItemRepository.getDetailProduct(productId);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "ms");
        return list;
    }
    @Override
    @Transactional
    public void createProduct(List<MultipartFile> multipartFiles, ProductRequest productRequest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Category category = categoryRepository.getCategoryById(productRequest.getCategory());

        if(category == null){ throw new BusinessException(APIStatus.CATEGORY_NOT_FOUND);}

        Branch branch = branchRepository.getBranchById(productRequest.getBranch());
        if(branch == null){
            throw  new BusinessException(APIStatus.BRANCH_NOT_FOUND);
        }
        // Flow: Product -> Product_Image -> Product_Item
        Product product = Product.builder()
                .product_Name(productRequest.getProduct_Name())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(category)
                .branch(branch)
                // Change product status
                .productStatus(ProductStatus.ACTIVE)
                .build();
        productRepository.save(product);

        ExecutorService executorService = Executors.newFixedThreadPool(multipartFiles.size());
        List<Future<Map>> futures = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            Future<Map> future = executorService.submit(() -> {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
                }
                return fileUploadImp.upload(multipartFile, "products");
            });
            futures.add(future);
        }

        for (int i = 0; i < multipartFiles.size(); i++) {
            Future<Map> future = futures.get(i);
            try {
                Map result = future.get();
                imageRepository.save(
                        Image.builder()
                                .name((String) result.get("original_filename"))
                                .url((String) result.get("url"))
                                .product(product)
                                .cloudinaryId((String) result.get("public_id"))
                                .build()
                );
            } catch (Exception e) {
                // Xử lý ngoại lệ khi có lỗi xảy ra trong tiến trình tải lên ảnh
            }
        }

        executorService.shutdown();

        productRequest.getProductItemRequests().forEach(item -> {
            if (!sizeRepository.existsById(item.getSize()) || !colorRepository.existsById(item.getColor())){
                throw new BusinessException(APIStatus.COLOR_SIZE_NOT_FOUND);
            }
            productItemRepository.save(
                    ProductItem.builder()
                            .product(product)
                            .color(colorRepository.getById(item.getColor()))
                            .size(sizeRepository.getById(item.getSize()))
                            .quantity(0)
                            .build()
            );
        });

        product.setCommonCreate(userDetailService.getIdLogin());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "ms");
    }
    @Override
    public void addProductExisted(UUID productId, List<ProductItemRequest> productItemRequests) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BusinessException(APIStatus.PRODUCT_NOT_FOUND));
        productItemRequests.forEach(item -> {
            if(productItemRepository.getProductItemByProductAndAttribute(product, item.getSize(), item.getColor()) == null){
                productItemRepository.save(
                        ProductItem.builder()
                                .product(product)
                                .color(colorRepository.getById(item.getColor()))
                                .size(sizeRepository.getById(item.getSize()))
                                .quantity(0)
                                .build()
                );
            }
        });
        product.setCommonUpdate(userDetailService.getIdLogin());
    }
    @Override
    @Transactional
    public void updateProduct(UUID productId, List<MultipartFile> multipartFiles, ProductRequest productRequest) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Product product = productRepository.getProductById(productId);
        if(product == null){
            throw new BusinessException(APIStatus.PRODUCT_NOT_FOUND);
        }
        Category category = categoryRepository.getCategoryById(productRequest.getCategory());

        if(category == null){ throw new BusinessException(APIStatus.CATEGORY_NOT_FOUND);}

        Branch branch = branchRepository.getBranchById(productRequest.getBranch());
        if(branch == null){
            throw  new BusinessException(APIStatus.BRANCH_NOT_FOUND);
        }

        product.setProduct_Name(productRequest.getProduct_Name());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);
        product.setBranch(branch);
        productRepository.save(product);

        List<String> imageList = imageRepository.getCloudinaryIdByProduct(product);
        for(String cloudId : imageList){
            fileUploadImp.delete(cloudId);
        }
        imageRepository.deleteByProduct(product);

        ExecutorService executorService = Executors.newFixedThreadPool(multipartFiles.size());
        List<Future<Map>> futures = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            Future<Map> future = executorService.submit(() -> {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
                }
                return fileUploadImp.upload(multipartFile, "products");
            });
            futures.add(future);
        }

        for (int i = 0; i < multipartFiles.size(); i++) {
            Future<Map> future = futures.get(i);
            try {
                Map result = future.get();
                imageRepository.save(
                        Image.builder()
                                .name((String) result.get("original_filename"))
                                .url((String) result.get("url"))
                                .product(product)
                                .cloudinaryId((String) result.get("public_id"))
                                .build()
                );
            } catch (Exception e) {
            }
        }
        executorService.shutdown();
        product.setCommonUpdate(userDetailService.getIdLogin());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "ms");
    }



    @Override
    public void deleteProduct(UUID productId) {
        Product product = productRepository.getProductById(productId);
        if(product == null){
            throw new BusinessException(APIStatus.PRODUCT_NOT_FOUND);
        }
        product.setProductStatus(ProductStatus.DELETED);
        product.setCommonUpdate(userDetailService.getIdLogin());
        productRepository.save(product);
    }

}
