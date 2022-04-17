import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

export default {
    getRestaurants(zip){
       return http.get('restaurants?location=' + zip);
    },
    createGroup(location, expiration) {
      return http.get('create?location=' + location + "&expiration=" + expiration)
    },
    addRestaurantToGroupList(groupId, restaurantId) {
      return http.post(groupId, {
        "restaurantId" : restaurantId
      });
    },
    addRestaurantToUserFav() {
      
    }

}