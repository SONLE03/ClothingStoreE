package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.service.attribute.image.ImageService;
import com.sa.clothingstore.util.fileUpload.FileUploadImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.IMAGES)
public class ImageController {
    private final ImageService imageService;
    private final FileUploadImp fileUpload;
    @GetMapping
    public List<Image> getAll() {
        return imageService.getAllImage();
    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public String createImage(@RequestBody @Valid ImageRequest imageRequest) {
//        imageService.createImage(imageRequest);
//        return "Image was created successfully";
//    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createImage(@RequestParam("image") MultipartFile multipartFile) throws IOException{
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity("imagen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = fileUpload.upload(multipartFile, "image");
        Image image = new Image();
        image.setName((String) result.get("original_filename"));
        image.setUrl((String) result.get("url"));
        image.setCloudinaryId((String) result.get("public_id"));
        System.out.println(image.getCloudinaryId());

        imageService.addImage(image);
        return new ResponseEntity(image, HttpStatus.OK);
    }
    @PutMapping(APIConstant.IMAGE_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyImage(@PathVariable UUID id, @RequestBody @Valid ImageRequest imageRequest) {
        imageService.modifyImage(id, imageRequest);
        return "Size was created successfully";
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteImage(@PathVariable UUID id) {
        imageService.deleteImage(id);
        return "Size was created successfully";
    }
}
