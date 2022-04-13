<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'card' }">Card</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>



<style>
html {
  font-size: 18px;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  /* font-style: oblique ; */
  color: #8C1184;
}
body {
  display: flex;
  /* flex-direction: column; */
  background-color: #AC8A6D;
  justify-content: center;
  height: 50vh;
}
div#app > div#nav {
  display: flex;
  flex-direction: row;
  font-family: monospace;
  font-size: 16px;
  padding-bottom: 1rem;
}
div#app > div#nav > #home {
  display: flex;
}
div#app > div#nav > #card {
  display: flex;
}
div#app > div#nav > #logout {
  display: flex;
}

/* @media (min-width: 1024px) {
  #body-grid {
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
      "header header header nav"
      "main main main main"
      "footer footer footer footer";
    column-gap: 35px;
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

/* Color Theme Swatches in Hex */
.color-theme_DSC_3862-1-hex { color: #888888; }
.color-theme_DSC_3862-2-hex { color: #CDA787; }
.color-theme_DSC_3862-3-hex { color: #FBF2EB; }
.color-theme_DSC_3862-4-hex { color: #AC8A6D; }
.color-theme_DSC_3862-5-hex { color: #E6CCB6; }

/* Color Theme Swatches in RGBA */
.color-theme_DSC_3862-1-rgba { color: rgba(216, 198, 175, 1); }
.color-theme_DSC_3862-2-rgba { color: rgba(205, 167, 135, 1); }
.color-theme_DSC_3862-3-rgba { color: rgba(251, 242, 235, 1); }
.color-theme_DSC_3862-4-rgba { color: rgba(172, 138, 109, 1); }
.color-theme_DSC_3862-5-rgba { color: rgba(230, 204, 182, 1); }

/* Color Theme Swatches in HSLA */
.color-theme_DSC_3862-1-hsla { color: hsla(33, 34, 76, 1); }
.color-theme_DSC_3862-2-hsla { color: hsla(27, 41, 66, 1); }
.color-theme_DSC_3862-3-hsla { color: hsla(26, 66, 95, 1); }
.color-theme_DSC_3862-4-hsla { color: hsla(27, 27, 55, 1); }
.color-theme_DSC_3862-5-hsla { color: hsla(27, 48, 80, 1); }


</style>
