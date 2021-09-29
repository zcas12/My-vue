<template>
  <div class="container">
    <div id="product-title">
      <h2 style="font-size: 38px; color: #405357; font-weight: 600;">
        <span>Interior</span>
        <span> 인테리어</span>
      </h2>
    </div>
    <InteriorList/>
  </div>
</template>

<script>
import InteriorList from '~/components/InteriorList'
import Loader from '~/components/Loader'

export default {
  components:{
    InteriorList,
    Loader
  }
}

</script>
<style lang="scss" scoped>
.container{
  #product-title{
    width: 677px;
    height: auto;
    margin: 30px 0px 28px 10px;
  }
}
</style>