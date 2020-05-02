<template>
  <div class="findUser">
  <input type="text" v-model="inputData" @input="getData()" placeholder="Поиск по всем полям...">
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
                              this.$axios.get('/api/v1/admin/users/findBySomething/',{
              params: {
                "something": this.inputData
              }
            },{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.data = response.data;
    this.$emit('clicked', this.data )

    console.log("response",response);
   
  })
  .catch( (error) => {
    console.log(error);
  });
      },
      getData() {
          this.getByFirstName()
          
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
.findUser {
    margin-bottom: 10px;
}
</style>
