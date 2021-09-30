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
          <div class="th-inner both">찜일자</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="orderPrice">
          <div class="th-inner both">금액</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="count">
          <div class="th-inner both">수량</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="count">
          <div class="th-inner both"></div>
          <div class="fht-cell"></div>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,idx) in wishList" :key="item.wishId">
        <td>{{item.wishId}}</td>
        <td>{{item.name}}</td>
        <td>{{item.wishDate}}</td>
        <td>{{item.price}}</td>
        <td>{{item.count}}</td>
        <td>
          <button class="btn btn-danger" @click="deleteWish(item.wishId)">X</button>
        </td>
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
      wishList:[],
    }
  },
  created() {
    this.fetchData()
  },
  methods:{
    fetchData(){
      let id = storage.getItem("authId");
      this.axios.get(`/api/wish/${id}`)
          .then(response => {
            console.log(response.data);
            const result = response.data;
            this.wishList = result;
          })
          .catch(error => {
            console.log(error)
          })
    },
    deleteWish(id){
      if (!confirm("삭제 하시겠습니까?")) {

      } else {
        this.axios.delete('/api/v1/wish/' + id)
            .then(response => {

              console.log(response.data);
              this.fetchData();
            })
            .catch(error => {
              console.log(error);
            })
      }
    }
  }
}
</script>