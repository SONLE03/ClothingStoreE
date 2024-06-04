package com.sa.clothingstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonModel {
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default NOW()")
    @JsonFormat(timezone = "GMT+7")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP default NOW()")
    @JsonFormat(timezone = "GMT+7")
    private Timestamp updatedAt;

    @Column(name = "created_by", columnDefinition = "binary(16) DEFAULT 0")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "binary(16) DEFAULT 0")
    private UUID updatedBy;

    public void setCommonCreate(UUID currentLoginId) {
        this.createdAt = resultTimestamp();
        this.createdBy = currentLoginId;
        this.updatedAt = resultTimestamp();
        this.updatedBy = currentLoginId;
    }

    public void setCommonUpdate(UUID currentLoginId) {
        this.updatedAt = resultTimestamp();
        this.updatedBy = currentLoginId;
    }

    public static Timestamp resultTimestamp() {
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }
}
