<template>
  <myPage/>
  <div class="container">
  <table class="table table-hover">
    <thead>
    <tr>
      <th tabindex="0" style="" data-field="orderId">
        <div class="th-inner both">Item ID</div>
        <div class="fht-cell"></div>
      </th>
      <th tabindex="0" style="" data-field="name">
        <div class="th-inner both">상품정보</div>
        <div class="fht-cell"></div>
      </th>
      <th tabindex="0" style="" data-field="orderDate">
        <div class="th-inner both">주문일자</div>
        <div class="fht-cell"></div>
      </th>
      <th tabindex="0" style="" data-field="orderPrice">
        <div class="th-inner both">주문금액</div>
        <div class="fht-cell"></div>
      </th>
      <th tabindex="0" style="" data-field="count">
        <div class="th-inner both">수량</div>
        <div class="fht-cell"></div>
      </th>
      <th tabindex="0" style="" data-field="status">
        <div class="th-inner both">주문상태</div>
        <div class="fht-cell"></div>
      </th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item,idx) in orderList" :key="item.orderId">
      <td>{{item.orderId}}</td>
      <td>{{item.name}}</td>
      <td>{{item.orderDate}}</td>
      <td>{{item.orderPrice}}</td>
      <td>{{item.count}}</td>
      <td>{{item.status}}</td>
    </tr>
    </tbody>
  </table>
  </div>
</template>
<script>
import myPage  from "~/routes/MyPage"
const storage = window.sessionStorage;
export default {
  components:{
    myPage
  },
  data(){
    return{
      orderList:[],
    }
  },
  created() {
    this.fetchData()
  },

  methods:{
    fetchData(){
      let id = storage.getItem("authId");
      this.axios.get(`/api/orderItem/${id}`)
          .then(response => {
            console.log(response.data);
            const result = response.data;
            this.orderList = result;
          })
          .catch(error => {
            console.log(error)
          })
    }
  }
}
</script>