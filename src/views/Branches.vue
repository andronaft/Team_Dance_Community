<template>
  <div class="home">

    <div class="main">

      <main>
        <h3>Бранчи</h3>
        <div class="branches" v-if="this.$route.query.id">
            <!-- {{this.$route.query}} -->

              <h3>Групповые тренировки</h3>

              <div class="groupTrainings">
                <div class="divTable">
                  <div class="header">
                    <span class="weekDay">
                      Понедельник
                    </span>
                     <span class="weekDay">
                      Вторник
                    </span>
                     <span class="weekDay">
                      Среда
                    </span>
                     <span class="weekDay">
                      Четверг
                    </span>
                     <span class="weekDay">
                      Пятница
                    </span>
                     <span class="weekDay">
                      Суббота
                    </span>
                     <span class="weekDay">
                      Воскресение
                    </span>
                  </div>
                  <div class="divBody">
                      <div class="weekDay monday">
                        <div class="monday"  v-for="training in trainings.monday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>

                      <div class="weekDay tuesday">
                        <div class="tuesday"  v-for="training in trainings.tuesday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <div class="weekDay wednesday">
                        <div class="wednesday"  v-for="training in trainings.wednesday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <div class="weekDay thirsday">
                        <div class="thirsday"  v-for="training in trainings.thirsday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <div class="weekDay friday">
                        <div class="friday"  v-for="training in trainings.friday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <div class="weekDay sunday">
                        <div class="sunday"  v-for="training in trainings.sunday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <div class="weekDay saturday">
                        <div class="saturday"  v-for="training in trainings.saturday" :key="training.id">
                          <div>{{training.name}}</div>
                          <div>{{training.time}}</div>
                          <div>{{training.hallId}}</div>
                          <div>{{training.trainerIds}}</div>
                        </div>
                      </div>
                      <!-- <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                         <div>{{training.name}}</div>
                      </div>
                      <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                        <div>{{training.name}}</div>
                      </div>
                      <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                         <div>{{training.name}}</div>
                      </div>
                      <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                         <div>{{training.name}}</div>
                      </div>
                      <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                         <div>{{training.name}}</div>
                      </div>
                      <div class="weekDay" v-for="training in trainings.monday" :key="training.id">
                         <div>{{training.name}}</div>
                      </div> -->
                  </div>
                </div>
      <!-- <table class="alltables">
          <tr>
              <th>Понедельник</th>
              <th>Вторник</th>
              <th>Среда</th>
              <th>Четверг</th>
              <th>Пятница</th>
              <th>Суббота</th>
              <th>Воскресение</th>
          </tr>
          <tr>
              <td v-for="training in trainings.monday" :key="training.id">{{training.name}}</td>
              <td v-for="training in trainings.tuesday" :key="training.id">{{training.name}}</td>
              <td v-for="training in trainings.wednesday" :key="training.id">{{training.name}}</td>
              <td v-for="training in trainings.thirsday" :key="training.id">{{training.name}}</td>
              <td v-for="training in trainings.friday" :key="training.id">{{training.name}}</td>
              <td v-for="training in trainings.sunday" :key="training.id">{{training.name}}</td>

          </tr> 


      </table> -->
  </div>
  <!-- <div class="groupTrainings">
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
  </div> -->
              
          
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
        trainings: {
          monday: [

          ],
          tuesday: [

          ],
          wednesday: [

          ],
          thirsday: [

          ],
          friday: [

          ],
          sunday: [

          ],
          saturday: [

          ]
        },
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
              const array = response.data
              array.forEach(element => {
                console.log(element,"Elem")
                if(element.weekDay == "MONDAY") {
                  this.trainings.monday.push(element)
                }
              });
              //this.trainings = response.data;
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


.header {
  display: grid;
  width: 100%;
  grid-template-columns: repeat(7,1fr);
  span {
    border: 2px solid var(--color-main);
  }

}

.divBody {
    display: grid;
  width: 100%;
  grid-template-columns: repeat(7,1fr);
}

</style>