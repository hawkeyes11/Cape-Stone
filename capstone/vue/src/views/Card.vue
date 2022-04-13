<template>
  <div>
    <div class="search">
    <input 
      type="text" placeholder="Enter a city or zip code"
      v-model="zip"
    />
    <button @click="getRestaurants()">Get Restaurants</button>
    <restaurant-card :restaurant='card' v-for="card in response" :key = "card.id" />
    </div>
  </div>
</template>

<script>
import RestaurantCard from '@/components/RestaurantCard.vue';
import restaurantService from '../services/RestaurantService.js'
export default {
  components: {
    RestaurantCard
  },
  methods: {
    getRestaurants() {

      restaurantService.getRestaurants(this.zip).then((r) => {
        this.response = r.data
        console.log(r.data)
      })

    }
  },
  data() {
    return {
      zip:'',
      response:''
    }
  }
};
</script>

<style>
div.search {
  display: flex;
  flex-direction: column;
}
div > input {
  display: flex;
  padding: 10px;
  font-size: 16px;
}
div > button {
  display: flex;
  margin-top: 1em;;
  justify-content: center;
  align-content: center;
  margin-top: 10px;
  background-image: radial-gradient(#FF0821, #FF5465);
  padding: 5px 2px 5px 2px;
  border-radius: 5px;
  margin-bottom: 2%;
  font-size: 16px;

}


</style>
