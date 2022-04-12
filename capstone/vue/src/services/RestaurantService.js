import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/restaurants"
});

export default {
    getRestaurants(zip){
       return http.get('?location=' + zip);
    } 
}