<template>
  <div class="users">
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
    <th>Edit</th>
  </tr>
  <tr v-for="branch in allBranches" v-bind:key="branch.id">
    <td>{{branch.id}}</td>
    <td>{{branch.name}}</td>
    <td>{{branch.information}}</td>
    <td>{{branch.contact}}</td>
    <td>{{branch.location}}</td>
    <td>{{branch.imgUrl}}</td>
    <td>{{branch.status}}</td>
    <td>{{date(branch.created)}}</td>
    <td>{{date(branch.updated)}}</td>
    <td><button @click="edit(branch)">Edit</button></td>
  </tr>
<modal name="hello-world" @before-open="beforeOpen"  :height="'auto'">
  <form @submit.prevent="editBranchBtn" class="editBranch">
            <label for="name">Название</label>
            <div>
              <input id="name" type="text" v-model="editBranch.name" required autofocus>
            </div>
            <label for="information">Информация</label>
            <div>
              <input id="information" type="text" v-model="editBranch.information" required>
            </div>
            <label for="contact">Контакт</label>
            <div>
              <input id="contact" type="text" v-model="editBranch.contact" required>
            </div>
            <label for="location">Локация</label>
            <div>
              <input id="location" type="text" v-model="editBranch.location" required>
            </div>
             <label for="location">Статус</label>
            <div>
              <input id="location" type="text" v-model="editBranch.status" required>
            </div>
            <div>
              <button class="editBranch" type="submit">Редактировать бранч</button>
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
        allBranches: [],
        editBranch: {
            name: 'Название',
            information: 'Информация',
            contact: 'Контакт',
            location: 'Локация',
            status: 'ACTIVE'
        }
    }
  },
  computed: {
      
  },
  created() {
      this.getAllBranches()
  },
  methods: {
      editBranchBtn() {
          console.log("Edited",this.editBranch)
          this.$axios.post('/api/v1/admin/branch/update', {
                    	"id": this.editBranch.id,
	"name": this.editBranch.name,
	"information":this.editBranch.information,
	"contact": this.editBranch.contact,
	"location": this.editBranch.location,
	"status": this.editBranch.status
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
          this.$modal.hide('hello-world');
      },
      beforeOpen (event) {
          this.editBranch = event.params
    console.log("Modal", this.edit);
  },
      edit(branch) {
          console.log(branch)

          this.$modal.show('hello-world', branch)
      },
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

.editBranch {
    margin: 20px;
}
</style>
