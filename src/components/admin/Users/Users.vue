<template>
  <div class="users">
    <h1>Пользователи</h1>
    <findUser @clicked="onClickChild" />
    <table class="admin">
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Email</th>
    <th>Status</th>
    <th>Roles</th>
    <th>Edit</th>
    <th>Set Trainer</th>
  </tr>
  <tr v-for="user in allUsers" v-bind:key="user.id">
    <td>{{user.id}}</td>
    <td>{{user.username}}</td>
    <td>{{user.firstName}}</td>
    <td>{{user.lastName}}</td>
    <td>{{user.email}}</td>
    <td>{{user.status}}</td>
    <td>{{user.roles}}</td>
    <td><button @click="edit(user)">Edit</button></td>
    <td><button @click="setTrainer(user)">Сделать тренером</button></td>

  </tr>

<modal name="editUser" @before-open="beforeOpen"  :height="'auto'">
  <form @submit.prevent="editUserBtn" class="editUser">
            <label for="name">Телефон</label>
            <div>
              <input id="name" type="text" v-model="user.mobile" required autofocus>
            </div>
            <label for="information">Социальные</label>
            <div>
              <input id="information" type="text" v-model="user.social" required>
            </div>
            <label for="contact">О себе</label>
            <div>
              <input id="contact" type="text" v-model="user.about" required>
            </div>
            <label for="location">Рейтинг</label>
            <div>
              <input id="location" type="text" v-model="user.rating" required>
            </div>
             <label for="location">Уровень</label>
            <div>
              <input id="location" type="text" v-model="user.level" required>
            </div>
             <label for="location">Статус</label>
            <div>
              <input id="location" type="text" v-model="user.status" required>
            </div>
            <div>
              <button class="editUser" type="submit">Редактировать Юзера</button>
            </div>
          </form>
</modal>
</table>
  </div>
</template>

<script>
import findUser from '@/components/admin/Users/findUser.vue'


export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  components: {
    findUser
  },
  data() {
    return {
      resultedUsers: [],
      allUsers: [],
      user: []
    }
  },
  created() {
    this.getAllUsers()
  },
  methods: {
    editUserBtn() {

    },
    beforeOpen (event) {
    },
    edit(user) {
      this.$axios.get('api/v1/admin/users/profile/getProfile/', {
              params: {
                "id": user.id
              }
            }, {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {
          this.user = response.data;
          this.$modal.show('editUser', this.user);

          console.log("response", response);

        })
        .catch((error) => {
          console.log(error);
        });
    },
    onClickChild(value) {
      console.log(value) // someValue
      this.allUsers = value
    },
    getAllUsers() {
      this.$axios.get('/api/v1/admin/users/', {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {
          this.allUsers = response.data;
          this.resultedUsers = response.data;
          console.log("response", response);

        })
        .catch((error) => {
          console.log(error);
        });
    },
    setTrainer(user) {
      this.$axios.get('/api/v1/admin/trainer/setRoleTrainer', {
        params: {
            "id": user.id
        }
        
      }, {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {

          console.log("response", response);

        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h1 {
    color: var(--color-black);

}


.editUser {
    margin: 20px;
}
</style>
