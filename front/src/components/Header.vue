<template>
  <header class="container">
    <Logo />
    <div class="nav nav-pills">
      <div v-for="nav in navigation" :key="nav.name" class="nav-item">
        <RouterLink :to="nav.href" active-class="active" :class="{active:isMatch(nav.path)}" class="nav-link">
          {{nav.name}}
        </RouterLink>
      </div>
    </div>
    <div v-if="!loginSuccess">
      <RouterLink to="/Login" class="login">
        로그인
      </RouterLink>
      <RouterLink to="/SignUp" class="admin">
        회원가입
      </RouterLink>
    </div>
    <div v-else="loginSuccess && auth === 'ROLE_USER'">
      <RouterLink to="/WishList" class="myPage">
        MY페이지
      </RouterLink>
      <button class="logout" v-on:click="logout">
        로그아웃
      </button>
    </div>
    <div v-else="loginSuccess && auth === 'ROLE_ADMIN'">
      <RouterLink to="/Admin_OrderList" class="myPage">
        관리자 페이지
      </RouterLink>
      <button class="logout" v-on:click="logout">
        로그아웃
      </button>
    </div>
  </header>
</template>
<script>
import {mapActions, mapState} from 'vuex'
import Logo from './Logo'
const storage = window.sessionStorage;
export default {
  components:{
    Logo
  },
  data(){
    return{
      navigation:[
        {
          name:'메인',
          href:'/'
        },
        {
          name:'인테리어',
          href:'/interior',
          path: /^\/movie/
        },
        {
          name:'주방',
          href:'/kitchen',
        },
        {
          name:'침실',
          href:'/bedroom',
        },
        {
          name:'소개',
          href:'/about'
        },
        {
          name:'QnA',
          href:'/QnA'
        }
      ],
      auth:storage.getItem("auth")
    }
  },
  created() {
    if(storage.getItem("token")===null ){

    }else{
      this.refreshLogin(storage.getItem("token"));
    }

  },
  computed:{
    ...mapState('member',[
      "loginSuccess",
      "loginError",
      'name',
      'id'
    ])

  },
  methods:{
    ...mapActions('member',["refreshLogin"]),
    isMatch(path){
      if (!path) return false
      return path.test(this.$route.fullPath)
    },
    logout(){
      storage.removeItem("token");
      storage.removeItem("authId");
      this.$router.go(0);
    }
  }
}
</script>
<style lang="scss" scoped>
a{
 text-decoration: none;
}
  header{
    height: 70px;
    padding: 0 40px;
    display: flex;
    align-items: center;
    position: relative;
    .logo{
      margin-right: 40px;
    }

    .login{
      position: absolute;
      top:10px;
      bottom: 0;
      margin: auto;
      right: 40px;
    }
    .admin,.myPage{
      position: absolute;
      top:10px;
      bottom: 0;
      margin: auto;
      right: 100px;
    }
    .logout{
      position: absolute;
      top: 10px;
      right: 15px;
      border: none;
      color: #80A6AD;
      background-color: #fff;
      &:hover{
        color: #66858a;
      }
    }
  }
</style>