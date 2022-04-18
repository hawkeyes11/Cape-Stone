<template>
<div class="homepage content">
    <h2>Welcome to APP NAME</h2>
    <div class="options" >
        <button class="custom-btn find-restaurant"><span>Find a Restaurant</span> <!-- redirects to Restaurant Cards -->
        </button> 
        <!-- Figure out the social networking/matchmaking elements -->
        <button class="custom-btn invite"><span>Saved Restaurants</span>    <!-- redirects to Friends List ?? -->
        </button>
        <button class="custom-btn logout"><span>Logout</span> <!-- redirects back to login page -->
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
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #0AA605;
  padding-bottom: 1em;
  height: 500px;
}
.custom-btn {
  width: 100%;
  height: 75px;
  background-color: #F25C05;
  color: #000;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  /* background: transparent; */
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
   7px 7px 20px 0px rgba(0,0,0,.1),
   4px 4px 5px 0px rgba(0,0,0,.1);
  outline: none;
 
} 
button {
  display: flex;
  background: #b621fe;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 750px;
  height: 100%;
  text-align: middle;
}

button:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image:linear-gradient(#F21616, white) ;
  border-radius: 5px;
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
    7px 7px 20px 0px rgba(0,0,0,.1),
   4px 4px 5px 0px rgba(0,0,0,.1);
  transition: all 0.3s ease;
}

button:hover {
  color: #fff;
}
button:hover:after {
  left: 0;
  width: 100%;
}
button:active {
  top: 2px;
}
button.logout {
  background-color: cadetblue;
  width: 25%;
}


</style>
