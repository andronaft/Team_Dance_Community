<template>
  <div id="app">
    <navbar/>
    <main class="main">
          <div v-if="isLoggedIn" class="alert alert-success" role="alert">You are logged in!</div>
         
  <!-- 123{{user}} -->
  <!-- <button @click="logout">Logout</button> -->
      <router-view/>
    </main>
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
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn},
    ...mapGetters({
      user: "user"
    }),
  },
  created() {
    console.log(this.$store.state)
    this.$axios.interceptors.response.use(undefined, function (err) {
      return new Promise(function (resolve, reject) {
        if (err.status === 500 && err.config && !err.config.__isRetryRequest) {
          this.$store.dispatch(logout)
        }
        throw err;
      });
    });
  },
  methods: {
    logout: function () {
        this.$store.dispatch('logout')
        .then(() => {
          this.$router.push('/login')
        })
      },
  }
}
</script>

<style lang="scss">
button:focus {outline:0;}


body {
  background: #213751;
  padding: 0;
  margin: 0;
}
main.main {
  margin-top: 60px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  overflow: auto;
  color: #fff;
}



  input[type=text],input[type=email], input[type=firstname], input[type=password]{
  width: 100%;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: white;
  background-repeat: no-repeat;
  padding: 12px 20px 12px 20px;
}


.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active до версии 2.1.8 */ {
  opacity: 0;
}
</style>
