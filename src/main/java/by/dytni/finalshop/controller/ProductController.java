    package by.dytni.finalshop.controller;

    import by.dytni.finalshop.domain.product.Product;
    import by.dytni.finalshop.service.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequiredArgsConstructor
    public class ProductController {
        private final ProductService productService;

        @GetMapping("/")
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

        @PostMapping("/products/create")
        public String saveProduct(@ModelAttribute Product product) {
            productService.saveProduct(product);
            return "redirect:/";
        }

        @PostMapping("/products/delete/{id}")
        public String deleteProduct(@PathVariable Integer id) {
            productService.deleteProductById(id);
            return "redirect:/";
        }
        @GetMapping("/products/{id}")
        public String viewProduct(@PathVariable Integer id, Model model) {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            return "productInfo";
        }
    }
