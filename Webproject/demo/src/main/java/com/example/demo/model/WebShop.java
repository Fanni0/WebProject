package com.example.demo.model;

import java.util.Objects;
import java.util.StringJoiner;

public class WebShop {
    private Long id;
    private String title;
    private String author;
    private Category category;
    private int date;
    private Cover cover;
    private int price;

    public WebShop() {
    }

    public WebShop(Long id, String title, String author, Category category, int date, Cover cover, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.date = date;
        this.cover = cover;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public int getDate() { return date; }

    public void setDate(int date) { this.date = date; }

    public Cover getCover() { return cover; }

    public void setCover(Cover cover) { this.cover = cover; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WebShop)) {
            return false;
        }
        WebShop webshop = (WebShop) o;
        return date == webshop.date && price == webshop.price && Objects.equals(id, webshop.id) &&
                Objects.equals(title, webshop.title) && Objects.equals(author, webshop.author) &&
                category == webshop.category && cover == webshop.cover;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, category, date, cover, price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WebShop.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("category=" + category)
                .add("date=" + date)
                .add("cover=" + cover)
                .add("price=" + price)
                .toString();
    }
}
