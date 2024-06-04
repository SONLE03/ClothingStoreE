package com.sa.clothingstore.service.importProduct;

import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.response.importProduct.ImportDetailResponse;
import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;

import java.util.List;
import java.util.UUID;

public interface ImportProductService {
    List<ImportInvoice> getAllImport();
    List<ImportItem> getImportById(UUID importId);
    void createImport(List<ImportRequest> importRequests);
    void printImport(UUID importId);
    List<ImportResponse> filterImportByDate(DailyRequest dailyRequest);

    ImportDetailResponse getImportDetail(UUID importId);
}
