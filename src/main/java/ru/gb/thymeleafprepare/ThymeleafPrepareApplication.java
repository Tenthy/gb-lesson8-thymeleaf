package ru.gb.thymeleafprepare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.entity.enums.Status;
import ru.gb.thymeleafprepare.service.CartService;
import ru.gb.thymeleafprepare.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ThymeleafPrepareApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ThymeleafPrepareApplication.class, args);

        ProductService productService = context.getBean(ProductService.class);
        CartService cartService = context.getBean(CartService.class);

        Cart cart = cartService.findById(2L);
        cart.deleteProduct(productService.findById(1L));
        cart.deleteProduct(productService.findById(2L));
        cart.deleteProduct(productService.findById(3L));
        cartService.saveAndFlush(cart);
    }
}
