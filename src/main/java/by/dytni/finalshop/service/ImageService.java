package by.dytni.finalshop.service;

import by.dytni.finalshop.domain.Image;
import by.dytni.finalshop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public ResponseEntity<byte[]> displayImage(Integer id) throws SQLException
    {
        Image image = imageRepository.findById(id).orElse(null);
        byte [] imageBytes;
        imageBytes = image.getBlob().getBytes(1,(int) image.getBlob().length());
        return ResponseEntity.ok().contentLength(image.getSize())
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(imageBytes);
    }

    public List<Image> displayImages() {
        return imageRepository.findAll();
    }
}
