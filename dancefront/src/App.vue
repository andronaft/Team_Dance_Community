<template>
  <div id="app">
    <navbar/>
    <div v-if="isLoggedIn" class="alert alert-success" role="alert">You are logged in!</div>
    <router-view/>
  </div>
</template>

<script>
// @ is an alias to /src
import Navbar from '@/components/Navbar.vue'
import { mapGetters } from "vuex";

export default {
  name: 'Home',
  components: {
    Navbar
  },
  computed: {
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn}
  }
}
</script>

<style lang="scss">
body {
  background: #213751;
  padding: 0;
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;

  color: #fff;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
