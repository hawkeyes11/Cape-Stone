<template>
  <div>
    <div v-if="!isLoading" class="search">
    <input @keyup.enter="getRestaurants()"
      type="text" placeholder="Enter a city or zip code"
      v-model="zip"
    />
    <button @click="getRestaurants()">Get Restaurants</button>
    <restaurant-card :restaurant='card' v-for="card in response" :key = "card.id" />
    <button class="host">Are you the host?</button>
    </div>
    <div v-if="isLoading">
      <img src="@/assets/Food_load.gif" />
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
      });
    },
    loading() {
      setTimeout(() => {
        this.isLoading = false
      }, 1850)
    }
  },
  data() {
    return {
      zip:'',
      response:'',
      isLoading: true
    }
  },
  mounted() {
   this.loading()
  }
};
</script>

<style scoped>

/* div {
  display: flex;
  justify-items: center;
} */
div.search {
  display: flex;
  flex-direction: column;
  width: 50%;
  justify-items: center;
}
input {
  display: flex;
  padding: 10px;
  font-size: 16px;
}
button {
  display: flex;
  margin-top: 1em;
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
