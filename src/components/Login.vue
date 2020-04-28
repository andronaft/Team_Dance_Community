<template>
<div class="login_navbar" v-click-outside="hideProfile">
    <div v-if="!isLoggedIn">
        <button class="profile" @click="toggleProfile">
            <font-awesome-icon icon="user" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
        </button>
        <transition name="fade">
            <div class="profile_dialog" :class="{active: isActive}" v-if="isActive">
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
                    <router-link to="/registration" @click.native="toggleProfile">Регистрация</router-link>
                </form>
            </div>
        </transition>
    </div>
    <div class="" v-else>

        <button class="profile" @click="toggleProfile">
            <font-awesome-icon icon="user" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
        </button>
        <transition name="fade">
        <div class="profile_dialog" :class="{active: isActive}" v-if="isActive">
            <div class="profile_body">
                <div class="logo">
                       <img alt="Vue logo" src="@/assets/logo.png">
                </div>
                <div class="username">
                    <router-link :to="{ name: 'Profile', params: { username: store_username }}">{{store_username}}
                    </router-link>
                </div>
                <div class="logout">
                    <button class="logoutBtn" @click="logout">Logout</button>
                </div>
                <div class="logout">
                    <router-link class="logoutBtn" @click.native="toggleProfile" to="/admin">Admin</router-link>
                </div>
            </div>
        </div>
        </transition>
    </div>
</div>
</template>

<script>
import store from "../store";
import { mapGetters } from "vuex";


export default {
  name: 'LoginComponent',

  data: () => ({
    username: 'testuser',
    password: '13371337',
    isActive: false
  }),
  computed: {
    isLoggedIn : function(){ return this.$store.getters.isLoggedIn},
    store_username: function(){ return this.$store.getters.user.username},
  },
  created() {
      console.log("State",this.$store.state.user)
  },
  methods: {
      logMeIn() {
          console.log("Ligmein")
          let username = this.username;
          let password = this.password
          this.$store.dispatch('login', { username, password })
       .then(() => this.$router.push('/'))
       .catch(err => console.log(err))
      },
      toggleProfile() {
      console.log("toogle profile")
      this.isActive = !this.isActive
    },
     hideProfile() {
      console.log("hide profile")
      if(this.isMobile()) {
          this.isActive = false
      }
    },
     logout: function () {
        this.$store.dispatch('logout')
        .then(() => {
          this.$router.push('/login')
        })
      },
  },
   
}
</script>

<style scoped lang="scss">

.login_navbar {
    display: flex;
    justify-content: center;
    align-items: center;
}
   .profile_dialog {
       margin-top: 15px;
  background-color: #fff;
  color: var(--color-black);
  text-align: left;
  position: absolute;
    left: 0;
    width: 100%;
    display: none;

    .profile_body {
        padding: 10px;
        .logo {
            display: flex;
            justify-content: center;
            align-items: center;
            img {
                width: 100px;
            }
        }
        .username {
            a {
                color: var(--color-dark);
                cursor: pointer;
                text-decoration: none;
            }
        }
    }
    &.active {
        display: block;
    }
    .login {
        padding: 20px;
    }
    @include lg { 

        left: auto;
        right: 0;
        width: 200px;
    }
}

.loginBtn, .logoutBtn {
  width: 100%;
  margin-top: 10px;
  background: #000;
  color: #fff;
  border:none;
  cursor: pointer;
  padding: 15px;
  box-sizing: border-box;
  display: block;
  text-align: center;
  text-decoration: none;
  font-size: 11px;
}

.profile {
    cursor: pointer;
    margin-right: 20px;
    text-align: left;
    position: relative;
    display: flex;
    right: 0;
    justify-items: flex-start;
    align-items: center;
    -webkit-appearance: none;
border-radius: 0;
background: none;
border: none;
}
</style>