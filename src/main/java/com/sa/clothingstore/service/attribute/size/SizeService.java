package com.sa.clothingstore.service.attribute.size;

import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import com.sa.clothingstore.dto.response.attribute.ColorResponse;
import com.sa.clothingstore.dto.response.attribute.SizeResponse;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAllSize();
    SizeResponse createSize(SizeRequest sizeRequest);
    Size modifySize(Integer id, SizeRequest sizeRequest);
    void deleteSize(Integer id);
}
