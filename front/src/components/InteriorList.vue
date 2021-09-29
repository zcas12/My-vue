<template>
  <div class="container">
    <div class="inner">
      <div class="interior-list">
          <RouterLink
              v-for="item in interiorList"
              :key="item.id"
              :to="`/itemdetail/${item.id}`"
              style="backgroundImage:url(https://static.wixstatic.com/media/84770f_07b37235e2394a1bb679d4c99c2485d2.png/v1/fill/w_225,h_225,al_c,q_85,usm_0.66_1.00_0.01/84770f_07b37235e2394a1bb679d4c99c2485d2.webp)"
              class="interior">
            <div class="info">
              <div class="title">
                {{item.name}}
              </div>
              <div class="year">
                {{item.price}}
              </div>
            </div>
          </RouterLink >

      </div>

    </div>
  </div>
</template>
<script>
import InteriorItem from '~/components/InteriorItem'
import Loader from '~/components/Loader'
export default {
  components:{
    InteriorItem,
    Loader
  },
  created() {
    this.fetchData()
  },
  data(){
    return{
      interiorList:[]
    }
  },
  methods:{
    fetchData(){
      this.axios.get('/api/v1/InteriorList')
          .then(response => {
            console.log(response.data.data);
            const result = response.data.data;
            this.interiorList = result;
          })
          .catch(error => {
            console.log(error)
          })
    }
  }
}
</script>
<style lang="scss" scoped>
.container{
  margin-top: 30px;
  .inner{
    //background-color: $gray-200;
    padding: 10px 0;
    border: 4px;
    text-align: center;
  }
  .interior-list{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    .interior{
      $width:230px;
      width:$width;
      height: $width * 6 / 5;
      margin:10px;
      border-radius: 4px;
      background-size: cover;
      overflow: hidden;
      position: relative;
      &:hover::after{
        content: "";
        position: absolute;
        top:0;
        bottom:0;
        left:0;
        right:0;
        border:2px solid #80A6AD;
      }
      .info {
        background-color: rgba(#ddd, .3);
        width: 100%;
        padding: 10px;
        font-size: 16px;
        text-align: center;
        position: absolute;
        left: 0;
        bottom: 0;
        backdrop-filter: blur(10px);
      }
    }

  }
}

</style>