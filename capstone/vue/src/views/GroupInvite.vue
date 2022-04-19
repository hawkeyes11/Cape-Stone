<template>
  <div>
    <input
        class="search-input"
        type="date"
        v-if="isHost"
        placeholder="Expiration Date"
        v-model="expiration"
      />
      <input
        class="search-input"
        v-if="isHost"
        @keyup.enter="hosting()"
        type="text"
        placeholder="Enter a city or zip code"
        v-model="groupZip"
      />
      <button class="custom-btn" v-if="isHost" @click="hosting">
        <span>Send List to Friends</span>
      </button>
      <p v-if="url">
        {{
          "Link to the list of cards that the host added for friends to agree or disagree on(Needs front end to display cards by id, not sure how to do this yet) " +
            url
        }}
      </p>
    <!-- <input
      class="search-input"
      @keyup.enter="getGroupRestaurants()"
      type="text"
      placeholder="Enter group id"
      v-model="groupId"
    />
   <input
      class="search-input"
      @keyup.enter="getRestaurants()"
      type="text"
      placeholder="Enter restaurant id"
      v-model="restaurantId"
    /> -->
  <restaurant-card
    v-for="card in restaurantList" :key="card.id"
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
    getGroupRestaurants(){
      restaurantService.getGroupRestaurants(this.groupId).then((r) => {
        this.groupThings = r.data;
        console.log(r.data);
      })
    },
    getRestaurantsById(){
      restaurantService.getRestaurantsById(this.restaurantId).then((r) => {
        // this.restaurantList = r.data;
        console.log(r.data);
      })
    }
  },
  getRestaurants() {
      restaurantService.getRestaurants(this.zip).then((r) => {
        this.response = r.data;
        console.log(r.data);
      });
    },
  data(){
    return{
      restaurantId: "",
      groupId: "",
      counter: "",
      groupThings: [],
      restaurantList: [],
      
      groupRestaurantList: this.$store.state.groupRestaurantList,
    }
  }
};
</script>

<style scoped>
.content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0% 0% 20% 0%;
  width: 100%;
}
</style>
