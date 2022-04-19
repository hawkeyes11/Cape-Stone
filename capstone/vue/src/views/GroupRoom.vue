<template>
  <div>
    <restaurant-card
      :hosting="groupId"
      :restaurant="card"
      v-for="card in response"
      :key="card.id"
    />
  </div>
</template>

<script>
import RestaurantCard from "@/components/RestaurantCard.vue";
import restaurantService from "../services/RestaurantService.js";
export default {
  components: {
    RestaurantCard,
  },
  methods: {
    getRestaurants() {
      restaurantService.getRestaurants(this.zip).then((r) => {
        this.response = r.data;
        console.log(r.data);
      });
    },
  },
  data(){
      return {
          groupId: "",
          zip: "",
          
      }
  }
};
</script>

<style>
</style>