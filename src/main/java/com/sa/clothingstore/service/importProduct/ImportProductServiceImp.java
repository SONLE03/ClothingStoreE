package com.sa.clothingstore.service.importProduct;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.response.importProduct.ImportDetailResponse;
import com.sa.clothingstore.dto.response.importProduct.ImportItemResponse;
import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.importInvoice.ImportItemKey;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.repository.importInvoice.ImportInvoiceRepository;
import com.sa.clothingstore.repository.importInvoice.ImportItemRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.service.email.EmailService;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportProductServiceImp implements ImportProductService{
    private final UserDetailService userDetailsService;
    private final ProductItemRepository productItemRepository;
    private final ImportInvoiceRepository importInvoiceRepository;
    private final ImportItemRepository importItemRepository;
    private final EmailService emailService;
    @Override
    public List<ImportInvoice> getAllImport() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return importInvoiceRepository.findAll(sort);
    }

    @Override
    public List<ImportItem> getImportById(UUID importId) {
       ImportInvoice importInvoice = importInvoiceRepository.findById(importId)
               .orElseThrow(() -> new BusinessException(APIStatus.IMPORT_NOT_FOUND));
       return importItemRepository.findByImportInvoice(importInvoice);
    }

    @Override
    @Transactional
    public void createImport(List<ImportRequest> importRequests) {
        // Create a new ImportInvoice
        ImportInvoice importInvoice = new ImportInvoice();
        importInvoice.setCommonCreate(userDetailsService.getIdLogin());
        importInvoice = importInvoiceRepository.save(importInvoice);

        // Initialize total to zero
        BigDecimal total = BigDecimal.ZERO;

        List<ImportItem> importItems = new ArrayList<>();

        for (ImportRequest request : importRequests) {
            UUID productItemId = request.getProductItemId();

            // Check if the product exists
            ProductItem productItem = productItemRepository.findById(productItemId)
                    .orElseThrow(() -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));
            // Trigger giá nhập < giá bán
            if (productItem.getProduct().getPrice().compareTo(request.getPrice()) < 0) {
                throw new BusinessException(APIStatus.IMPORT_PRODUCT_PRICE);
            }
            // Calculate total for each import request
            total = total.add(request.getTotal());
            Integer quantity = request.getQuantity();
            // Create ImportItemKey
            ImportItemKey importItemKey = new ImportItemKey();
            importItemKey.setImportId(importInvoice.getId()); // Set importId from the saved ImportInvoice
            importItemKey.setProductItemId(productItemId); // Set productItemId

            // Create ImportItem
            ImportItem importItem = new ImportItem();
            importItem.setId(importItemKey);
            importItem.setImportInvoice(importInvoice);
            importItem.setProductItem(productItem);
            importItem.setQuantity(quantity);
            importItem.setPrice(request.getPrice());
            importItem.setTotal(request.getTotal());

            importItems.add(importItem);

            productItem.setQuantity(quantity + productItem.getQuantity());
            productItemRepository.save(productItem);
        }

        // Set total for ImportInvoice
        importInvoice.setTotal(total);

        // Save ImportItems
        importItemRepository.saveAll(importItems);
        emailService.sendImportProduct(importInvoice);
    }

    @Override
    public void printImport(UUID importId) {

    }

    @Override
    public List<ImportResponse> filterImportByDate(DailyRequest dailyRequest) {
        return importInvoiceRepository.filterImportByDate(dailyRequest.getStartDate(), dailyRequest.getEndDate());
    }

    @Override
    public ImportDetailResponse getImportDetail(UUID importId) {
        ImportInvoice importInvoice = importInvoiceRepository.findById(importId)
                .orElseThrow(() -> new BusinessException(APIStatus.IMPORT_NOT_FOUND));
        ImportResponse importResponse = importInvoiceRepository.getImportById(importId);
        List<ImportItemResponse> importItemResponseList = importItemRepository.getImportItem(importId);
        ImportDetailResponse importDetailResponse = new ImportDetailResponse(importResponse, importItemResponseList);
        return importDetailResponse;
    }

}
