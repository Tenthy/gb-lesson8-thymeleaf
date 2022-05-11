package ru.gb.thymeleafprepare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafprepare.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
