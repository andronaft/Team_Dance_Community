<template>
<div class="login_navbar" v-click-outside="hideProfile">
    <div v-if="!isLoggedIn" v-click-outside="profileOutside">
        <button class="profile" @click="toggleProfile" ref="toggle">
            <font-awesome-icon icon="user" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
        </button>
        <transition name="fade">
            <div class="profile_dialog" :class="{active: isActive}" v-if="isActive" ref="profile">
                <form class="login" @submit.prevent="logMeIn">
                    <label>Username</label>
                    <div>
                        <input required v-model="username" type="text" placeholder="Name" />
                    </div>
                    <label>Password</label>
                    <div>
                        <input required v-model="password" type="password" placeholder="Password" />
                    </div>
                    <button class="loginBtn" type="submit">Войти</button>
                    <router-link to="/registration" @click.native="toggleProfile">Регистрация</router-link>
                </form>
            </div>
        </transition>
    </div>
    <div class="" v-else  v-click-outside="profileOutside">

        <button class="profile" @click="toggleProfile">
            <font-awesome-icon icon="user" size="2x" :style="{ color: 'white' }"></font-awesome-icon>
        </button>
        <transition name="fade">
            <div class="profile_dialog" :class="{active: isActive}" v-if="isActive">
                <div class="profile_body">
                    <div class="logo">
                        <router-link :to="{ name: 'Profile', params: { username: store_username }}"><img alt="Vue logo"
                                src="@/assets/logo.png"></router-link>
                    </div>
                    <div class="logout">
                        <router-link class="logoutBtn" :to="{ name: 'Profile', params: { username: store_username }}">{{store_username}}
                        </router-link>
                    </div>
                    <div class="logout">
                        <button class="logoutBtn" @click="logout">Выход</button>
                    </div>
                    <div class="logout" v-if="this.$store.getters.user.role.includes('ROLE_ADMIN')">
                        <router-link class="logoutBtn" @click.native="toggleProfile" to="/admin">Админ Панель
                        </router-link>
                    </div>
                    <div class="logout" v-if="this.$store.getters.user.role.includes('ROLE_TRAINER')">
                        <router-link class="logoutBtn" @click.native="toggleProfile" to="/cabinet">Кабинет</router-link>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</div>
</template>

<script>
import store from "../store";
import {
    mapGetters
} from "vuex";


export default {
    name: 'LoginComponent',

    data: () => ({
        username: 'testuser',
        password: '13371337',
        isActive: false
    }),
    computed: {
        isLoggedIn: function () {
            return this.$store.getters.isLoggedIn
        },
        store_username: function () {
            return this.$store.getters.user.username
        },
    },
    created() {
        
        console.log("State", this.$store.state.user)
    },
    methods: {

        profileOutside() {
            if(this.isActive) {
                this.isActive = false
            }
        },
        logMeIn() {
            console.log("Ligmein")
            let username = this.username;
            let password = this.password
            this.$store.dispatch('login', {
                    username,
                    password
                })
                .then(() => this.$router.push('/'))
                .catch(err => console.log(err))
        },
        toggleProfile() {
            console.log("toogle profile")
            this.isActive = !this.isActive
        },
        hideProfile() {
            console.log("hide profile")
            if (this.isMobile()) {
                this.isActive = false
            }
        },
        logout: function () {
            this.$store.dispatch('logout')
                .then(() => {
                    this.$router.push('/login')
                })
        },
    },

}
</script>

<style scoped lang="scss">
.login_navbar {
    display: flex;
    justify-content: center;
    align-items: center;
}

.profile_dialog {
    top:60px;
    background-color: var(--color-white);
    color: var(--color-black);
    text-align: left;
    position: absolute;
    left: 0;
    width: 100%;
    display: none;

    .profile_body {
        padding: 10px;

        .logo {
            display: flex;
            justify-content: center;
            align-items: center;

            img {
                border-radius: 50%;
                background-color: var(--color-black);
                height: 100px;
                width: 100px;
            }
        }

        .username {
            a {
                color: var(--color-dark);
                cursor: pointer;
                text-decoration: none;
            }
        }
    }

    &.active {
        display: block;
    }

    .login {
        padding: 20px;
        a {
            color: var(--color-dark);
            display: block;
            margin-top: 10px;
            text-decoration: none;
        }
    }

    @include lg {
        border-bottom-left-radius: 10px;
        border-bottom-right-radius: 10px;
        left: auto;
        right: 0;
        width: 300px;
    }
}

.loginBtn,
.logoutBtn {
    border-radius: 5px;
    width: 100%;
    margin-top: 10px;
    background: var(--color-primary-dark);
    color: var(--color-white);
    border: none;
    cursor: pointer;
    padding: 15px;
    box-sizing: border-box;
    display: block;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    transition: all .4s ease-in-out;
    &:hover {
        background: lighten(#011627, 10%);
    }
}

.profile {
    cursor: pointer;
    padding: 10px;
    margin-right: 20px;
    text-align: left;
    position: relative;
    display: flex;
    right: 0;
    justify-items: flex-start;
    align-items: center;
    -webkit-appearance: none;
    border-radius: 0;
    background: none;
    border: none;
    transition: all .4s ease-in-out;

    &:hover {
        background-color: var(--color-black);
        border-radius: 10px;
    }
}
</style>