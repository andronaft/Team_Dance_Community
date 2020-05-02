<template>
  <div class="bids">
      <table class="admin">
          <tr>
              <th>ID</th>
              <th>Firstname</th>
              <th>Lastname</th>
              <th>Email</th>
              <th>Mobile</th>
              <th>Status</th>
              <th>Created</th>
              <th>Updated</th>
          </tr>
          <tr v-for="bid in newBids" v-bind:key="bid.id">
              <td>{{bid.id}}</td>
              <td>{{bid.firstName}}</td>
              <td>{{bid.lastName}}</td>
              <td>{{bid.email}}</td>
              <td>{{bid.mobile}}</td>
              <td>{{bid.status}}</td>
              <td>{{date(bid.created)}}</td>
              <td>{{date(bid.created)}}</td>
          </tr>


      </table>
  </div>
</template>

<script>
export default {
    props: {
        msg: String
    },
    data() {
        return {
            newBids: []
        }
    },
    created() {
        this.getNewBids()
    },
    methods: {
        date(timestamp) {
          return this.convertTimestamp(timestamp)
        },
        getNewBids() {
            this.$axios.get('/api/v1/admin/bid/findAllFeedback/', {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    this.newBids = response.data;
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


</style>
