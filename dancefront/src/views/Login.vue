<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Автоизация</h3>
        <div class="login-form">
          <form class="login" @submit.prevent="logMeIn">
            <label>Username</label>
            <div>
            <input required v-model="username" type="text" placeholder="Name" />
            </div>
            <label>Password</label>
            <div>
            <input required v-model="password" type="password" placeholder="Password" />
            </div>
            <button class="loginBtn" type="submit">Login</button>
          </form>

          <button @click="logout">logout</button>
        </div>
      </main>
    </div>
  </div>
</template>

<script>

import store from "../store";


import { VueperSlides, VueperSlide } from 'vueperslides'

import Timetable from '@/components/Timetable.vue'

// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: { VueperSlides, VueperSlide, Timetable },
  data: () => ({
    username: 'testuser',
    password: '13371337'
  }),
  methods: {
    logout: function () {
        this.$store.dispatch('logout')
        .then(() => {
          this.$router.push('/login')
        })
      },
      logMeIn() {
          console.log("Ligmein")
          let username = this.username;
          let password = this.password
          this.$store.dispatch('login', { username, password })
       .then(() => this.$router.push('/'))
       .catch(err => console.log(err))

//           this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/auth/login', {
//             "username":"testuser",
//             "password":"13371337"
//           },{
//     headers: { "Access-Control-Allow-Origin": "*" }
// }).then( (response) => {
//     console.log("response",response);
//     store.dispatch("fetchUser", response.data);
//   })
//   .catch( (error) => {
//     console.log(error);
//   });
      }
  }
}
</script>


<style lang="scss" scoped>
  .main {
    display: grid;
    padding: 10px;
    grid-template-columns: 1fr;
    @include lg {
      grid-template-columns: 1fr;
      width: 400px;
      margin: 0 auto;
    }
  }




.loginBtn {
  width: 100%;
  margin-top: 10px;
  background: #000;
  color: #fff;
  border:none;
  cursor: pointer;
  padding: 15px;
}
</style>