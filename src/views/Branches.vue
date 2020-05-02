<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Бранчи</h3>
        <div class="branches" v-if="this.$route.query.id">
            <!-- {{this.$route.query}} -->

              <h3>Групповые тренировки</h3>
  <div class="groupTrainings">
      <table class="alltables">
          <tr>
              <th>Название</th>
              <th>Время</th>
              <th>День</th>
          </tr>
          <tr v-for="training in trainings" :key="training.id">
              <td>{{training.name}}</td>
              <td>{{training.time}}</td>
              <td>{{training.weekDay}}</td>
          </tr>


      </table>
  </div>
              
          
        </div>
        <div v-else>
          {{branches}}
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
        trainings: '',
        branches: ''
      }),
      created() {
        this.getAllBranches()
        this.getTrainings()
      },
      methods: {
        getAllBranches() {
          this.$axios.get('/api/v1/branch/', {
              params: {
              }
            }, {
              headers: {
                "Access-Control-Allow-Origin": "*"
              }
            }).then((response) => {
              this.branches = response.data;
              console.log("response", response);

            })
            .catch((error) => {
              console.log(error);
            });
        },
        getTrainings() {
          this.$axios.get('/api/v1/training/groupTraining/findByBranch', {
              params: {
                branchId: this.$route.query.id
              }
            }, {
              headers: {
                "Access-Control-Allow-Origin": "*"
              }
            }).then((response) => {
              this.trainings = response.data;
              console.log("response", response);

            })
            .catch((error) => {
              console.log(error);
            });
        }
      }
    }
</script>


<style lang="scss" scoped>
h1 {
    color: var(--color-black);

}


</style>