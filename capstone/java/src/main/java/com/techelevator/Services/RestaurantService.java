package com.techelevator.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Restaurant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RestaurantService implements RestaurantServiceAPI {
    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<Restaurant> getRestaurants(String zipcode) {
        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth("cvoslUcg3V5t7U61q0w44m0ozvmJO7T9y06V1LNR5V6vQssRuUF3xuoqePMjP-caC1zSU6_5xADKT1yGnkAvqtZKOlubGaxC0ZqXqJtIkn4BXGfcb8NI256HUp5QYnYx");
        HttpEntity entity = new HttpEntity(header);
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            ResponseEntity<String> response = restTemplate.exchange("https://api.yelp.com/v3/businesses/search?location=" + zipcode + "&categories=food", HttpMethod.GET, entity, String.class);
            restaurantList = jsonMapping(response);

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        return restaurantList;
    }

    private List<Restaurant> jsonMapping(ResponseEntity<String> response) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode root = null;
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            root = jsonMapper.readTree(response.getBody());
            JsonNode results = root.get("businesses");
            Iterator<JsonNode> iterator = results.elements();
            while(iterator.hasNext()) {

                JsonNode currentElement = iterator.next();

                String name = currentElement.get("name").asText();

                int reviewCount = currentElement.get("review_count").asInt();

                double rating = currentElement.get("rating").asDouble();

                JsonNode location = currentElement.get("location");
                Iterator<JsonNode> locationEl = location.get("display_address").elements();
                List<String> addressList = new ArrayList<>();
                while(locationEl.hasNext()) {
                    addressList.add(locationEl.next().asText());
                }

                String url = currentElement.get("image_url").asText();

                String phoneNumber = currentElement.get("display_phone").asText();

                double distance = convertToMiles(currentElement.get("distance").asDouble());

                Iterator<JsonNode> category = currentElement.get("categories").elements();
                List<String> categories = new ArrayList<>();
                while (category.hasNext()) {
                    categories.add(category.next().get("alias").asText());
                }

                String websiteUrl = currentElement.get("url").asText();

                Iterator<JsonNode> transaction = currentElement.get("transactions").elements();
                List<String> transactions = new ArrayList<>();
                while(transaction.hasNext()) {
                    transactions.add(transaction.next().asText());
                }

                boolean isClosing = currentElement.get("is_closed").asBoolean();

                restaurantList.add(new Restaurant(name, reviewCount, rating, categories, addressList, phoneNumber, distance, transactions, isClosing, url, websiteUrl));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    private double convertToMiles(double meters) {
        return (meters / 1609.34);
    }
}
