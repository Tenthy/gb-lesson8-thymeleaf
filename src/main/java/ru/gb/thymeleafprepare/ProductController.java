package ru.gb.thymeleafprepare;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.service.CartService;
import ru.gb.thymeleafprepare.service.ProductService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        //id вручную указал для удобства
        Long id = 2L;
        model.addAttribute("cart", cartService.findAllProductInCart(cartService.findById(id)));
        return "product-cart";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product;
        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    public String saveProduct(Product product) {
        product.setManufactureDate(LocalDate.now());
        productService.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/add/{id}")
    public String addProductInCart(@PathVariable(name = "id") Long id) {
        Cart cart = cartService.findById(2L);
        cart.addProduct(productService.findById(id));
        cartService.saveAndFlush(cart);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/delete-from-cart/{id}")
    public String deleteProductFromCart(@PathVariable(name = "id") Long id) {
        //значение 2L указал для удобства
        Cart cart = cartService.findById(2L);
        cart.deleteProduct(productService.findById(2L));
        cartService.saveAndFlush(cart);
        return "redirect:/product/cart";
    }
}
