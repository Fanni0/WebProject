package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.model.WebShop;

public interface WebShopService {

    List<WebShop> getAllWebShops();

    WebShop getWebShop(Long id);

    WebShop createWebShop(WebShop webShop);

    WebShop updateWebShop(Long id, WebShop webShopChange);

    void deleteWebShop(Long id);

    WebShop addToCart(Long id);

    List<WebShop> getAllCart();

    WebShop getCart(Long id);

    void deleteCart(Long id);

    List<WebShop> selectByCategory(Category category);

    List<WebShop> getAllSelected();

    List<WebShop> getAllSorted();

}
