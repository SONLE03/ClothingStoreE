package com.sa.clothingstore.service.attribute.color;

import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.response.attribute.ColorResponse;
import com.sa.clothingstore.dto.response.category.BranchResponse;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.category.Branch;

import java.util.List;
import java.util.UUID;

public interface ColorService {
    List<Color> getAllColor();
    ColorResponse createColor(ColorRequest colorRequest);
    Color modifyColor(Integer id, ColorRequest colorRequest);
    void deleteColor(Integer id);
}
