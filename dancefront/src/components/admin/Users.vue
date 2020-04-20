<template>
  <div class="users">
    <h1>Пользователи</h1>
    <table>
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Email</th>
    <th>Status</th>
    <th>Roles</th>
  </tr>
  <tr v-for="user in allUsers" v-bind:key="user.id">
    <td>{{user.id}}</td>
    <td>{{user.username}}</td>
    <td>{{user.firstname}}</td>
    <td>{{user.lastname}}</td>
    <td>{{user.email}}</td>
    <td>{{user.status}}</td>
    <td>{{user.roles}}</td>
  </tr>


</table>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
        allUsers: []
    }
  },
  created() {
      this.getAllUsers()
  },
  methods: {
      getAllUsers() {
                    this.$axios.get('/api/v1/admin/users/',{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.allUsers = response.data;
    console.log("response",response);
   
  })
  .catch( (error) => {
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
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
  color: #222;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
