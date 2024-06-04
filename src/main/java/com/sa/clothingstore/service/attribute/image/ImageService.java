package com.sa.clothingstore.service.attribute.image;

import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.response.attribute.ImageResponse;
import com.sa.clothingstore.dto.response.category.BranchResponse;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.category.Branch;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    ImageResponse createImage(ImageRequest imageRequest);
    Image modifyImage(UUID id,ImageRequest imageRequest);
    void deleteImage(UUID id);
    Image getById(UUID id);
    List<Image> getAllImage();

    void addImage(Image image);
}
