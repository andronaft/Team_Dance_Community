<template>
  <nav class="navbar">
    <button class="burger" @click="toogleNav">
      <font-awesome-icon icon="hamburger"  :style="{ color: 'white' }"></font-awesome-icon>
    </button>
      <div class="navbar__left" :class="{active: isActive}">
      <router-link v-on:click.native="clickNav" to="/">Главная</router-link>
      <router-link v-on:click.native="clickNav" to="/">Новости</router-link>
      <router-link v-on:click.native="clickNav" to="/">Расписание</router-link>
      
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
      this.toogleNav()
    },
    toogleNav() {
      console.log("toogle nav")
      this.isActive = !this.isActive
    },
    toggleProfile() {
      console.log("toogle profile")
    }
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
  padding: 20px;
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
  background: #11647E;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
   @include lg {
      display: grid;
      grid-template-columns: 1fr 1fr 1fr;
    }

  &__left {
    display: none;
    @include lg {
      display: flex;
    }

    &.active {
      width: 100%;
      background: #11647E;
      position: absolute;
      top:60px;
      display: grid;
    }
  }
  &__center {
    text-align: center;
  }
  &__right {
    text-align: right;
    padding: 20px;
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
