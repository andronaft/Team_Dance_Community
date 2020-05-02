<template>
  <div class="users">
      <h1>Заявки</h1>
        <div class="tabs">
            <div class="tab" :class="{active: currentComponent == 'Activebids'}" @click="setTab('Activebids')">Активные заявки</div>
            <div class="tab" :class="{active: currentComponent == 'Allbids'}" @click="setTab('Allbids')">Все заявки</div>
        </div>
       <component :is="currentComponent"/>
  </div>
</template>

<script>

import Activebids from '@/components/admin/Bids/Activebids.vue'
import Allbids from '@/components/admin/Bids/Allbids.vue'

export default {
    name: 'HelloWorld',
    components: {
        Activebids, Allbids
    },
    props: {
        msg: String
    },
    data() {
        return {
            currentComponent: 'Activebids'
        }
    },
    created() {
    },
    methods: {
        setTab(tab) {
            this.currentComponent = tab
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
