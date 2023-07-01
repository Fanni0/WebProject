package com.example.demo.controller;

import java.awt.PageAttributes;
import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.model.WebShop;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.service.WebShopService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web-shop")
public class WebShopController {

    private final WebShopService webShopService;

    public WebShopController(WebShopService webShopService) { this.webShopService = webShopService; }

    @GetMapping
    public String getAllWebShop(final Model model){
        final List<WebShop> webShops = webShopService.getAllWebShops();
        model.addAttribute("webShops", webShops);
        return"webshop/list";
    }

    @GetMapping("/{id}")
    public String getWebShop(final Model model, final @PathVariable Long id){
        final WebShop webShop = webShopService.getWebShop(id);
        model.addAttribute("webShop", webShop);
        return"webshop/edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createWebShop(final Model model, final @RequestParam(value = "id", required = false) Long id, final WebShop webShopChange){
        final WebShop webShop = webShopService.updateWebShop(id, webShopChange);
        model.addAttribute("webshop", webShop);
        return"webshop/edit";
    }

    @GetMapping("/create")
    public String createwebShopForm(final Model model) { return"webshop/create"; }

    @PostMapping("/create")
    public String createWebShop(final Model model, final WebShop webShop){
        final WebShop savedWebShop = webShopService.createWebShop(webShop);
        model.addAttribute("webshop", savedWebShop);
        return"webshop/edit";
    }

    @GetMapping("/{id}/delete")
    public String deletewebShop(final Model model, final @PathVariable("id") Long id){
        try{
            webShopService.deleteWebShop(id);
        } catch (NotFoundException e){

        }
        final List<WebShop> webShops = webShopService.getAllWebShops();
        model.addAttribute("webShops", webShops);
        return"webshop/list";
    }

    @GetMapping("/{id}/cart")
    public String addtocart(final Model model, final @PathVariable("id") Long id){
        try{
            webShopService.addToCart(id);
        } catch (NotFoundException e){

        }
        final List<WebShop> webShops = webShopService.getAllWebShops();
        model.addAttribute("webShops", webShops);
        return"webshop/list";
    }

    @GetMapping("/cart")
    public String viewCart(final Model model) {
        final List<WebShop> webShops = webShopService.getAllCart();
        model.addAttribute("webShops", webShops);
        return"webshop/cart";
    }

    @GetMapping("/cart/{id}/delete")
    public String deleteCart(final Model model, final @PathVariable("id") Long id){
        try{
            webShopService.deleteCart(id);
        } catch (NotFoundException e){

        }
        final List<WebShop> webShops = webShopService.getAllCart();
        model.addAttribute("webShops", webShops);
        return"webshop/cart";
    }

    @GetMapping("/select")
    public String selectByCategory(final Model model, final @RequestParam(value = "categorysel", required = false) Category category){
        webShopService.selectByCategory(category);
        final List<WebShop> webShops = webShopService.getAllSelected();
        model.addAttribute("webShops", webShops);
        return"webshop/list";
    }
}
