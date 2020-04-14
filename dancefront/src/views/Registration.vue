<template>
  <div class="home">

      <div class="main">
       
        <main>
          <h3>Registration</h3>
           <div class="login-form">
 <form @submit.prevent="register">
     <label for="name">Username</label>
      <div>
          <input id="name" type="text" v-model="username" required autofocus>
      </div>
      <label for="name">Name</label>
      <div>
          <input id="name" type="text" v-model="firstName" required autofocus>
      </div>
       <label for="password-confirm">Фамилия</label>
      <div>
          <input id="firstName" type="text" v-model="lastName" required>
      </div>
      <label for="email" >E-Mail Address</label>
      <div>
          <input id="email" type="email" v-model="email" required>
      </div>
      <label for="password">Password</label>
      <div>
          <input id="password" type="password" v-model="password" required>
      </div>
      <div>
          <button type="submit">Register</button>
      </div>
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
        username: '',
        email : "",
        password : "",
        firstName : "",
        lastName: '',
        is_admin : null

  }),
  methods: {
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
    }
  }


  .latestnews {
    display: flex;
    flex-direction: column;
    .news__item {
      padding: 10px;
      display: flex;
    }
    a {
      color: #fff;
    }
  }

  .bottom {
    padding: 10px 0;
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
  .boxes {
    display: grid;
    grid-template-columns: 1fr 1fr;
    a {
      height: 100px;
      width: 150px;
    }
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
</style>