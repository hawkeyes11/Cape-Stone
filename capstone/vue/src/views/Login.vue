<template>
  <div id="login" class="text-center">
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
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
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


div.login {
  
  align-content: center;
}
form {
  display: flex;
  flex-direction: column;
  
  background-color: aqua;
}
label {
  display: flex;
  padding: 10px;
}
label.username {
  display: flex;
  padding: 0px 0px 5px 0px;
}
input#username {
  display: flex;
  margin: 5px
}
label.password {
  display: flex;
  flex-direction: row;
}
input#password {
  display: flex;
  margin: 5px
}
#register {
  display: flex;
  flex-direction: row;
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
