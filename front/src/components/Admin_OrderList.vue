<template>
  <AdminPage/>
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
        <th tabindex="0" style="" data-field="status">
          <div class="th-inner both"></div>
          <div class="fht-cell"></div>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item) in orderList" :key="item.orderId">
        <td>{{item.orderId}}</td>
        <td>{{item.name}}</td>
        <td>{{item.orderDate}}</td>
        <td>{{item.orderPrice}}</td>
        <td>{{item.count}}</td>
        <td>{{item.status}}</td>
        <td><button class="btn btn-primary btn-finish" @click="orderFinish(item.orderId)">배송완료</button></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import AdminPage from "~/routes/AdminPage"
export default {
  components:{
    AdminPage
  },
  data(){
    return{
      orderList:[]
    }
  },
  created() {
    this.fetchData()
  },
  methods:{
    fetchData(){
      this.axios.get('/api/admin/order')
        .then(response => {
          console.log(response)
          const result = response.data;
          this.orderList = result;
        })
        .catch(error => {
          console.log(error)
        })
    },
    orderFinish(id){
      if (!confirm("배송완료 하시겠습니까?")) {

      } else {
        this.axios.put('/api/order/' + id)
            .then(response => {
              console.log(response);
              alert("완료되었습니다.")
              this.$router.go(0);
            })
            .catch(error => {
              console.log(error);
            })
      }
    }
  }

}

</script>
<style lang="scss" scoped>
.btn-finish{
  width: 100px;
  color: #F1F1F1;
}
</style>