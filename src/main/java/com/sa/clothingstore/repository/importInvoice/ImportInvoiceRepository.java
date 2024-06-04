package com.sa.clothingstore.repository.importInvoice;

import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;
import java.util.List;
@Repository
public interface ImportInvoiceRepository extends JpaRepository<ImportInvoice, UUID> {
    @Query("SELECT NEW com.sa.clothingstore.dto.response.importProduct.ImportResponse(i.id, i.total) " +
            " FROM ImportInvoice i " +
            "WHERE i.createdAt BETWEEN :startDate AND :endDate "
    )
    List<ImportResponse> filterImportByDate(Date startDate, Date endDate);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.importProduct.ImportResponse(i.id, i.total) " +
            " FROM ImportInvoice i " +
            "WHERE i.id = :importId"
    )
    ImportResponse getImportById(UUID importId);
}
