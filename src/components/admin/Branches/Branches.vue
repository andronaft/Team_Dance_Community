<template>
  <div class="users">
    <h1>Бранчи</h1>

    <div class="tabs">
            <div class="tab" @click="setTab('Allbranches')">Все Бранчи</div>
            <div class="tab" @click="setTab('Addbranch')">Добавить Бранч</div>
        </div>
           <component :is="currentComponent"/>

  </div>
</template>

<script>

import Allbranches from '@/components/admin/Branches/Allbranches.vue'
import Addbranch from '@/components/admin/Branches/Addbranch.vue'

export default {
  name: 'HelloWorld',
  components: {
        Addbranch, Allbranches
    },
  props: {
    msg: String
  },
  data() {
    return {
        currentComponent: 'Allbranches'
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
