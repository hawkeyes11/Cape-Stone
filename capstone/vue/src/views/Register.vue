<template>
  <div class="register content">
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
        class="custom-btn btn btn-lg btn-primary btn-block"
        type="submit"
      >
        <span>Create Account</span>
      </button>
      <router-link :to="{ name: 'login' }"
        >Have a profile? Sign in.</router-link
      >
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
.content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0% 0% 20% 0%;
  width: 100%;
}
form.form-register {
  display: flex;
  flex-direction: column;
  background-color: #0aa605;
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
  font-family: monospace;
  font-size: 0.75rem;
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
  font-size: 0.75rem;
}
form#login {
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
  font-size: 1.25em;
}
.custom-btn {
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
</style>
