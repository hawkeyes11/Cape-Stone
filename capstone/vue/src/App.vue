<template>
  <div class="app">
    <div v-if="!isLoading">
      <header>
        <nav class="navbar">
            <div class="header-logo-name">
              <img class="logo" src="@/assets/logo.png" href="/" />
              <h1 class="navbar-text">APP TITLE</h1>
            </div>
          <div class="nav-buttons">
            <router-link v-bind:to="{ name: 'home' }">Home</router-link
            >&nbsp;|&nbsp;
            <router-link v-bind:to="{ name: 'card' }">Card</router-link
            >&nbsp;|&nbsp;
            <router-link
              v-bind:to="{ name: 'logout' }"
              v-if="$store.state.token != ''"
              >Logout</router-link
            >
          </div>
        </nav>
      </header>
      <router-view class="content" />
    </div>
    <div v-if="isLoading">
      <img src="@/assets/Food_load.gif" />
    </div>
    <footer class="footer">Bling, Bling</footer>
  </div>
</template>

<script>
export default {
  methods: {
    loading() {
      setTimeout(() => {
        this.isLoading = false;
      }, 1850);
    },
  },
  data() {
    return {
      isLoading: true,
    };
  },
  mounted() {
    this.loading();
  },
};
</script>

<style>
/* body{
display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 2fr 1fr;
  grid-template-areas:
    "header header header header"
    "content content content content"
    "footer footer footer footer";
  column-gap: 5px;
}  */
header {
  display: flex;
  grid-area: header;
  background-color: #62CDD9;
  padding: 7px;
}
.content {
  display: flex;
  grid-area: content;
  align-content: center;
}
footer {
  display: flex;
  grid-area: footer;
  justify-content: center;
}
html {
  font-size: 18px;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  /* font-style: oblique ; */
  text-decoration-color: #400C0C;
  margin-left: 5%;
  margin-top: 2%;
  margin-right: 5%;
}
body {
  background-color: #FFFDDB;
}
nav {
  margin-bottom: 2%;
}
.logo {
  width: auto;
  height: 50px;
}
h1 {
  text-decoration: none;
}
div.nav-buttons {
  text-decoration: none;
}
/* 
@media (min-width: 1024px) {
  .app {
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
      "header header . header"
      ". content content ."
      "footer footer footer footer";
    column-gap: 35px;
  }
} */
/*
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
  } 
*/
/* Color Theme Swatches in Hex */
.color-theme_DSC_3862-1-hex {
  color: #888888;
}
.color-theme_DSC_3862-2-hex {
  color: #cda787;
}
.color-theme_DSC_3862-3-hex {
  color: #fbf2eb;
}
.color-theme_DSC_3862-4-hex {
  color: #ac8a6d;
}
.color-theme_DSC_3862-5-hex {
  color: #e6ccb6;
}

/* Color Theme Swatches in RGBA */
.color-theme_DSC_3862-1-rgba {
  color: rgba(216, 198, 175, 1);
}
.color-theme_DSC_3862-2-rgba {
  color: rgba(205, 167, 135, 1);
}
.color-theme_DSC_3862-3-rgba {
  color: rgba(251, 242, 235, 1);
}
.color-theme_DSC_3862-4-rgba {
  color: rgba(172, 138, 109, 1);
}
.color-theme_DSC_3862-5-rgba {
  color: rgba(230, 204, 182, 1);
}

/* Color Theme Swatches in HSLA */
.color-theme_DSC_3862-1-hsla {
  color: hsla(33, 34, 76, 1);
}
.color-theme_DSC_3862-2-hsla {
  color: hsla(27, 41, 66, 1);
}
.color-theme_DSC_3862-3-hsla {
  color: hsla(26, 66, 95, 1);
}
.color-theme_DSC_3862-4-hsla {
  color: hsla(27, 27, 55, 1);
}
.color-theme_DSC_3862-5-hsla {
  color: hsla(27, 48, 80, 1);
}


</style>
