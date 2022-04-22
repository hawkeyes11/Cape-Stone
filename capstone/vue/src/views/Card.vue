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

    <div class="favorites" v-if="userRestaurantList.length > 0">
      <div>
        <span>Favorited Restaurants</span>
      </div>
      <div class="small-card" v-for="rest in userRestaurantList" :key="rest.id">
        <h2>{{ rest.name }}</h2>
        <ul class="categories">
          <li v-for="c in rest.categories" :key="c.id">{{ c }}</li>
        </ul>
        <a class="info-link" target="_blank" :href="rest.websiteUrl"
          >More information</a
        >
      </div>
    </div>
    <!-- <div class="search">
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
      <p v-if="url">
        {{
          "Link to the list of cards that the host added for friends to agree or disagree on(Needs front end to display cards by id, not sure how to do this yet) " +
            url
        }}
      </p>
    </div> -->
    <input
      class="search-input"
      v-if="!isHost"
      @keyup.enter="getRestaurants()"
      type="text"
      placeholder="Enter a city or zip code"
      v-model="zip"
    />
    <button class="custom-btn" v-if="!isHost" @click="getRestaurants()">
      <span>Search Restaurants</span>
    </button>
    <restaurant-card
      :hosting="groupId"
      :restaurant="card"
      v-for="card in response"
      :key="card.id"
    />
    <!-- <router-link @click.native="$scrollToTop"> -->
    <!-- <button @click="scrollToTop()" class="custom-btn top" title="Go to top">Top</button> -->
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
    scrollToTop() {
      window.scrollTo(0, 0);
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
.search {
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
  outline: none;
  margin-left: 20px;
}
button {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 25%;
  height: 100%;
  text-align: middle;
}
button span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.5em;
  font-weight: 400;
}
.custom-btn {
  height: 75px;
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
  outline: none;
  line-height: 42px;
  border: none;
  background: linear-gradient(0deg, #62cdd9, #60f0d6);
}
button:hover {
  color: #62cdd9;
  background: transparent;
  box-shadow: none;
}
button:before,
button:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: #5c8fe6;
  box-shadow: -1px -1px 5px 0px #fff, 7px 7px 20px 0px #0003,
    4px 4px 5px 0px #0002;
  transition: 400ms ease all;
}
button:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
button:hover:before,
button:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
.small-card {
  display: inline-block;
  flex-direction: row;
  justify-content: center;
  align-content: space-between;
  align-items: center;
  margin: 2%;
  padding: 1%;
  background-color: white;
  border-radius: 5px;
  width: 15%;
  height: 10%;
}
</style>
