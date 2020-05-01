<template>
  <div class="users">
    <h1>Груповые тренировки</h1>

    <div class="tabs">
            <div class="tab" @click="setTab('Alltrainings')">Все тренировки</div>
            <div class="tab" @click="setTab('Addtraining')">Добавить</div>
        </div>
           <component :is="currentComponent"/>

  </div>
</template>

<script>

import Alltrainings from '@/components/admin/GroupTrainings/AllTrainings.vue'
import Addtraining from '@/components/admin/GroupTrainings/Addtraining.vue'

export default {
  name: 'HelloWorld',
  components: {
        Addtraining, Alltrainings
    },
  props: {
    msg: String
  },
  data() {
    return {
        currentComponent: 'Alltrainings'
    }
  },
  computed: {
      
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

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.tabs {
    cursor: pointer;
    color: var(--color-white);
    display: flex;
    .tab {
        background-color: var(--color-main);
        padding: 10px;
    }
}
</style>
