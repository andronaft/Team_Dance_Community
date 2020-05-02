<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Регистрация</h3>
        <div class="login-form">
          <form @submit.prevent="register" class="form">
            <label for="name" :class="{green: usernamestatus == false, red: usernamestatus == true}">Логин</label>
            <div>
              <input id="name" type="text" v-model="username" required autofocus v-on:blur="checkUsername" placeholder="Логин">
            </div>
            <label for="name">Имя</label>
            <div>
              <input id="name" type="text" v-model="firstName" required placeholder="Имя">
            </div>
            <label for="password-confirm">Фамилия</label>
            <div>
              <input id="firstName" type="text" v-model="lastName" required placeholder="Фамилия">
            </div>
            <label for="email" :class="{green: emailstatus == false, red: emailstatus == true}">E-Mail</label>
            <div>
              <input id="email" type="email" v-model="email" required  v-on:blur="checkEmail" placeholder="E-Mail">
            </div>
            <label for="password">Пароль</label>
            <div>
              <input id="password" type="password" v-model="password" required placeholder="Пароль">
            </div>
             <div class="checkbox">
    <label><input type="checkbox" required> Пользовательское соглашение</label>
  </div>
            <div>
              <button class="registeBtn" type="submit">Регистрация</button>
            </div>
          </form>

          <!-- <button @click="logout">logout</button> -->
        </div>
      </main>
    </div>
  </div>
</template>

<script>

import store from "../store";


import {
  VueperSlides,
  VueperSlide
} from 'vueperslides'

import Timetable from '@/components/Timetable.vue'

// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    VueperSlides,
    VueperSlide,
    Timetable
  },
  data: () => ({
    username: '',
    email: "",
    password: "",
    firstName: "",
    lastName: '',
    is_admin: null,
    usernamestatus: null,
    emailstatus: null

  }),
  methods: {
    checkUsername() {
      this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/auth/checkUsername/', {
          "username": this.username
        }, {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {
          console.log("response", response);
          this.usernamestatus = response.data.isExist
        })
        .catch((error) => {
          console.log(error);
        });
    },
    checkEmail() {
      this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/auth/checkEmail/', {
          "email": this.email
        }, {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {
          console.log("response", response);
          this.emailstatus = response.data.isExist
        })
        .catch((error) => {
          console.log(error);
        });
    },
    logout: function () {
      this.$store.dispatch('logout')
        .then(() => {
          this.$router.push('/login')
        })
    },
    register() {
      console.log("Ligmein")
      let data = {
        username: this.username,
        email: this.email,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName
      }
      this.$store.dispatch('register', data)
        .then(() => alert("ok"))
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

label {
  &.green {
    color: var(--color-successful);
  }
  &.red {
    color: var(--color-err);
  }
}
</style>