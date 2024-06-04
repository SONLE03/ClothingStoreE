package com.sa.clothingstore.dto.request.product;

import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class SizeColorPair {
    private SizeRequest sizeRequest;
    private ColorRequest colorRequest;

    public SizeColorPair(SizeRequest size, ColorRequest color) {
        this.sizeRequest = size;
        this.colorRequest = color;
    }

    // Equals and hashCode methods to ensure uniqueness of pairs
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeColorPair that = (SizeColorPair) o;
        return sizeRequest.equals(that.sizeRequest) && colorRequest.equals(that.colorRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeRequest, colorRequest);
    }
}
