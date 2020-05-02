<template>
  <nav class="navbar" v-if="renderComponent">
      <div class="navbar__left"  v-click-outside="hideNav">
        <button class="burger" @click="toggleNav">
      <font-awesome-icon icon="hamburger" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
    </button>
     <div class="nav" :class="{active : isActive}" >
      <router-link v-on:click.native="clickNav" class="link" to="/">Главная</router-link>
      <router-link v-on:click.native="clickNav" class="link" to="/">Новости</router-link>
      <router-link v-on:click.native="clickNav" class="link" to="/branches">Бранчи
      <div class="dropdown">
          <router-link v-for="branch in branches" :key="branch.id" v-on:click.native="clickNav" class="link" :to="{ path: 'branches', query: { id: branch.id }}" >{{branch.name}}</router-link>
      </div>
      </router-link>
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
    isActive: false,
    branches: '',
    renderComponent: true
    }
  },
  created() {
    this.getBranches()
  },
  methods: {
    getBranches() {
      this.$axios.get('/api/v1/branch/',{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.branches = response.data;
    console.log("response",response);
   
  })
  .catch( (error) => {
    console.log(error);
  });
    },
    forceRerender() {
  // Remove my-component from the DOM
  this.renderComponent = false;

  // If you like promises better you can
  // also use nextTick this way
  this.$nextTick().then(() => {
    // Add the component back in
    this.renderComponent = true;
  });
},
    clickNav() {
      console.log("clicked nav")
      this.forceRerender();
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
  box-shadow: 0 2px 2px -2px var(--color-black);

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
        height: 60px;
        display: block;
        box-sizing: border-box;
        transition: all .4s ease-in-out;
        position: relative;

        &:hover {
          background-color: var(--color-primary-dark);

        }

        .dropdown {
          position: absolute;
          left: 0;
          top: 100%;
          display: none;
          background: var(--color-main);
          flex-direction: column;
        }

      }

      &.active {
        width: 100%;
        background: #11647E;
        position: absolute;
        top: 60px;
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


a.link {
  &:hover {
    .dropdown {
      display: flex;
    }
  }

  .dropdown {
    display: none;
  }
}
</style>
