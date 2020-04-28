<template>
  <div class="admin">
    <aside>
        <nav>
            <router-link class="nav-link" @click.native.prevent="navClick('Users')" to="/admin">Пользователи</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('Branches')" to="/admin">Бранчи</router-link>
            <router-link class="nav-link" @click.native.prevent="navClick('Bids')" to="/admin">Заявки</router-link>
        </nav>
    </aside>
    <main>
        <component :is="currentComponent"/>
    </main>
  </div>
</template>

<script>

import HelloWorld from '@/components/HelloWorld.vue'
import Users from '@/components/admin/Users.vue'
import Branches from '@/components/admin/Branches/Branches.vue'
import Bids from '@/components/admin/Bids/Bids.vue'

export default {
    components: {
        HelloWorld, Users, Branches, Bids
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
        display: grid;
        grid-template-columns: 1fr 3fr;
        height: 100vh;
        aside {
            background-color: var(--color-black);
            //flex: 1;
            nav {
                .nav-link {
                    display: block;
                    background-color: var(--color-main);
                    color: #fff;
                    padding: 20px;
                    text-decoration: none;;
                }
            }
        }
        main {
            background-color: #fff;
            padding: 0 20px;
        }
    }
</style>
