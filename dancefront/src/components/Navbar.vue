<template>
  <nav class="navbar">
      <div class="navbar__left"  v-click-outside="hideNav">
        <button class="burger" @click="toggleNav">
      <font-awesome-icon icon="hamburger" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
    </button>
     <div class="nav" :class="{active : isActive}" >
      <router-link v-on:click.native="clickNav" to="/">Главная</router-link>
      <router-link v-on:click.native="clickNav" to="/">Новости</router-link>
      <router-link v-on:click.native="clickNav" to="/">Расписание</router-link>
     </div>
      
      </div>
      <div class="navbar__center">
        <div class="logo">
          LOGO
        </div>
      </div>
      <div class="navbar__right">
          <!-- <router-link to="/login">Авторизация</router-link> -->
              <Login/>
      </div>
      
  </nav>
</template>

<script>

import Login from '@/components/Login.vue'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  components: {
    Login
  },
  data() {
    return {
    isActive: false
    }
  },

  methods: {
    clickNav() {
      if(this.isMobile()) {
          this.toggleNav()
      }
      
    },
    hideNav() {
      console.log(this.isMobile())
      if(this.isMobile()) {
          this.isActive = false
      }
    },
    toggleNav() {
      console.log("toggle nav")
      this.isActive = !this.isActive
    },

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.burger {
  text-align: left;
  position: relative;
  display: flex;
  left: 0;
  justify-items: flex-start;
  align-items: center;
  @include lg {
    display: none;
  }
}

.navbar {
  z-index: 999;
  height: 60px;
  position: fixed;
  top: 0;
  width: 100%;
  background: var(--color-main);

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
   @include lg {
      display: grid;
      grid-template-columns: 1fr 1fr 1fr;
    }

  &__left {
     display: flex;
     .nav {
       display: none;
       @include lg {
         position: relative;
         display: flex;
       }
       a {
         transition: all .4s ease-in-out;
         &:hover {
         background-color: var(--color-primary-dark);
       }

       }
       &.active {
      width: 100%;
      background: #11647E;
      position: absolute;
      top:60px;
      display: grid;
    }
     }

    
  }
  &__center {
    text-align: center;
  }
  &__right {
    display: flex;
    justify-content: flex-end;
    text-align: right;
  }
}

a {
  padding: 20px;
  text-decoration: none;
  font-weight: bold;
  color: #fff;

  &.router-link-exact-active {
    color: #fff;
  }
}

</style>
