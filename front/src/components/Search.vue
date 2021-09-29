<template>
  <div class="container">
    <input v-model="title" type="text" class="form-control" placeholder="제목" @keyup.enter="apply"/>
    <button class="btn btn-primary" @click="apply">
      검색
    </button>
  </div>
</template>

<script>
export default{
  data(){
    return{
      title:''
    }
  },
  methods:{
    apply(){

    }
  }
}

</script>

<style lang="scss" scoped>
.container{
  display: flex;
  > *{
    margin-right: 10px;
    font-size: 15px;
    &:last-child{
      margin-right: 0;
    }
  }
  .btn{
    width: 120px;
    //height: 50px;
    font-weight: 700;
    flex-shrink: 0;
    color: #fff;
  }
}

</style>