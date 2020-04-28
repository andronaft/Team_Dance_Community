<template>
  <div class="branches">
    <div class="addBranch-form">
          <form @submit.prevent="addBranch">
            <label for="name">Название</label>
            <div>
              <input id="name" type="text" v-model="name" required autofocus>
            </div>
            <label for="information">Информация</label>
            <div>
              <input id="information" type="text" v-model="information" required>
            </div>
            <label for="contact">Контакт</label>
            <div>
              <input id="contact" type="text" v-model="contact" required>
            </div>
            <label for="location">Локация</label>
            <div>
              <input id="location" type="text" v-model="location" required>
            </div>
            <div>
              <button class="addBranch" type="submit">Добавить бранч</button>
            </div>
          </form>
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
        name: 'Название',
        information: 'Информация',
        contact: 'Контакт',
        location: 'Локация'
    }
  },
  computed: {
      
  },
  created() {
  },
  methods: {
      addBranch() {
          this.$axios.post('/api/v1/admin/branch/create', {
                    "name": this.name,
                    "information": this.information,
	                "contact": this.contact,
	                "location": this.location
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
