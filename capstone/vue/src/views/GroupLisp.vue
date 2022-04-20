<template>
  <div>
      <restaurant-card v-for="rest in restList" :key="rest.id" :restaurant="rest"/>
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService"
import RestaurantCard from "../components/RestaurantCard.vue"

export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
            groupId: this.$route.params.groupId,
            restList: []
        }
    },
    created() {
        restaurantService.getGroupFinalist(this.groupId)
        .then((r) => {
            this.restList = r.data;
        });
    }

}
</script>

<style>

</style>