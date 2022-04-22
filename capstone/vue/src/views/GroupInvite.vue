<template>
  <div class="content">
    <div class="search">
      <button @click="isHost = !isHost" class="custom-btn host">
        <span>Click here to start!</span>
      </button>
      <input
      :min="today"
        class="date"
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
      <button class="custom-btn invite" v-if="isHost" @click="hosting">
        <span>Generate your custom invite link</span>
      </button>
      <router-link v-bind:to="{ name: 'join', params: { groupId: groupId} } ">
      <button class="custom-btn link" v-if="url">
      <p v-if="url">
        {{
          url
        }}
      </p>
      </button>
      </router-link>
      <input v-if="url" type="text" placeholder="Enter email or phone number to invite a friend">
      <button class="custom-btn"   v-if="url" @click="alert">Send your link</button>
    </div>
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
  //   getGroupRestaurants() {
  //     restaurantService.getGroupRestaurants(this.groupId).then((r) => {
  //       this.groupThings = r.data;
  //       console.log(r.data);
  //     });
  //   },
  //   },
  // getRestaurants() {
  //   restaurantService.getRestaurants(this.zip).then((r) => {
  //     this.response = r.data;
  //     console.log(r.data);
  //   });

  hosting() {
    restaurantService.createGroup(this.groupZip, this.expiration).then((r) => {
      this.url = r.data;
      this.groupId = r.data.substring(r.data.length - 6);
      restaurantService.getRestaurants(this.groupZip).then((r) => {
        this.response = r.data;
        console.log(r.data);
      });
    });
  },
  alert() {
    alert("Your invite has been sent!");
  }
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
      today: new Date().toISOString().slice(0,10),
      groupRestaurantList: this.$store.state.groupRestaurantList,
    };
  },
};
</script>

<style scoped>
.content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: left;
  margin: 0% 0% 20% 0%;
  width: 100%;
}
div.search {
  display: flex;
  /* background-color: #fffddb; */
  flex-direction: column;
  width: 50%;
  justify-items: center;
}
input {
  display: flex;
  border-radius: 5px 0px 5px 0px;
  padding: 10px;
  font-size: 1em;
  margin: 0% 2% 1% 2%;
  width: 50%;
  border: none;
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
button.invite {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 50%;
  height: 100%;
  text-align: middle;
}
button.invite span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.5em;
  font-weight: 400;
}
.custom-btn .invite {
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
button.invite:hover {
  color: #62cdd9;
  background: transparent;
  box-shadow: none;
}
button.invite:before,
button.invite:after {
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
button.invite:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
button.invite:hover:before,
button.invite:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
button.link {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 50%;
  height: 100%;
  text-align: middle;
}
button.link span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.5em;
  font-weight: 400;
}
.custom-btn .invite {
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
button.invite:hover {
  color: #62cdd9;
  background: transparent;
  box-shadow: none;
}
button.invite:before,
button.invite:after {
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
button.invite:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
button.invite:hover:before,
button.invite:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
</style>
