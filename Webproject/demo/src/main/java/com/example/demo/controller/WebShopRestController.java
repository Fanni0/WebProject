package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.WebShop;
import com.example.demo.service.WebShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/web-shop")
public class WebShopRestController {

    private final WebShopService webShopService;

    public WebShopRestController(WebShopService webShopService) { this.webShopService = webShopService; }

    @GetMapping
    public List<WebShop> getallWebShops(){ return webShopService.getAllWebShops(); }

    @GetMapping("/{id}")
    WebShop getWebShop(@PathVariable("id") final Long id){ return webShopService.getWebShop(id); }

    @PostMapping
    WebShop createwebShop(@RequestBody final WebShop webShop){
        return webShopService.createWebShop(webShop);
    }

    @PutMapping("/{id}")
    WebShop updatewebShop(@PathVariable final Long id, final @RequestBody WebShop webShopChange){
        return webShopService.updateWebShop(id, webShopChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWebShop(@PathVariable final Long id){
        webShopService.deleteWebShop(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
