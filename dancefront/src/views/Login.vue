<template>
  <div class="home">

      <div class="main">
       
        <main>
          <h3>Автоизация</h3>
           <div class="login-form">
 <form class="login" @submit.prevent="logMeIn">
     <h1>Sign in</h1>
     <label>Username</label>
     <input required v-model="username" type="username" placeholder="Name"/>
     <label>Password</label>
     <input required v-model="password" type="password" placeholder="Password"/>
     <hr/>
     <button type="submit">Login</button>
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

  input[type=text] {
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