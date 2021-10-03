<template>
  <AdminPage/>
  <div class="container">
    <div class="input-form col-md-12 mx-auto">
      <form v-on:submit="submitItem">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="name">제품명</label>
            <input  class="form-control" id="name" v-model="name" placeholder=" " required/>
          </div>
          <div class="col-md-6 mb-3">
            <label for="categories">카테고리</label>
            <div  class="selects">
              <select id="categories" v-model="category" class="form-select">
                <option
                    v-for="item in categories"
                    :key="item.key"
                    :value="item.key">
                  {{item.name}}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="price">가격</label>
            <input type="number" class="form-control" id="price" min="1" v-model="price" placeholder="" required />
          </div>
          <div class="col-md-6 mb-3">
            <label for="stock">수량</label>
            <input type="number" class="form-control" id="stock" min="1" v-model="stock" placeholder="" required />
          </div>
        </div>
        <div class="btn-div">
          <button type="submit" class="btn btn-primary btn-write">등록</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import AdminPage from "~/routes/AdminPage";
export default {
  components:{
    AdminPage
  },
  data(){
    return{
      itemList:[],
      categories:[
        {
          name:'인테리어',
          key:'interior'
        },
        {
          name:'주방',
          key:'kitchen'
        },
        {
          name:'침실',
          key:'bedroom'
        }
      ],
      name:'',
      price:0,
      stock:0,
      category:''
    }
  },
  methods:{
    submitItem(){
      let url = '/api/admin/item';
      const data = {
        name: this.name,
        price: this.price,
        stock: this.stock,
        category:this.category
      }
      this.axios.post(url,data)
          .then(response => {
            console.log(response);
          })
          .catch(error =>{
            console.log(error)
          })
    }
  }
}

</script>
<style lang="scss" scoped>
label{
  margin-bottom: 10px;
}
input{
  margin-bottom: 10px;
}
.container {
  .input-form {
    max-width: 680px;
    margin-top: 50px;
    padding: 32px;
    background: #fff;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    border-radius: 10px;
    -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
    -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)

  }
}
select{
  width: 150px;
  margin-right: 10px;
  &:last-child{
    margin-right: 0;
  }
}
.btn-div{
  text-align: right;
  .btn-write{
    color: #F1F1F1;
  }
}
</style>