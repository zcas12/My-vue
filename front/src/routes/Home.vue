<template>
  <div class="container">
      <div class="main-box" >
        <div class="title">
          <div class="title-content" >
            <h4 style="color: #405357; line-height: 1.2em; font-size: 47px;">
              <span style="font-size: 47px; font-weight: 700;">
                <span style="line-height:1.2em;">MODERN DESIGN & COZY COMFORT</span>
              </span>
            </h4>
          </div>
          <div class="title-content2">
            <h5 style="color: #F1F1F1; font-size: 22px; letter-spacing: 0.1em;">
              <span>북유럽 스타일 인테리어의 아늑함 </span>
            </h5>
          </div>
        </div>
        <div class="s-container">
          <swiper
              :slides-per-view="1"
              :space-between="50"
              :autoplay="{ delay:5000}"
              :navigation="{}"
              :pagination="{ clickable: true }"
              @swiper="onSwiper"
              @slideChange="onSlideChange">
            <swiper-slide>
              <img src="https://static.wixstatic.com/media/cda177_4acd33bb70eb4ef6b299f81a90593394.jpg/v1/fill/w_490,h_387,al_c,q_80,usm_0.66_1.00_0.01/cda177_4acd33bb70eb4ef6b299f81a90593394.webp"/>
            </swiper-slide>
            <swiper-slide>
              <img src="https://static.wixstatic.com/media/cda177_3173128ef795400a848e41de26833385.jpg/v1/fill/w_490,h_387,al_c,q_80,usm_0.66_1.00_0.01/cda177_3173128ef795400a848e41de26833385.webp"/>
            </swiper-slide>
            <swiper-slide>
              <img src="https://static.wixstatic.com/media/cda177_333db974a151469bb4d1685c4448a004.jpg/v1/fill/w_490,h_387,al_c,q_80,usm_0.66_1.00_0.01/cda177_333db974a151469bb4d1685c4448a004.webp"/>
            </swiper-slide>
          </swiper>
        </div>
      </div>
      <div class="main-box" style="display: grid; grid-template-columns: repeat(3,334.5px); grid-gap: 10px; grid-auto-rows: minmax(100px, auto); margin-top: 10px;">
        <div class="main-item" style="width: 310px; height: 660px; grid-column: 1/2; grid-row: 1/5;">
          <div class="item-component">
            <img src="https://static.wixstatic.com/media/84770f_846b3eb4a2424c6ca3e20381f6a37b12.png/v1/fill/w_314,h_657,al_c,q_90,usm_0.66_1.00_0.01/84770f_846b3eb4a2424c6ca3e20381f6a37b12.webp"/>
          </div>
        </div>
        <div class="main-item" style="width: 310px; height: 320px; grid-column: 2/3;grid-row: 1/3;} ">
          <img src="https://static.wixstatic.com/media/84770f_2ff29ff6663a4d619983458cb0899264.png/v1/fill/w_314,h_319,al_c,q_90,usm_0.66_1.00_0.01/84770f_2ff29ff6663a4d619983458cb0899264.webp"/>
        </div>
        <div class="main-item" style="width: 310px; height: 320px; grid-column: 2/3; grid-row: 3/4;">
          <img src="https://static.wixstatic.com/media/84770f_d53e8acab8b245a9af9d4d94393c9f35.png/v1/fill/w_314,h_319,al_c,q_90,usm_0.66_1.00_0.01/84770f_d53e8acab8b245a9af9d4d94393c9f35.webp"/>
        </div>
        <div class="main-item item-decor" style="width: 310px; height: 170px; grid-column: 3/4; grid-row: 1/2; background-color: #80A6AD; cursor: pointer;">
          <span style="color: #F1F1F1; white-space:nowrap; margin: 0;" >DECOR</span>
        </div>
        <div class="main-item" style=" width: 310px; height: 470px; grid-column: 3/4; grid-row: 2/4">
          <img src="https://static.wixstatic.com/media/84770f_604877bf317e405eb76aa207fbc71bb8.png/v1/fill/w_314,h_469,al_c,q_90,usm_0.66_1.00_0.01/84770f_604877bf317e405eb76aa207fbc71bb8.webp"/>
        </div>
      </div>
  </div>
</template>
<script>
// import Swiper core and required components
import SwiperCore, { Navigation, Pagination, A11y , Autoplay } from "swiper";

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// install Swiper components
SwiperCore.use([Navigation, Pagination, A11y, Autoplay ]);

// Import Swiper styles
export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  methods: {
    onSwiper(swiper) {
      // console.log(swiper)
    },
    onSlideChange() {
      // console.log('slide change')
    },
  },
};
</script>


<style lang="scss" >
@import "~swiper/swiper";
@import "~swiper/swiper-vars";
@import "~swiper/components/pagination/pagination.scss";
@import "~swiper/components/navigation/navigation.scss";
html,
body {
  position: relative;
  height: 100%;
}

body {
}
.container{
  width: 1024px;
  .main-box{
    display: flex;
    margin-top: 30px;
    .title{
      width: 100%;
      background-color: #80A6AD ;
      .title-content{
        width: 319px;
        height: auto;
        position: relative;
        margin: 47px 0px 20px 0;
        left: 39px;
      }
      .title-content2{
        position: relative;
        margin: 0px 0px 6px 0;
        left: 39px;
      }
    }
    .s-container {
      width: 491px;
    }
    .main-item {
      max-width: 100%;
      max-height: 100%;
      overflow: hidden;
      .item-component {
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
      }
    }
    .item-decor{
      background-color: #80A6AD;
      cursor: pointer;
      text-align: initial;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 48px;
      font-weight: 700;
      &:hover{
        opacity: 0.6;
        transition: all 1s;
      }
    }
  }

}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;

  /* Center slide text vertically */
  display: -webkit-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  align-items: center;
}


</style>
