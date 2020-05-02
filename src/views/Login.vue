<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Автоизация</h3>
        <div class="login-form">
          <form class="form" @submit.prevent="logMeIn">
            <label>Логин</label>
            <div>
            <input required v-model="username" type="text" placeholder="Логин" />
            </div>
            <label>Пароль</label>
            <div>
            <input required v-model="password" type="password" placeholder="Пароль" />
            </div>
            <button class="loginBtn" type="submit">Login</button>
          </form>

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
    username: '',
    password: ''
  }),
  methods: {
      logMeIn() {
          console.log("Ligmein")
          let username = this.username;
          let password = this.password
          this.$store.dispatch('login', { username, password })
       .then(() => this.$router.push('/'))
       .catch(err => console.log(err))
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
</style>