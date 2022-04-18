<template>
    <div>
        <h1>Profile</h1>
        <h2>{{username}}</h2>

        <div class="preferences content">
            <form action="dropdown">
                <select name="categories" size="10" multiple="multiple">
                <option @click="addPreference(category)" v-for="category in categories" v-bind:key = "category.id" v-bind:value = "category">
                    {{category}}
                </option>
                </select>
            </form>
        </div>

        <div>
            <ul>
                <li v-for="cat in userCategories" :key="cat.id">{{cat}}</li>
            </ul>
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
        RestaurantService.getUserCategories(this.username)
        .then((r) => {
            this.userCategories = r.data;
        });
    },
    data() {
        return {
            categories: '',
            username: this.$store.state.user.username,
            userCategories: []
        }
    },
    methods: {
        addPreference(category) {
            RestaurantService.addCategories(this.$store.state.token, category)
            .then(() => {
                alert("category added");
                RestaurantService.getUserCategories(this.username)
                .then((r) => {
                    this.userCategories = r.data;
                });
            });
            
        }
    }
}
</script>

<style>

</style>