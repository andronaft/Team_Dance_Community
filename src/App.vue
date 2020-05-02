<template>
  <div id="app">
    <navbar/>
    <main class="main">
          <!-- <div v-if="isLoggedIn" class="alert alert-success" role="alert">You are logged in!</div> -->
         
  <!-- 123{{user}} -->
  <!-- <button @click="logout">Logout</button> -->
     <router-view :key="$route.fullPath"></router-view>

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
    this.$axios.interceptors.response.use((response) => {
    if(response.status === 403) {
        //  alert("You are not authorized");
    }
    return response;
}, (error) => {
    if (error.response && error.response.data) {
      if(error.response.status == 403) {
         this.$store.dispatch('logout')
      }
      console.log("RESPONSE ERR",error.response.status)
        return Promise.reject(error.response.data);
    }
    return Promise.reject(error.message);
});
    // this.$axios.interceptors.response.use(undefined,  (err) => {
    //   return new Promise( (resolve, reject) => {

    //     console.log("ISTOKENALIVE", err.status)
    //     if (err.status === 500 && err.config && !err.config.__isRetryRequest) {
    //       this.$store.dispatch(logout)
    //     }
        
    //     throw err;
    //   });
    // });
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

html, body, #app {
  height: 100%;
  margin: 0;
}
body {
  background: var(--color-primary-dark);
  padding: 0;
  margin: 0;
}
main.main {
  margin-top: 60px;
}
#app {
  height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  overflow: auto;
  color: var(--color-white);
}



  input[type=text],input[type=email], input[type=firstname], input[type=password]{
  width: 100%;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: var(--color-white);
  background-repeat: no-repeat;
  padding: 12px 20px 12px 20px;
}


.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active до версии 2.1.8 */ {
  opacity: 0;
}

.msg {
  margin-top: 20px;
  padding: 20px;
  background-color: var(--color-main);
  &.err {
    background-color: var(--color-err);
  }
}

table.admin {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
    color: #222;
    td,
th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
}

table.alltables {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
    color: #222;
    td,
th {
    border: 1px solid  var(--color-primary);
    text-align: left;
    padding: 8px;
}
tr {
  background-color:  var(--color-main);
}
$color: #FF9F1C;
tr:nth-child(even) {
    background-color: darken( $color, 10% );
}
}


</style>
