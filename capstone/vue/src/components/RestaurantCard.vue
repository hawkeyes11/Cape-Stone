<template>
  <div class="restaurant-cards">
    <button class="dislike">Hate it</button>
    <div class="card">  
      <h2 class="name">{{ restaurant.name }}</h2>
      <div class="address">
        Address: {{ restaurant.display_address[0] }}
        {{ restaurant.display_address[1] }}
      </div>
      <a :href="'tel:' + restaurant.display_phone" class="phone"
        >Call to Order: {{ restaurant.display_phone }}</a
      >

      <!-- need to round off number -->
      <p class="distance">Distance in miles: {{ restaurant.distance }}</p>

      <p class="hours">{{ restaurant.isClosed ? "Closed" : "Open now" }}</p>

      <!-- need to display stars not number -->
      <div class="rating">
        Average Rating: {{ restaurant.rating }} <br />
        Amount of Reviews: {{ restaurant.reviewCount }}
      </div>

      <p v-for="t in restaurant.transactions" :key="t.id">{{ t }}</p>

      <img :src="restaurant.url" />

      <a target="_blank" :href="restaurant.websiteUrl">Like to more information</a>

      <p>{{restaurant.price}}</p>

      <!-- TODO correctly concat strings of categories -->
      <p v-for="c in restaurant.categories" :key="c.id">{{ c }}</p>
    </div>
    <button @click="addToList()" class="like">Yummy!</button>
  </div>
</template>

<script>
import restaurantService from '../services/RestaurantService.js'

export default {
  name: "restaurant-card",
  props: ["restaurant", "hosting"],
  methods: {
    addToList() {
      if(this.hosting) {
        restaurantService.addRestaurantToGroupList(this.hosting, this.restaurant.restaurantId)
        .then(() => {
          alert("Restaurant Added");
        })
      } else {
        this.$store.commit("ADD_TO_USER_LIST", this.restaurant.restaurantId);
        console.log(this.$store.state.userRestaurantList);
      }
    }
  }
};
</script>

<style scoped>
.card {
  border-radius: 20px;
  background-color: #fbf2eb;
  /* position: relative; */
  display: flex;
  flex-direction: column;
  /* height: 35rem; */
  justify-content: center;
  align-items: left;
  padding-left: 1em;
  /* padding-right: 1em; */
  padding-bottom: 1%;
  margin: 2%;
  margin-top: 1em;
  max-width: 1080px;
}
div > h2.name {
  /* display: flex; */
  font-size: 2rem;
  }
a.phone {
  /* display: flex; */
  padding-top: 1em;
}
a {
  /* display: flex; */
}
div.address {
  /* display: flex; */
  padding-top: 1em;
}
div.distance {
  /* display: flex; */
}
div.rating {
  /* display: flex; */
}
div.phone {
  /* display: flex; */
}
div.hours {
  /* display: flex; */
}
img {
  display: flex;
  width: 50%;
  height: auto;
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
