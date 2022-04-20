<template>
  <div class="restaurant-cards">
    
    <div class="card">
      <h2 class="name">{{ restaurant.name }}</h2>
      <div class="address">
        <a target="_blank" :href="'https://www.google.com/maps/place/' +  restaurant.display_address[0] + '+' + restaurant.display_address[1]">Address: {{ restaurant.display_address[0] }}
        {{ restaurant.display_address[1] }}</a>
      </div>
      <a :href="'tel:' + restaurant.display_phone" class="phone"
        >Call to Order: {{ restaurant.display_phone }}</a
      >

      <!-- need to round off number -->
      <!-- <p class="distance">Distance in miles: {{ restaurant.distance }}</p> -->

      <p class="hours">{{ restaurant.isClosed ? "Closed" : "Open now" }}</p>

      <!-- need to display stars not number -->
      <div class="rating">
        Average Rating: {{ restaurant.rating }} <br />
        Amount of Reviews: {{ restaurant.reviewCount }}
      </div>

      <p class="transaction" v-for="t in restaurant.transactions" :key="t.id">
        {{ t }}
      </p>

      <img class="photo" :src="restaurant.url" />

      <a class="info-link" target="_blank" :href="restaurant.websiteUrl"
        >Link to more information</a
      >

      <p class="price">{{ restaurant.price }}</p>

      <!-- TODO correctly concat strings of categories -->
      <ul class="categories">
        <li v-for="c in restaurant.categories" :key="c.id">{{ c }}</li>
      </ul>
    </div>
  
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService.js";

export default {
  name: "restaurant-card",
  props: ["restaurant", "hosting"],
  methods: {
    addToList() {
      if (this.hosting) {
        restaurantService
          .addRestaurantToGroupList(this.hosting, this.restaurant.restaurantId)
          .then(() => {
            alert("Restaurant Liked");
          });
      } else {
        this.$store.commit("ADD_TO_USER_LIST", this.restaurant);
        console.log(this.$store.state.userRestaurantList);
      }
    },
    dislike(){
      alert("Restaurant Disliked")
    }
  },
};
</script>

<style scoped>
.restaurant-cards {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: center;
  align-items: center;
  margin: 1% 10% 2% 10%;
  background-color: #fffddb;
  border: 3px solid #F21616;
  border-radius: 1em;
  height: 25%;
  width: auto;
}
.dislike {
  display: flex;
  width: 15%;
  height: auto;
  justify-content: center;
  margin: 2%;
}
.like {
  display: flex;
  width: 15%;
  height: auto;
  justify-content: center;
  margin: 2%;
}
.card {
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 20px;
  /* border: 2px solid red; */
  /* position: relative; */
  /* height: 35rem; */
  justify-content: center;
  align-items: left;
  /* padding-right: 1em; */
  padding: 1%;
  margin: 2%;
  width: 60%;
  height: 50%;
  filter: drop-shadow(3px 3px 10px #000000);
}
.name {
  /* display: flex; */
  font-size: 2rem;
}

a {
  display: flex;
}
a.phone {
  display: flex;
  padding-top: 1em;
}
a.info-link {
  display: flex;
}
div.address {
  display: flex;
  padding-top: 1em;
}
/* div.distance {
  display: flex;
} */
div.rating {
  display: flex;
}
div.phone {
  display: flex;
}
div.hours {
  display: flex;
}
.categories {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap-reverse;
}
.categories li {
  list-style: none;
}
.photo {
  display: flex;
  border-radius: 10px 0px 10px 0px;
  padding: 1%;
  width: 50%;
  height: 50%;
}
/*form.form-signin {
  display: flex;
  flex-direction: column;
  background-color: #FBF2EB;
  width: 750px;
  border-radius: 15px;
  padding: 2.5%;
  height :100%
}
label {
  display: flex;
  padding: 10px;
  font-size: 24px;
}
label.username {
  display: flex;
  margin: 2%;
}
input#username {
  display: flex;
  margin: 2%;
  width: 50%;
  padding: 10px;
  font-family: monospace;
  font-size: 16px;
}
label.password {
  display: flex;
  margin: 2%;
}
input#password {
  display: flex;
  margin: 2%;
  width: 50%;
  padding: 10px;
  margin-bottom: 5%;
  font-family: monospace;
}
form#register {
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  display: flex;
  width: 50%;
  
}
button {
  width: 75%;
  justify-content: center;
  align-content: center;
  margin-top: 10px;
  background-image: radial-gradient(#FF0821, #FF5465);
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 2%;
  font-size: 18px;
} */
</style>
