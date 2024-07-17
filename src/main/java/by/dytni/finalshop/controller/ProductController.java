    package by.dytni.finalshop.controller;


    import by.dytni.finalshop.domain.Image;
    import by.dytni.finalshop.domain.Product;
    import by.dytni.finalshop.service.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.access.prepost.PreAuthorize;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

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

        /*@GetMapping("/products/type/{type}")
        public String productsByType(@PathVariable String type, Model model) {
            model.addAttribute("products", productService.getAllProductsOfType(type));
            model.addAttribute("newProduct", new Product());
            return "products";
        }*/

        @GetMapping("/products/add")
        public String addProduct(Model model) {
            model.addAttribute("products", productService.getProducts(null));
            return "addproduct";
        }
        @PostMapping("/products/create")
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public String saveProduct(@ModelAttribute Product product) {
            productService.saveProduct(product);
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
            model.addAttribute("image1", imageList.get(0));
            model.addAttribute("image2", imageList.get(1));
            model.addAttribute("image3", imageList.get(2));
            return "productInfo";
        }
    }
