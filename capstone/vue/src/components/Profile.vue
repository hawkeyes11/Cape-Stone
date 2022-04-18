<template>
    <div>
        <h1>Profile</h1>

        <div class="preferences content">
            <form action="dropdown">
            <option v-for="category in categories" v-bind:key = "category.id" v-bind:value = "category">
            </option>
            </form>
            <!-- Display ist of categories as a dropdown -->

        </div>
        



    </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService.js'

export default {
    created() {
        RestaurantService.getCategories().then((r) => {
            this.categories = r.data
        });
    },
    data() {
        return {
            categories: ''
        }
    }
}
</script>

<style>

</style>