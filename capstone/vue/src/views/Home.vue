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
  font-size: 1.5em;
  font-weight: 400;
}
.custom-btn {
  height: 75px;
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
  outline: none;
  line-height: 42px;
  border: none;
  background: linear-gradient(0deg, #62cdd9, #60f0d6);
}
button:hover {
  color: #62cdd9;
  background: transparent;
  box-shadow: none;
}
button:before,
button:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  height: 2px;
  width: 0;
  background: #5c8fe6;
  box-shadow: -1px -1px 5px 0px #fff, 7px 7px 20px 0px #0003,
    4px 4px 5px 0px #0002;
  transition: 400ms ease all;
}
button:after {
  right: inherit;
  top: inherit;
  left: 0;
  bottom: 0;
}
button:hover:before,
button:hover:after {
  width: 100%;
  transition: 800ms ease all;
}
</style>
