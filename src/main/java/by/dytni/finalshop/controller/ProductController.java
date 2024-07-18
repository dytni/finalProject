    package by.dytni.finalshop.controller;


    import by.dytni.finalshop.domain.Image;
    import by.dytni.finalshop.domain.Product;
    import by.dytni.finalshop.service.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.access.prepost.PreAuthorize;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.servlet.ModelAndView;

    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;

    @Controller
    @RequiredArgsConstructor
//    @RestController
    public class ProductController {
        private final ProductService productService;

        @GetMapping("/products")
        public String products(@RequestParam(name = "name", required = false)String name, Model model) {
            model.addAttribute("products", productService.getProducts(name));
            return "products";
        }


        @GetMapping("/products/add")
        public String addProduct(Model model) {
            model.addAttribute("products", productService.getProducts(null));
            return "addproduct";
        }
        @PostMapping("/products/create")
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public String saveProduct(@ModelAttribute Product product,
                                  @RequestParam("file1")MultipartFile file1,
                                  @RequestParam("file2")MultipartFile file2,
                                  @RequestParam("file3")MultipartFile file3 ) throws IOException, SQLException {
            productService.saveProduct(product, file1, file2, file3);
            return "redirect:/products/add";
        }

        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        @PostMapping("/products/delete/{id}")
        public String deleteProduct(@PathVariable Integer id) {
            productService.deleteProductById(id);
            return "redirect:/products/add";
        }
        @GetMapping("/products/info/{id}")
        public String viewProduct(@PathVariable Integer id, Model model) {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            List<Image> imageList = product.getImageList();
            model.addAttribute("image1id", imageList.get(0).getId());
            model.addAttribute("image2id", imageList.get(1).getId());
            model.addAttribute("image3id", imageList.get(2).getId());
            return "productInfo";
        }
    }
