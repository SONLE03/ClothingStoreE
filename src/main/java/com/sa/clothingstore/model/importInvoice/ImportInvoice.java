package com.sa.clothingstore.model.importInvoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.clothingstore.model.CommonModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "import_invoice")
public class ImportInvoice extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "total")
    private BigDecimal total;

    @JsonIgnore
    @OneToMany(mappedBy = "importInvoice")
    List<ImportItem> importItems = new ArrayList<>();;
}
