<template>
  <div>
    <div class="homepage-welcome">
      <h2>Welcome back {{ username }}!</h2>
    </div>
    <div class="options">
      <h3>Hungry?</h3>
      <button @click="$router.push('/meet')" class="custom-btn invite">
        <span>Find Food for You and Your Friends </span>
        <!-- redirects to Friends List ?? -->
      </button>
      <button @click="$router.push('/card')" class="custom-btn find-restaurant">
        <span>Find Food for You</span>
        <!-- redirects to Restaurant Cards -->
      </button>
      <!-- Figure out the social networking/matchmaking elements -->
      <button @click="showInput = !showInput" class="custom-btn final">
        <span> Go to Group List </span>
      </button>
      <div v-if="showInput">
        <label for="groupIdInput">Enter id of your group</label>
        <input v-model="groupId" id="groupIdInput" />
        <button
          class="custom-btn final"
          @click="$router.push({ name: 'groupLisp', params: { groupId } })"
        >
          Go to group finalist
        </button>
      </div>
      <!-- <button @click="$router.push('/')" class="custom-btn logout">
        <span>Logout</span>
      </button> -->
    </div>
  </div>
</template>

<script>
// import authService from "../services/AuthService";

export default {
  name: "home",
  data() {
    return {
      username: this.$store.state.user.username,
      showInput: false,
      groupId: "",
    };
  },
};
</script>

<style scoped>
.homepage-welcome {
  display: flex;
  justify-content: left;
  margin-left: 10%;
}

.options {
  display: flex;
  /* font-family: 'Yantramanav', sans-serif; */
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #fffddb;
  border: 2px solid #f2b705;
  width: 75%;
  height: 75%;
  border-radius: 15px;
  margin: auto;
  padding: 1%;
  height: auto;
  filter: drop-shadow(3px 3px 10px #000000);
}
button {
  display: flex;
  border: none;
  z-index: 1;
  align-content: center;
  margin: 20px;
  width: 75%;
  height: 100%;
  text-align: middle;
}
button span {
  display: flex;
  justify-content: center;
  font-family: "Yantramanav", sans-serif;
  font-size: 1.25em;
}
.custom-btn {
  display: flex;
  flex-shrink: 1;
  height: 75px;
  background-color: #f25c05;
  color: #000;
  border-radius: 5px;
  padding: 10px 25px 10px 25px;
  font-family: "Lato", sans-serif;
  font-weight: 500;
  /* background: transparent; */
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}
button:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image: linear-gradient(to left, #f25c05, #f2b705);
  border-radius: 5px;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
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
  background-color: #f21616;
  width: 25%;
  height: 50%;
}
button.logout:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
  background-image: linear-gradient(to left, black, #f21616);
  border-radius: 5px;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

button.logout:hover {
  color: #fff;
}
button.logout:hover:after {
  left: 0;
  width: 100%;
}
button.logout:active {
  top: 2px;
}
</style>
 © 2020 Josh Goebel
Powered by Node.js, Express, Highlight.js, and Love.