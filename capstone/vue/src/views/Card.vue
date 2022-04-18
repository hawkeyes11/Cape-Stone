<template>
  <div class="content">
    <!-- <div class="category-btn">
      <button @click="filteredPrice">Category Sort</button>
    </div> -->

    <!-- <div class="well" v-bind:class="{ filtered: filter === 1 }">
                <span class="amount" v-on:click="filter = 1">{{  numberOfReviews(1) }}</span>
               1 Star Review{{ numberOfReviews(1) == 1 ? '' : 's' }}
            </div>
             -->

    <div v-if="userRestaurantList.length > 0">
      {{
        "List of user liked restaurants(Goes away on refresh): " +
          userRestaurantList
      }}
    </div>
    <div class="search">
      <button @click="isHost = !isHost" class="host">Are you the host?</button>
      <input
        type="date"
        v-if="isHost"
        placeholder="Expiration Date"
        v-model="expiration"
      />
      <input
        v-if="isHost"
        @keyup.enter="hosting()"
        type="text"
        placeholder="Enter a city or zip code"
        v-model="groupZip"
      />
      <button v-if="isHost" @click="hosting">Create Group</button>
      <p v-if="url">
        {{
          "Link to the list of cards that the host added for friends to agree or disagree on(Needs front end to display cards by id, not sure how to do this yet) " +
            url
        }}
      </p>
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
      :hosting="groupId"
      :restaurant="card"
      v-for="card in response"
      :key="card.id"
    />

    <!-- <button class="top">BACK TO TOP</button> -->
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
          this.groupId = r.data.substring(r.data.length - 6);
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
      response: [],
      isLoading: true,
      isHost: false,
      groupId: "",
      groupZip: "",
      expiration: "",
      url: "",
      userRestaurantList: this.$store.state.userRestaurantList,
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
button.top {
  position: fixed;
  justify-content: baseline;
  bottom: 0;
  right: 0;
  margin-right: 15px;
}
</style>
