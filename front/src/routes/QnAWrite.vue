<template>
  <div class="container">
    <form v-on:submit="submitQnA">
      <div class="form-group">
        <label for="title">제목</label>
        <input id="title" v-model="title" class="form-control" placeholder="제목을 입력하세요"/>
      </div>

      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" v-model="content" class="form-control" placeholder="내용을 입력하세요" />
      </div>
      <div class="btn-div">
        <button type="submit" class="btn btn-primary btn-write">등록</button>
        <router-link to="/QnA">
          <button class="btn btn-primary btn-back">뒤로가기</button>
        </router-link>
      </div>
    </form>
  </div>
</template>

<script>
const storage = window.sessionStorage;
export default {
  data:()=>{
    return{
      title:'',
      content: '',
      name:''
    }
  },
  computed:{

  },
  created() {
    this.fetchName();
  },
  methods:{
    fetchName(){
      let id = storage.getItem("authId")
      this.axios.get(`/api/myInfo/${id}`)
          .then(response => {
            this.name = response.data.name
          })
          .catch(error => {
            console.log(error)
          })

    },
    submitQnA(){
      const date = new Date()
      const current = date.getFullYear() + '-' + (date.getMonth()+1) + '-'+ date.getDate();

      const url = '/api/v1/QnA';
      const data = {
        title: this.title,
        content: this.content,
        date: current,
        name: this.name
      }
      this.axios.post(url,data)
          .then(response => {
            console.log(response);
            this.$router.push('/QnA')
          })
          .catch(error =>{
            console.log(error)
          })
    }

  }
}
</script>
<style lang="scss" scoped>
textarea{
  //width:500px;
  height:150px;
  resize: vertical;  //세로크기만 조절가능
}

.form-group{
  margin-top: 15px;
  .form-control{
    margin-top: 5px;
  }
}

.btn-div{
  margin-top: 20px;
  text-align: center;
  .btn-primary{
    width: 120px;
    font-weight: 700;
    flex-shrink: 0;
    margin: 5px;
  }
  .btn-back{
    background: #F1F1F1;
    border-color: #F1F1F1;
    &:hover{
      border-color: #d8d8d8;
      background-color: #d8d8d8;
    }
  }
  .btn-write{
    color: #eeeeee;
  }

}
</style>