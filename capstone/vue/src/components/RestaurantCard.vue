<template>
  <div>
    <h2 class="name">name: {{ restaurant.name }}</h2>
    <p class="address">
      address: {{ restaurant.display_address[0] }}
      {{ restaurant.display_address[1] }}
    </p>
    <a :href='"tel:" + restaurant.display_phone' class="phone">Call to Order: {{ restaurant.display_phone }}</a>


 <!-- need to round off number -->
    <p class="distance">miles away: {{ restaurant.distance }}</p>

    <p>{{restaurant.isClosed ? 'Closed' : 'Open now' }}</p>

<!-- need to display stars not number -->
    <div>rating: {{ restaurant.rating }} amount of reviews:
    {{ restaurant.reviewCount }}</div>


    <img :src="restaurant.url" />

    <p v-for="t in restaurant.transactions" :key="t.id">{{ t }}</p>
    <p v-for="c in restaurant.categories" :key="c.id">{{ c }}</p>
  </div>
</template>

<script>
// import restaurantService from '../services/RestaurantService.js'

export default {
  name: "restaurant-card",
  props: ["restaurant"],
};
</script>

<style>
</style>