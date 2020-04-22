<template>
  <div class="users">
    <h1>Пользователи</h1>
    <table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Information</th>
    <th>Contact</th>
    <th>Location</th>
    <th>Img Url</th>
    <th>Status</th>
    <th>Created</th>
    <th>Updated</th>
  </tr>
  <tr v-for="user in allBranches" v-bind:key="user.id">
    <td>{{user.id}}</td>
    <td>{{user.name}}</td>
    <td>{{user.information}}</td>
    <td>{{user.contact}}</td>
    <td>{{user.location}}</td>
    <td>{{user.imgUrl}}</td>
    <td>{{user.status}}</td>
    <td>{{date(user.created)}}</td>
    <td>{{date(user.updated)}}</td>

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
        allBranches: []
    }
  },
  computed: {
      
  },
  created() {
      this.getAllBranches()
  },
  methods: {
      date(timestamp) {
          return this.convertTimestamp(timestamp)
      },
      getAllBranches() {
                    this.$axios.get('/api/v1/admin/branch/',{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.allBranches = response.data;
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
