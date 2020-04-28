<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Бранчи</h3>
        <div class="profile">
            {{this.$route.query}}
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
        trainings: ''
    }),
    created() {
        this.getTrainings()
    },
    methods: {
        getTrainings() {
            this.$axios.get('/api/v1/training/groupTraining/findByBranch',{
                params: {
                    branchId: this.$route.query.id
                }
            },{
    headers: { "Access-Control-Allow-Origin": "*" }
}).then( (response) => {
    this.trainings = response.data;
    console.log("response",response);
   
  })
  .catch( (error) => {
    console.log(error);
  });
        }
    }
}
</script>


<style lang="scss" scoped>
  .main {

  }





</style>