package by.dytni.finalshop.controller;

import by.dytni.finalshop.domain.Image;
import by.dytni.finalshop.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Integer id) {
        Optional<Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }

        Image image = optionalImage.get();

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image.getBytes());
            InputStreamResource inputStreamResource = new InputStreamResource(byteArrayInputStream);

            return ResponseEntity.ok()
                    .header("fileName", image.getOriginalFileName())
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .contentLength(image.getSize())
                    .body(inputStreamResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading image data");
        }
    }
}