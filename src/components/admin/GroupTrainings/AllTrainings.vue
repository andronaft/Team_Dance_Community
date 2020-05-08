<template>
  <div class="users">
    <table class="admin">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>branchId</th>
    <th>capacity</th>
    <th>hallId</th>
    <th>time</th>
    <th>weekDay</th>
    <th>status</th>
    <th>Created</th>
    <th>Updated</th>
    <th>Edit</th>
  </tr>
  <tr v-for="training in allTrainings" v-bind:key="training.id">
    <td>{{training.id}}</td>
    <td>{{training.name}}</td>
    <td>{{training.branchId}}</td>
    <td>{{training.capacity}}</td>
    <td>{{training.hallId}}</td>
    <td>{{training.time}}</td>
    <td>{{training.weekDay}}</td>
    <td>{{training.status}}</td>
    <td>{{date(training.created)}}</td>
    <td>{{date(training.updated)}}</td>
    <td><button @click="edit(training)">Edit</button></td>
  </tr>
<modal name="editTrainig" @before-open="beforeOpen"  :height="'auto'">
  <form @submit.prevent="editTrainigBtn" class="editTraining">
            <label for="name">Id</label>
            <div>
              <input id="name" type="text" v-model="training.id" required autofocus>
            </div>
            <label for="information">Name</label>
            <div>
              <input id="information" type="text" v-model="training.name" required>
            </div>
            <label for="contact">branchId</label>
            <div>
              <input id="contact" type="text" v-model="training.branchId" required>
            </div>
            <label for="location">Capacity</label>
            <div>
              <input id="location" type="text" v-model="training.capacity" required>
            </div>
             <label for="location">location</label>
            <div>
              <input id="location" type="text" v-model="training.location" required>
            </div>
             <label for="location">time</label>
            <div>
              <input id="location" type="text" v-model="training.time" required>
            </div>
             <label for="location">weekDay</label>
            <div>
              <input id="location" type="text" v-model="training.weekDay" required>
            </div>
             <label for="location">status</label>
            <div>
              <input id="location" type="text" v-model="training.status" required>
            </div>
            <label for="location">hallId</label>
            <div>
              <input id="location" type="text" v-model="training.hallId" required>
            </div>
            <div>
              <button class="editTrainig" type="submit">Редактировать</button>
            </div>
          </form>
</modal>

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
            allTrainings: [],
            training: ''
        }
    },
    computed: {

    },
    created() {
        this.getAllTrainings()
    },
    methods: {
        editTrainigBtn() {
            console.log("Edited", this.training)
            this.$axios.post('/api/v1/admin/training/groupTraining/update', {
                    	"id": this.training.id,
	                    "name":this.training.name,
	                    "branchId":this.training.branchId,
	                    "capacity":this.training.capacity,
	                    "location":this.training.location,
	                    "time":this.training.time,
	                    "weekDay":this.training.weekDay,
                      "status":this.training.status,
                      "hallId":this.training.hallId
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
            this.$modal.hide('editTrainig');
        },
        beforeOpen(event) {
            console.log("Modal", this.edit);
        },
        edit(training) {
            this.training = training
            console.log(training)


            this.$modal.show('editTrainig', training)
        },
        date(timestamp) {
            return this.convertTimestamp(timestamp)
        },
        getAllTrainings() {
            this.$axios.get('/api/v1/admin/training/groupTraining/', {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    this.allTrainings = response.data;
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

.editTraining {
    margin: 20px;
}
</style>
