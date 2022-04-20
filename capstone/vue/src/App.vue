<template>
  <div class="app">
    <div v-if="!isLoading">
      <header class="header-logo-name">
        <img class="logo" src="@/assets/Logo_BLUE.png" href="/" />
        <h1 class="title">Meet2Eat</h1>
        <img class="avatar" v-if="$store.state.user.username != undefined" :src="avatarImg"/>
      </header>
      <nav v-if="$store.state.user.username != undefined" class="navbar">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'card' }">Search</router-link
        >&nbsp;|&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </nav>
      <router-view class="content" />
    </div>
    <div v-if="!isLoading">
    <footer class="footer">&copy; Team $Bling</footer>
    </div>
    <div v-if="isLoading">
      <img class="load-food" src="@/assets/Food_load.gif" />
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    loading() {
      setTimeout(() => {
        this.isLoading = false;
      }, 2500);
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
  computed: {
    avatarImg() {
      return "https://avatars.dicebear.com/api/pixel-art-neutral/" + this.$store.state.user.username + ".svg"
    }
    
  }
};
</script>

<style>
body {
  /* display: grid; */
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 2fr 1fr;
  grid-template-areas:
    "header header nav"
    "content content"
    "footer footer";
  column-gap: 5px;
}
html {
  font-size: 18px;
  font-family: "Yantramanav", sans-serif;
  color: #400c0c;
  background-color: rgba(98, 205, 217, 0.35);
}
header {
  display: flex;
  flex-direction: row;
  flex-grow: 1 2 3;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: baseline;
  margin-top: 0vh;
  grid-area: header;
  background-color: #fffddb;
  min-width: 100%;
}
h1 {
  display: flex;
}
.logo {
  position: relative;
  width: 12%;
  padding-top: 2%;
}
.title {
  display: flex;
  font-size: 4em;
  font-family: "Pacifico", cursive;
  margin: -10px;
  /* font-family: 'Permanent Marker', cursive; */
}
.avatar {
  display: flex;
  justify-content: flex-end;
  width: 5%;
  height: auto;
}
nav {
  display: flex;
  grid-area: nav;
  flex-wrap: wrap;
  justify-content: space-evenly;
  margin: 2% 0% 2% 0%;
}
.content {
  margin-bottom: 20%;
  grid-area: content;
  align-content: center;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
footer {
  display: flex;
  position: static;
  bottom: 0;
  grid-area: footer;
  justify-content: center;
  background-color: #fffddb;
  padding: 2%;
  height: auto;
  min-width: 100%;
}
nav.navbar-buttons {
  display: flex;
}
/* div.nav-buttons {
  text-decoration: none;
} */
img.load-food {
  position: absolute;
  align-content: center;
  height: 50%;;
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
