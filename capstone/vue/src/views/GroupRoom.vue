<template>
  <div class="content">
    <restaurant-card
      :hosting="groupId"
      :restaurant="card"
      v-for="card in restaurantList"
      :key="card.id"
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
  created() {
    restaurantService.getGroupRestaurants(parseInt(this.$route.params.groupId))
    .then((r) => {
      this.restaurantList = r.data;
    })
  },
  data(){
      return {
          restaurantList: [],
          groupId: this.$route.params.groupId,
          zip: "",
          
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