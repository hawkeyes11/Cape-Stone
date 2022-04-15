<template>
  <div>
    <div class="search">
      <button @click="isHost = !isHost" class="host">Are you the host?</button>
      <input type="date" v-if="isHost" placeholder="Expiration Date" v-model="expiration" />
      <input
        v-if="isHost"
        @keyup.enter="hosting()"
        type="text"
        placeholder="Enter a city or zip code"
        v-model="groupZip"
      />
      <button v-if="isHost" @click="hosting">Create Group</button>
      <p>{{ url }}</p>
    </div>
    <input
      v-if="!isHost"
      @keyup.enter="getRestaurants()"
      type="text"
      placeholder="Enter a city or zip code"
      v-model="zip"
    />
    <button v-if="!isHost" @click="getRestaurants()">Get Restaurants</button>
    <restaurant-card
      :restaurant="card"
      v-for="card in response"
      :key="card.id"
    />
    <!--     
    <div v-if="isLoading">
      <img src="@/assets/Food_load.gif" />
    </div> -->
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
    loading() {
      setTimeout(() => {
        this.isLoading = false;
      }, 1850);
    },
    hosting() {
      restaurantService
        .createGroup(this.groupZip, this.expiration)
        .then((r) => {
        this.url = r.data;
        restaurantService.getRestaurants(this.groupZip).then((r) => {
        this.response = r.data;
        console.log(r.data);
        });
        });
    },
  },
  data() {
    return {
      zip: "",
      response: "",
      isLoading: true,
      isHost: false,
      groupZip: "",
      expiration: "",
      url: "",
    };
  },
  mounted() {
    this.loading();
  },
};
</script>

<style scoped>
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
  background-image: radial-gradient(#ff0821, #ff5465);
  padding: 5px 2px 5px 2px;
  border-radius: 5px;
  margin-bottom: 2%;
  font-size: 16px;
}
</style>
