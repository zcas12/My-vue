<template>
  <div class="container">
    <div id="title">
      <h2 style="font-size: 38px; color: #405357; font-weight: 600;">
        <span>관리자</span>
        <span> 페이지</span>
      </h2>
    </div>
    <div class="nav nav-pills">
      <div v-for="nav in navigation" :key="nav.name" class="nav-item">
        <RouterLink :to="nav.href" active-class="active"  :class="{active:isMatch(nav.path)}" class="nav-link">
          {{nav.name}}
        </RouterLink>
      </div>
    </div>
  </div>
</template>
<script>
const storage = window.sessionStorage;
export default {
  data(){
    return{
      navigation:[
        {
          name:'주문목록',
          href:'/Admin_OrderList'
        },
        {
          name:'회원목록',
          href: '/Admin_MemberList'
        }
      ]
    }
  },
  created() {
    if(storage.getItem("token")===null ){
      this.$router.push('/login')
    }
  },
  methods:{
    isMatch(path){
      if (!path) return false
      return path.test(this.$route.fullPath)
    }
  }
}

</script>
<style lang="scss" scoped>
.container{
  #title{
    width: 677px;
    height: auto;
    margin: 30px 0px 28px 10px;
  }
}
</style>