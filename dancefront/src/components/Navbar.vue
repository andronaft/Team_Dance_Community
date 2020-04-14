<template>
  <nav class="navbar">
      <div class="navbar__left">
        <router-link to="/">Главная</router-link>
      <router-link to="/">Новости</router-link>
      <router-link to="/">Расписание</router-link>
      <router-link to="/login">Авторизация</router-link>
      </div>
  </nav>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    nav {
        display: flex;
        background: #11647E;
        .navbar__left {
        display: flex;
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
