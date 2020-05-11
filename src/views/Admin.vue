<template>
  <div class="admin">
    <aside>
        <nav>
            <router-link class="nav-link" @click.native.prevent="navClick('Users')" to="/admin">Пользователи</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('Branches')" to="/admin">Бранчи</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('Bids')" to="/admin">Заявки</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('GroupTraining')" to="/admin">Груповые тренировки</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('Trainers')" to="/admin">Тренера</router-link>

        </nav>
    </aside>
    <main>
        <component :is="currentComponent"/>
    </main>
  </div>
</template>

<script>

import HelloWorld from '@/components/HelloWorld.vue'
import Users from '@/components/admin/Users/Users.vue'
import Branches from '@/components/admin/Branches/Branches.vue'
import Bids from '@/components/admin/Bids/Bids.vue'
import Trainers from '@/components/admin/Trainers/Trainers.vue'
import GroupTraining from '@/components/admin/GroupTrainings/GroupTraining.vue'


export default {
    components: {
        HelloWorld, Users, Branches, Bids, GroupTraining, Trainers
    },
    data() {
        return {
            currentComponent: 'Users'
        }
    },
    created() {
        console.log("Adm", this.$route.query.tab)
        if(this.$route.query.tab) {
            this.currentComponent = this.$route.query.tab
        }
    },
    methods: {
        navClick(component) {
            this.currentComponent = component
            this.$router.push({query: {tab: component}})
        }
    }
}
</script>
<style lang="scss" scoped>
    .admin {
        position: sticky;
        display: grid;
        grid-template-columns: 1fr 3fr;
        height: 100%;
        aside {
            background-color: var(--color-black);
            //flex: 1;
            nav {
                .nav-link {
                    transition: all .4s ease-in-out;
                    display: block;
                    background-color: var(--color-main);
                    color: #fff;
                    padding: 20px;
                    text-decoration: none;
                    &:hover {
                        background: darken(#FF9F1C, 10%);
                    }
                }
            }
        }
        main {
            background-color: #fff;
            padding: 20px 20px;
        }
    }
</style>


<style lang="scss">

.tabs {
    cursor: pointer;
    color: var(--color-white);
    display: flex;
    margin-bottom: 10px;
    .tab {
        background-color: var(--color-main);
        padding: 10px;
        &:hover {
             background-color: lighten(#FF9F1C, 10%);
        }
        &.active {
            background-color: lighten(#FF9F1C, 10%);
        }
    }
}

.status {
  border-radius: 10px;
  padding: 5px;
  color: var(--color-white);
  background-color: var(--color-successful);
  &.err {
    background-color: var(--color-err);
  }
}

.roles {
  display: flex;
.role {
  cursor: pointer;
  border-radius: 10px;
  margin: 0 5px;
  display: block;
  background-color: var(--color-black);
  color: var(--color-white);
  padding: 10px;
}
}
</style>