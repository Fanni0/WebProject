package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.model.Cover;
import com.example.demo.model.WebShop;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopServiceImpl implements WebShopService {

    private static final List<WebShop> DATA_BASE = new ArrayList<>();
    private static final List<WebShop> CART_CONTENT = new ArrayList<>();
    private static final List<WebShop> CATEGORY_SELECTED = new ArrayList<>();

    @Autowired
    public WebShopServiceImpl(){
        DATA_BASE.add(new WebShop(1L, "Üvegtrón - Éjkorona", "Sarah J. Maas", Category.FANTASY, 2014, Cover.SOFT_COVER, 4369));
        DATA_BASE.add(new WebShop(2L, "Üvegtrón - A tűz örököse", "Sarah J. Maas", Category.FANTASY, 2015, Cover.HARD_COVER, 4274));
        DATA_BASE.add(new WebShop(3L, "Assassin's Creed - Testvériség", "Oliver Bowden", Category.HISTORICAL_FICTION, 2011, Cover.SOFT_COVER, 3290));
        DATA_BASE.add(new WebShop(4L,"Harry Potter és a Félvér Herceg", "J. K. Rowling", Category.FANTASY, 2006, Cover.HARD_COVER, 5690));
        DATA_BASE.add(new WebShop(5L, "A Gyűrűk Ura - A király visszatér", "J. R. R. Tolkien", Category.FANTASY, 2004, Cover.HARD_COVER, 3000));
        DATA_BASE.add(new WebShop(6L, "Vaják I. - Az utolsó kívánság", "Andrzej Sapkowski", Category.FANTASY, 2011, Cover.SOFT_COVER, 3790));
        DATA_BASE.add(new WebShop(7L, "Cinder és Ella", "Kelly Oram", Category.ROMANCE, 2014, Cover.SOFT_COVER, 2900));
    }

    public WebShopServiceImpl(final List<WebShop> webShops){
        DATA_BASE.addAll(webShops);
    }


    @Override
    public List<WebShop> getAllWebShops() {
        return Collections.unmodifiableList(DATA_BASE);
    }

    @Override
    public WebShop getWebShop(Long id) {
        return DATA_BASE.stream()
                .filter(webShop -> webShop.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public WebShop createWebShop(WebShop webShop) {
        webShop.setId(getNextId());
        DATA_BASE.add(webShop);
        return webShop;
    }

    @Override
    public WebShop updateWebShop(Long id, WebShop webShopChange) {
        final WebShop webShop = getWebShop(id);
        webShop.setTitle(webShopChange.getTitle());
        webShop.setAuthor(webShopChange.getAuthor());
        webShop.setCategory(webShopChange.getCategory());
        webShop.setDate(webShopChange.getDate());
        webShop.setCover(webShopChange.getCover());
        webShop.setPrice(webShopChange.getPrice());
        return webShop;
    }

    @Override
    public void deleteWebShop(Long id) {
        final WebShop webShop = getWebShop(id);
        DATA_BASE.remove(webShop);
    }

    @Override
    public WebShop addToCart(Long id) {
        final WebShop webShop = getWebShop(id);
        CART_CONTENT.add(new WebShop(getNextCartId(), webShop.getTitle(), webShop.getAuthor(), webShop.getCategory(), webShop.getDate(), webShop.getCover(),
                webShop.getPrice()));
        return webShop;
    }

    @Override
    public List<WebShop> getAllCart() {
        return Collections.unmodifiableList(CART_CONTENT);
    }

    @Override
    public WebShop getCart(Long id) {
        return CART_CONTENT.stream()
                .filter(webShop -> webShop.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteCart(Long id) {
        final WebShop webShop = getCart(id);
        CART_CONTENT.remove(webShop);
    }

    @Override
    public List<WebShop> selectByCategory(Category category) {
        WebShop webShop;
        for(int i=0; i<getLastId();i++){
            webShop = DATA_BASE.get(i);
            if (webShop.getCategory().equals(category))
                CATEGORY_SELECTED.add(webShop);
        }
        return CATEGORY_SELECTED;
    }

    @Override
    public List<WebShop> getAllSelected() {
        return Collections.unmodifiableList(CATEGORY_SELECTED);
    }

    @Override
    public List<WebShop> getAllSorted() {
        //return Collections.sort(DATA_BASE, Comparator.comparingInt(WebShop::getDate));
        return null;
    }

    private long getNextId(){ return getLastId() + 1L; }

    private long getLastId(){
        return DATA_BASE.stream()
                .mapToLong(WebShop::getId)
                .max()
                .orElse(0);
    }

    private long getNextCartId(){ return getCartId() + 1L; }

    private long getCartId(){
        return CART_CONTENT.stream()
                .mapToLong(WebShop::getId)
                .max()
                .orElse(0);
    }
}
