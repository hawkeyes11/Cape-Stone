<template>
  <div class="content">
    <h2>Profile</h2>
    <span>{{ username }}</span>

    <div class="preferences">
      <form action="dropdown">
        <select name="categories" size="20" multiple="multiple">
          <option
            @click="addPreference(category)"
            v-for="category in categories"
            v-bind:key="category.id"
            v-bind:value="category"
          >
            {{ category }}
          </option>
        </select>
      </form>
    </div>

    <div>
      <ul>
        <li v-for="cat in userCategories" :key="cat.id">{{ cat }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import RestaurantService from "../services/RestaurantService.js";

export default {
  created() {
    RestaurantService.getCategories().then((r) => {
      this.categories = r.data;
    });
    RestaurantService.getUserCategories(this.username).then((r) => {
      this.userCategories = r.data;
    });
  },
  data() {
    return {
      categories: "",
      username: this.$store.state.user.username,
      userCategories: [],
    };
  },
  methods: {
    addPreference(category) {
      RestaurantService.addCategories(this.$store.state.token, category).then(
        () => {
          alert("category added");
          RestaurantService.getUserCategories(this.username).then((r) => {
            this.userCategories = r.data;
          });
        }
      );
    },
  },
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
