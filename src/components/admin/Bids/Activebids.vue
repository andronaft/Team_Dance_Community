<template>
  <div class="bids">
      <table>
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
          <tr v-for="(bid, key) in newBids" v-bind:key="bid.id">
              <td>{{bid.id}}</td>
              <td>{{bid.firstName}}</td>
              <td>{{bid.lastName}}</td>
              <td>{{bid.email}}</td>
              <td>{{bid.mobile}}</td>
              <td><button @click="applyBid(bid, key)">Apply</button></td>
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
        applyBid(bid, key) {
            console.log(key)
            const index = this.newBids.indexOf(key);
            this.newBids.splice(key, 1);
           this.$axios.post('/api/v1/admin/bid/applyFeedback/', {
                    "id": bid.id
                }, {
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    }
                }).then((response) => {
                    console.log("response", response);
                    this.emailstatus = response.data.isExist
                })
                .catch((error) => {
                    console.log(error);
                });

        },
        date(timestamp) {
          return this.convertTimestamp(timestamp)
        },
        getNewBids() {
            this.$axios.get('/api/v1/admin/bid/findAllNotActiveFeedback/', {
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

table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
    color: #222;
}

td,
th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
