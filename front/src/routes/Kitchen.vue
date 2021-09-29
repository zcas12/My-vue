<template>
  <div class="container">
    <div id="product-title">
      <h2 style="font-size: 38px; color: #405357; font-weight: 600;">
        <span>Kitchen</span>
        <span> 주방</span>
      </h2>
    </div>
    <KitchenList/>
  </div>
</template>

<script>
import KitchenList from '~/components/KitchenList'
import Loader from '~/components/Loader'

export default {
  components:{
    KitchenList,
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