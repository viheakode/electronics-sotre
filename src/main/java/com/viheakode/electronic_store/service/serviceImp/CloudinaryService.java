package com.viheakode.electronic_store.service.serviceImp;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map<String, String> upload(MultipartFile file) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Image file is required");
        }

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                Map.of("folder", "products")
        );

        return Map.of(
                "url", result.get("secure_url").toString(),
                "publicId", result.get("public_id").toString()
        );
    }

    public void delete(String publicId) throws Exception {
        cloudinary.uploader().destroy(
                publicId,
                Map.of("resource_type", "image")
        );
    }
}
