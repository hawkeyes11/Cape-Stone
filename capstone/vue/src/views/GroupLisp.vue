<template>
  <div>
      <group-card v-for="rest in restList" :key="rest.id" :restaurant="rest"/>
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService"
import GroupCard from "../components/GroupCard.vue"

export default {
    components: {
        GroupCard
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