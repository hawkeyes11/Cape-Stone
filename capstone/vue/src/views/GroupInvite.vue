<template>
  <div class="content">
    <div class="search">
      <button @click="isHost = !isHost" class="custom-btn host">
        <span>Invite Friends</span>
      </button>
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
      <router-link v-bind:to="{ name: 'join', params: { groupId: groupId} } ">
      <button class="custom-btn" v-if="url">
      <p v-if="url">
        {{
          url
        }}
      </p>
      </button>
      </router-link>
    </div>
<!-- 
  <input
      class="search-input"
      @keyup.enter="getGroupRestaurants()"
      type="text"
      placeholder="Enter group id"
      v-model="groupId"
    /> -->
   <!-- <input
      class="search-input"
      @keyup.enter="getRestaurantsById()"
      type="text"
      placeholder="Enter restaurant id"
      v-model="restaurantId"
    />  -->
    <!-- <restaurant-card
      :hosting="groupId"
      :restaurant="card"
      v-for="card in response"
      :key="card.id"
    /> -->

    <!-- <button class="top">BACK TO TOP</button> -->
  </div>
</template>

<script>
// import RestaurantCard from "@/components/RestaurantCard.vue";
import restaurantService from "../services/RestaurantService.js";
export default {
  components: {
    // RestaurantCard,
  },
  methods: {
    getGroupRestaurants() {
      restaurantService.getGroupRestaurants(this.groupId).then((r) => {
        this.groupThings = r.data;
        console.log(r.data);
      });
    },
    getRestaurantsById() {
      restaurantService.getRestaurantsById(this.restaurantId).then((r) => {
        // this.restaurantList = r.data;
        console.log(r.data);
      });
    },
  getRestaurants() {
    restaurantService.getRestaurants(this.zip).then((r) => {
      this.response = r.data;
      console.log(r.data);
    });
  },
  hosting() {
    restaurantService.createGroup(this.groupZip, this.expiration).then((r) => {
      this.url = r.data;
      this.groupId = r.data.substring(r.data.length - 6);
      this.$store.commit("SET_GROUP_ID", this.groupId);
      console.log(this.groupId);
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
      restaurantId: "",
      groupId: "",
      counter: "",
      response: [],
      groupThings: [],
      restaurantList: [],
      isHost: false,
      groupZip: "",
      url: "",
      expiration: "",
      isLoading: true,

      groupRestaurantList: this.$store.state.groupRestaurantList,
    };
  },
};
</script>

<style scoped>
/* .content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0% 0% 20% 0%;
  width: 100%;
} */
div.search {
  display: flex;
  flex-direction: column;
  width: 50%;
  justify-items: center;
}
input {
  display: flex;
  border-radius: 5px 0px 5px 0px;
  padding: 10px;
  font-size: 1em;
}
button {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 75%;
  height: 100%;
  text-align: middle;
}
button span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.25em;
}
.custom-btn {
  display: flex;
  flex-shrink: 1;
  height: 75px;
  background-color: #f25c05;
  color: #000;
  border-radius: 5px;
  padding: 10px 25px 10px 25px;
  font-family: "Lato", sans-serif;
  font-weight: 500;
  /* background: transparent; */
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}
button:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image: linear-gradient(to left, #f25c05, #f2b705);
  border-radius: 5px;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

button:hover {
  color: #fff;
}
button:hover:after {
  left: 0;
  width: 100%;
}
button:active {
  top: 2px;
}
button.top {
  position: fixed;
  justify-content: baseline;
  bottom: 0;
  right: 0;
  margin-right: 15px;
}
</style>
