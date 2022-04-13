<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <button
        @click="checkPassword"
        class="btn btn-lg btn-primary btn-block"
        type="submit"
      >
        Create Account
      </button>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
      validPassword: false,
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else if (!this.validPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Your password does not match the criteria";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
    checkPassword() {
      const validation = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
      if (this.user.password.match(validation)) {
        this.validPassword = true;
      } else {
        this.validPassword = false;
      }
    },
  },
};
</script>

<style scoped>
#register {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 35rem;
  justify-content: center;
  align-items: center;
  margin: 4rem auto;
}
form.form-register {
  display: flex;
  flex-direction: column;
  background-color: #fbf2eb;
  width: 750px;
  border-radius: 15px;
  padding: 2.5%;
  height: 100%;
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
  font-family: monospace;
}
label.confirmPassword {
  display: flex;
  margin: 2%;
}
input#confirmPassword {
  display: flex;
  margin-left: 2%;
  width: 50%;
  padding: 10px;
  margin-bottom: 5%;
  font-family: monospace;
}
form#login {
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
  display: flex;
  width: 50%;
}
button {
  width: 75%;
  justify-content: center;
  align-content: center;
  margin-top: 10px;
  background-image: radial-gradient(#ff0821, #ff5465);
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 2%;
  font-size: 18px;
}
</style>
