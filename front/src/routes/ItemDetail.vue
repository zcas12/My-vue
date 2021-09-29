<template>
  <div class="container">
    <button class="btn btn-primary btn-back" @click="backMenu">뒤로가기</button>
    <div class="item-detail">
      <div class="item-poster" style="background-image: url(https://static.wixstatic.com/media/84770f_2ff29ff6663a4d619983458cb0899264.png/v1/fill/w_500,h_500,al_c,q_90,usm_0.66_1.00_0.01/84770f_2ff29ff6663a4d619983458cb0899264.webp) ">

      </div>
      <div class="item-spec">
        <div class="title">
          {{theItem.name}}
        </div>
        <div class="price">
          {{theItem.price}}원
        </div>
        <div class="stock">
          <h3>수량</h3>
          <button type="button" class="plus" @click="quantityPlus">+</button>
          <input type='number' class="quantity" v-model="quantity"  min="1" readonly="readonly"/>
          <button type="button" class="minus" @click="quantityMinus">-</button>
        </div>
        <div class="order-price">
          <h3 style=" width: 40%;">주문금액</h3>
          <h3 style=" width: 60%; text-align: right; margin: 0 10px;">{{orderAmount}}원</h3>
        </div>
        <button class="btn btn-primary " style="width:48%" @click="wishList">찜하기</button>
        <button class="btn btn-primary " style="width:48%" @click="Order">바로구매</button>
      </div>
    </div>
  </div>
</template>
<script>
import {mapState} from 'vuex'
const storage = window.sessionStorage;
export default {
  data: function(){
    return{
      quantity: 0,
      orderAmount: 0,
      checkDuplicate:false
    }
  },
  computed:{
    ...mapState('item',[
        'theItem',
        'loading'
    ]),
    ...mapState('member',[
      "loginSuccess",
      "loginError",
      'id'
    ])
  },
  created() {
    this.$store.dispatch('item/itemDetailWidthId',{
      id: this.$route.params.id
    })

  },
  mounted(){
  },
  methods:{
    backMenu(){
      this.$router.go(-1)
    },
    quantityPlus(){
      this.quantity++;
      this.orderAmount = this.theItem.price * this.quantity;
    },
    quantityMinus(){
      if(this.quantity > 1) {
        this.quantity--;
        this.orderAmount = this.theItem.price * this.quantity;
      }
    },
    async wishList(){
      await this.checkWish();

      if(storage.getItem("token")===null ){
        alert("로그인을 해주세요.");
        this.$router.push('/Login');
      }else if(this.quantity < 1){
        alert("수량을 선택해주세요.");
      }else if (this.checkDuplicate === false){
        alert("이미 찜한 상품입니다.");
      } else if(this.quantity > 0){
        let data = {
          count : this.quantity,
          orderAmount : this.orderAmount,
          memberId : storage.getItem("authId"),
          itemId : this.theItem.id,
          date: today()
        }
        const url = '/api/wish';
        this.axios.post(url,data)
            .then(res => {
              if(res.data !== null){
                alert("찜목록에 등록되었습니다.");
              }
            })
            .catch(err => {

            })
      }
    },
    async checkWish(){
      let memberId = storage.getItem("authId");
      let itemId = this.theItem.id

      const url = `/api/wishCheck/${itemId}/${memberId}`;
      await this.axios.get(url)
          .then(res => {
            console.log(res.data)
            this.checkDuplicate= false;
          })
          .catch(err => {
            this.checkDuplicate= true;
          })
    },
    Order(){
      if(storage.getItem("token")===null ){
        alert("로그인을 해주세요.");
        this.$router.push('/Login');
      } else if(this.quantity < 1){
        alert("수량을 선택해주세요.");
      } else if(this.quantity > 0){
        let data = {
          count : this.quantity,
          orderAmount : this.orderAmount,
          memberId : storage.getItem("authId"),
          itemId : this.theItem.id,
          date: today()
        }
        const url = '/api/order';
        this.axios.post(url,data)
          .then(res => {
            if(res.data !== null){
              alert("주문이 되었습니다.");
            }
          })
          .catch(err => {

          })
      }
    }
  }
}

function today(){
  const date = new Date()
  const current = date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();
  return current;
}
</script>
<style lang="scss" scoped>
.container{
  padding-top: 40px;
  .btn-back{
    color: #eeeeee;
    margin-bottom: 20px;
  }
}
.item-detail{
  display: flex;
  .item-poster{
    flex-shrink: 0;
    width:500px;
    height: 500px;
    margin-right: 70px;
    border-radius: 10px;
    background-size: cover;
    background-position: center;
    position: relative;
  }
  .item-spec{
    flex-grow:1;
    .title{
      font-family: 'Oswald', sans-serif;
      font-size: 60px;
      line-height: 1;
      margin-bottom: 30px;
    }
    .price{
      font-size: 30px;
      font-weight: 900;
    }
    .stock {
      margin-bottom: 30px;
      h3 {
        margin: 24px 0 6px;
        font-family: "Oswald", sans-serif;
        font-size: 20px;
      }
      .plus,.minus{
        background-color: #80A6AD;
        border: 1px solid #80A6AD;
        color: #fff;
      }
      .quantity{
        width: 40px;
        outline: none;
        margin: 1px;
      }
    }
    .order-price{
      display:flex;
      margin-bottom: 30px;
    }
    button{
      color: #eeeeee;
      margin: 0 3px;
    }
  }
}

</style>