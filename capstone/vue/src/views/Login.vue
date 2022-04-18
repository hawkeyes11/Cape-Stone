<template>
  <div id="login" class="text-center content">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
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
      <button type="submit">Sign in</button>
      <router-link :to="{ name: 'register' }">Need an account? Create a profile.</router-link>
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
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 1rem auto;
}
form.form-signin {
  display: flex;
  flex-direction: column;
  background-color: #0aa605;
  width: 75%;
  border-radius: 15px;
  margin: 10px;
  padding: 1%;
  height: auto;
  width: 50%;
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
  font-size: 16px;
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
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  display: flex;
  width: 50%;
  
}
button {
  width: 75%;
  justify-content: center;
  align-content: center;
  margin-top: 10px;
  background-image: radial-gradient(#FF0821, #FF5465);
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 2%;
  font-size: 18px;
}

/* @media (min-width: 1024px) {
  #body-grid {
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
      "header header header nav"
      "main main main main"
      "footer footer footer footer";
    column-gap: 15px;
  }
}
@media (max-width: 1024px) {
  #body-grid {
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas:
      "header header header nav"
      "main main main main"
      "footer footer footer footer";
    column-gap: 35px;
  }
}

@media (max-width: 450px) {
#main-grid {
    grid-template-columns: 1fr;
    grid-template-areas:
      "header"
      "main"
      "footer";
  } */


</style>
