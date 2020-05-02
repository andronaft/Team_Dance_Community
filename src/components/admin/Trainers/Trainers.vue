<template>
  <div class="users">
    <h1>Тренера</h1>
    <table class="admin">
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Email</th>
    <th>Status</th>
    <th>Roles</th>
  </tr>
  <tr v-for="user in allTrainers" v-bind:key="user.id">
    <td>{{user.id}}</td>
    <td>{{user.username}}</td>
    <td>{{user.firstName}}</td>
    <td>{{user.lastName}}</td>
    <td>{{user.email}}</td>
    <td>{{user.status}}</td>
    <td>{{user.roles}}</td>
  </tr>


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
      allTrainers: [],
      user: []
    }
  },
  created() {
    this.getAllTrainers()
  },
  methods: {
    editUserBtn() {

    },
    beforeOpen (event) {
    },
    getAllTrainers() {
      this.$axios.get('/api/v1/admin/trainer/', {
          headers: {
            "Access-Control-Allow-Origin": "*"
          }
        }).then((response) => {
          this.allTrainers = response.data;
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
