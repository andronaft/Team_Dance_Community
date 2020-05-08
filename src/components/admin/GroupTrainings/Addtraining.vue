<template>
  <div class="Trainings">

      <div class="addTraining-form">
          <form @submit.prevent="addTraining">
              <label for="name">name</label>
              <div>
                  <input id="name" type="text" v-model="name" required autofocus>
              </div>
              <label for="information">branchId</label>
              <div>
                  <input id="information" type="text" v-model="branchId" required>
              </div>
              <label for="contact">capacity</label>
              <div>
                  <input id="contact" type="text" v-model="capacity" required>
              </div>
              <label for="location">location</label>
              <div>
                  <input id="location" type="text" v-model="location" required>
              </div>
              <label for="location">time</label>
              <div>
                  <input id="location" type="text" v-model="time" required>
              </div>
              <label for="location">location</label>
              <div>
                  <input id="weekDay" type="text" v-model="weekDay" required>
              </div>
              <label for="location">status</label>
              <div>
                  <input id="location" type="text" v-model="status" required>
              </div>
              <label for="location">hallId</label>
              <div>
                  <input id="location" type="text" v-model="hallId" required>
              </div>
              <div>
                  <button class="addBranch" type="submit">Добавить тренировку</button>
              </div>
          </form>

          <div class="msg" :class="{err: isError}" v-if="isError || isOK">
              <div v-if="isError">
                  {{error}}
              </div>
              <div v-if="isOK">
                  OK
              </div>


          </div>
      </div>
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
        isOK: false,
        isError: false,
        error: '',
        name: 'Название',
        branchId: '40',
        capacity: '20',
        location: 'Локация',
        time: "38:00:00",
        weekDay: "MONDAY",
        status: 'ACTIVE',
        hallId: ''

    }
  },
  computed: {
      
  },
  created() {
  },
  methods: {
      addTraining() {
          this.$axios.post('/api/v1/admin/training/groupTraining/create', {
                        "name": this.name,
                        "branchId": this.branchId,
                        "capacity": this.capacity,
                        "location": this.location,
                        "time": this.time,
                        "weekDay": this.weekDay,
                        "status": this.status,
                        "hallId": this.hallId
                }, {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    this.isOK = true
                    this.isError = false
                    console.log("response", response);
                })
                .catch((error) => {
                    console.log(error);
                    this.isOK = false
                    this.isError = true
                    this.error = error
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


</style>
