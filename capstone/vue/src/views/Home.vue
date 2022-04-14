<template>
<div class="homepage">
    <h2>Welcome to APP NAME</h2>
    <div class="options" >
        <button id="find-restaurant"><span>Find a Restaurant</span> <!-- redirects to Restaurant Cards -->
        </button> 
        <!-- Figure out the social networking/matchmaking elements -->
        <button id="invite"><span>Saved Restaurants</span>    <!-- redirects to Friends List ?? -->
        </button> 
        <button id="logout"><span>Logout</span> <!-- redirects back to login page -->
        </button> 
    </div>
</div>
</template>

<script>
// import authService from "../services/AuthService";

export default {
  name: "home"
};
</script>

<style scoped>
.homepage {
  background-color: azure;
  width: 100%;
}


</style>
