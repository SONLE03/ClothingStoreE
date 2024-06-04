package com.sa.clothingstore.util.fileUpload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImp{
    private final Cloudinary cloudinary;
    private Map<String, String> valuesMap = new HashMap<>();

//    public String uploadFile(MultipartFile multipartFile) throws IOException {
//        return cloudinary.uploader()
//                .upload(multipartFile.getBytes(),
//                        Map.of("public_id", UUID.randomUUID().toString()))
//                .get("url")
//                .toString();
//    }
    public Map upload(MultipartFile multipartFile, String folder) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", folder));
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
