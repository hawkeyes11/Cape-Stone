<template>
  <div >
      <div v-for="i in images" :key="i.id">
        <img src="../assets/celebration-confetti.gif">
        <h1>Congrats Cleveland Cohort 19</h1>
        <p>You all did wonderful and have grown so much since day one. We are so happy to have worked with you all and wish you all the best in your future
          endeavors
        </p>
        <img src="../assets/celebration-confetti.gif">
      </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      images: 1000
    }
  },
};
</script>

<style scoped>

</style>
