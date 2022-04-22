<template>
  <div class="login content">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button class="custom-btn" type="submit"><span>Sign in</span></button>
      <router-link :to="{ name: 'register' }"
        >Need an account? Create a profile.</router-link
      >
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
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
  margin: 5% 0% 20% 0%;
  width: 100%;
}
.form-signin {
  display: flex;
  flex-direction: column;
  background-color: #fffddb;
  border: 2px solid #f2b705;
  width: 100%;
  border-radius: 15px;
  margin: 10px;
  padding: 1%;
  height: auto;
  width: 50%;
  filter: drop-shadow(3px 3px 10px #000000);
}
label {
  display: flex;
  padding: 10px;
  font-size: 24px;
}
label.username {
  display: flex;
  margin: 2%;
}
input#username {
  display: flex;
  margin-left: 2%;
  margin-bottom: 2%;
  width: 50%;
  padding: 10px;
  font-family: monospace;
  font-size: 0.75rem;
}
label.password {
  display: flex;
  margin: 2%;
}
input#password {
  display: flex;
  margin-left: 2%;
  margin-bottom: 2%;
  width: 50%;
  padding: 10px;
  margin-bottom: 5%;
  font-family: monospace;
}
form#register {
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
  display: flex;
  width: 50%;
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
