<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Профиль</h3>
        <div class="profile">
            {{this.$route.params.username}}
            {{this.$axios.defaults.headers.common['Authorization']}}
        </div>
      </main>
    </div>
  </div>
</template>

<script>

import store from "../store";


export default {
    name: 'Home',
    components: {},
    data: () => ({
        userData: ''
    }),
    created() {
        this.getProfile()
    },
    methods: {
        getProfile() {
            this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/users/profile/getProfile/', {
                    "username": this.$route.params.username
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




.loginBtn {
  width: 100%;
  margin-top: 10px;
  background: #000;
  color: #fff;
  border:none;
  cursor: pointer;
  padding: 15px;
}
</style>