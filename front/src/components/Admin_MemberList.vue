<template>
  <AdminPage/>
  <div class="container">
    <table class="table table-hover">
      <thead>
      <tr>
        <th tabindex="0" style="" data-field="id">
          <div class="th-inner both">ID</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="name">
          <div class="th-inner both">이름</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="email">
          <div class="th-inner both">email</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="address">
          <div class="th-inner both">주소</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="address2">
          <div class="th-inner both">상세주소</div>
          <div class="fht-cell"></div>
        </th>
        <th tabindex="0" style="" data-field="authority">
          <div class="th-inner both">권한</div>
          <div class="fht-cell"></div>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,idx) in memberList" :key="item.id">
        <td>{{item.id}}</td>
        <td>{{item.name}}</td>
        <td>{{item.email}}</td>
        <td>{{item.address}}</td>
        <td>{{item.address2}}</td>
        <td>{{item.authority}}</td>
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
      memberList:[]
    }
  },
  created() {
    this.fetchData()
  },
  methods:{
    fetchData(){
      this.axios.get('/api/admin/member')
        .then(response => {
          console.log(response.data.data)
          const result = response.data.data;
          this.memberList = result;
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}

</script>