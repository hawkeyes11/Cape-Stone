import axios from 'axios';

const yelpToken = 'cvoslUcg3V5t7U61q0w44m0ozvmJO7T9y06V1LNR5V6vQssRuUF3xuoqePMjP-caC1zSU6_5xADKT1yGnkAvqtZKOlubGaxC0ZqXqJtIkn4BXGfcb8NI256HUp5QYnYx'

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

const yelp = axios.create({
  baseURL: "https://api.yelp.com/v3/businesses/",
});



export default {

  getRestaurantsById(restaurantId) {
    const config = {
      headers: { 'Authorization': 'Bearer ' + yelpToken }
    }
    return yelp.get(restaurantId, config);

  },


  getRestaurants(zip) {
    return http.get('restaurants?location=' + zip);
  },
  createGroup(location, expiration) {
    return http.get('create?location=' + location + "&expiration=" + expiration)
  },
  addRestaurantToGroupList(groupId, restaurantId) {
    return http.post(groupId, {
      "restaurantId": restaurantId
    });
  },
  addRestaurantToUserFav() {

  },
  getGroupRestaurants(groupId) {
    return http.get("groupinfo/" + groupId)
  },
  getCategories() {
    return http.get("categories")
  },
  addCategories(token, category) {
    const config = {
      headers: { 'Authorization': 'Bearer ' + token }
    }
    return http.post("user/categories", {
      category
    },
      config
    );
  },
  getUserCategories(username) {
    return http.get(username + "/categories")
  }

}