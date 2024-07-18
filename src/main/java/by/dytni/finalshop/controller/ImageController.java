package by.dytni.finalshop.controller;

import by.dytni.finalshop.domain.Image;
import by.dytni.finalshop.repository.ImageRepository;
import by.dytni.finalshop.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;


@RestController
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable Integer id) throws SQLException
    {
        return imageService.displayImage(id);
    }
    @GetMapping("/images")
    public ModelAndView displayImages()
    {
        ModelAndView modelAndView = new ModelAndView("allImages");
        modelAndView.addObject("imageList", imageService.displayImages());
        return modelAndView;
    }
}
