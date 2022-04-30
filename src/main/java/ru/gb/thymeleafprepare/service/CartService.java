package ru.gb.thymeleafprepare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.thymeleafprepare.dao.CartDao;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CartService {

    private final CartDao cartDao;

    public Cart saveAndFlush(Cart cart) {
        return cartDao.saveAndFlush(cart);
    }

    public List<Product> findAllProductInCart(Cart cart) {
        return cart.findAllProductInCart();
    }

    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }
}
