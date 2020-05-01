<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Профиль</h3>
        <div class="profile"> <button @click="edit()">Edit</button>
            {{this.$route.params.username}}
            {{userData}}
            <!-- {{this.$axios.defaults.headers.common['Authorization']}} -->
        </div>
      </main>
    </div>

    <modal name="editUser" @before-open="beforeOpen"  :height="'auto'">
  <form @submit.prevent="editUserBtn" class="editUser">
            <label for="name">Телефон</label>
            <div>
              <input id="name" type="text" v-model="userData.mobile" required autofocus>
            </div>
            <label for="information">Социальные</label>
            <div>
              <input id="information" type="text" v-model="userData.social" required>
            </div>
            <label for="contact">О себе</label>
            <div>
              <input id="contact" type="text" v-model="userData.about" required>
            </div>
            
            <div>
              <button class="editUser" type="submit">Обновить</button>
            </div>
          </form>
</modal>

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
      editUserBtn() {
this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/users/profile/updateProfile/', {
                       "social": this.userData.social,
                        "mobile": this.userData.mobile,
                        "about": this.userData.about
                }, {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    console.log("response", response);
                    this.userData = response.data
                })
                .catch((error) => {
                    console.log(error);
                });
                this.$modal.hide('editUser');
    },
    beforeOpen (event) {
    },
    edit() {
      this.$modal.show('editUser', this.userData);
    },
        getProfile() {
            this.$axios.post('https://team-dance-community.herokuapp.com/api/v1/users/profile/getProfile/', {
                    "username": this.$route.params.username
                }, {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    console.log("response", response);
                    this.userData = response.data
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


.editUser {
    margin: 20px;
}
</style>