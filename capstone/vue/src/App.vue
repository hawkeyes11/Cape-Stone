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
  font-size: 16px;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}
body {
  display: flex;
  background-color: whitesmoke;
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




</style>
