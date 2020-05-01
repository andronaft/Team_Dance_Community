<template>
  <div class="users">
  <input type="text" v-model="inputData" @input="getData()">
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
        inputData: '',
        data: []
    }
  },
  created() {
  },
  methods: {
      getByFirstName() {
                              this.$axios.get('/api/v1/admin/users/findByLastName/',{
              params: {
                "lastName":"surname"
              }
            },{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.data = response.data;
    console.log("response",response);
   
  })
  .catch( (error) => {
    console.log(error);
  });
      },
      getData() {
          this.getByFirstName()
          this.$emit('clicked', 'someValue')
//                     this.$axios.get('/api/v1/admin/users/',{
//     headers: { "Access-Control-Allow-Origin": "*" }
// }).then( (response) => {
//     this.allUsers = response.data;
//     console.log("response",response);
   
//   })
//   .catch( (error) => {
//     console.log(error);
//   });
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
