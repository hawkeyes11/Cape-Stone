package com.techelevator.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Restaurant {
    private String name;
    private int reviewCount;
    private double rating;
    private List<String> Categories;
    private List<String> display_address;
    private String display_phone;
    private double distance;
    private List<String> transactions;


    public Restaurant() {
    }

    public Restaurant(String name, int reviewCount, double rating, List<String> categories, List<String> display_address, String display_phone, double distance, List<String> transactions) {
        this.name = name;
        this.reviewCount = reviewCount;
        this.rating = rating;
        Categories = categories;
        this.display_address = display_address;
        this.display_phone = display_phone;
        this.distance = distance;
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void setCategories(List<String> categories) {
        Categories = categories;
    }

    public List<String> getDisplay_address() {
        return display_address;
    }

    public void setDisplay_address(List<String> display_address) {
        this.display_address = display_address;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }
}
