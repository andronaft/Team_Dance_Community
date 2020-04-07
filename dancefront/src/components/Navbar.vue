<template>
  <nav class="navbar">
      <router-link to="/">Главная</router-link>
      <router-link to="/">Новости</router-link>
      <router-link to="/">Расписание</router-link>
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
    }

    a {
    padding: 10px;
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
</style>
