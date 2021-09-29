<template>
  <div class="container">
    <div id="product-title">
      <h2 style="font-size: 38px; color: #405357; font-weight: 600;">
        <span>Bedroom</span>
        <span> 침실</span>
      </h2>
    </div>
    <BedroomList/>
  </div>
</template>

<script>
import BedroomList from '~/components/BedroomList'
import Loader from '~/components/Loader'

export default {
  components:{
    BedroomList,
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