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
      <div v-for="rest in userRestaurantList" :key="rest.id">
        <h2>{{rest.name}}</h2>
          <ul class="categories">
            <li v-for="c in rest.categories" :key="c.id">{{ c }}</li>
          </ul>
        <a class="info-link" target="_blank" :href="rest.websiteUrl">Like to more information</a>
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

    <a href=".app"><button @click="scrollToTop()" class="custom-btn top" title="Go to top">Top</button></a>

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
      document.documentElement.scrollTop = 0; 
    }
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
content {

}
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
