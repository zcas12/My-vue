<template>
  <myPage/>
  <div class="container">
    <div class="input-form col-md-12 mx-auto">
      <form class="validation-form" v-on:submit="myInfoUpdate" novalidate >
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" v-model="name" placeholder="" readonly />
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" v-model="password" placeholder="" @blur="passwordMach" required />
            <div class="pass-feedback" v-if="!passwordMatch">비밀번호가 잘못되었습니다.</div>
            <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="password">새 비밀번호</label>
            <input type="password" class="form-control" id="NewPassword" v-model="NewPassword" placeholder="" @keyup="formCheck" required />
            <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="password">새 비밀번호 확인</label>
            <input type="password" class="form-control" id="NewPasswordCheck" v-model="NewPasswordCheck" placeholder="" @keyup="passCheck" required />
            <div class="invalid-feedback">비밀번호가 일치하지않습니다.</div>
          </div>
        </div>
        <div class="mb-3">
          <label for="email">
            이메일
          </label>
          <input type="email" class="form-control" id="email" v-model="email" placeholder="you@example.com" required />
          <div class="invalid-feedback">이메일을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="address">
            주소
          </label>
          <input type="text" class="form-control" id="address" v-model="address" placeholder="서울특별시 강남구" required />
          <div class="invalid-feedback">주소를 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="address2">
            상세주소
            <span class="text-muted">
                  &nbsp;(필수 아님)
            </span>
          </label>
          <input type="text" class="form-control" id="address2" v-model="address2" placeholder="상세주소를 입력해주세요."/>
        </div>
        <hr class="mb-4"/>
        <div class="custom-control custom-checkbox">
          <input type="checkbox" class="form-check-input" id="invalidCheck" v-model="invalidCheck" required>
          <label class="form-check-label" for="invalidCheck">
            개인정보 수집 및 이용에 동의합니다.
          </label>
          <div class="invalid-feedback">
            You must agree before submitting.
          </div>
        </div>
        <div class="mb-4">

        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">
          정보 수정
        </button>
      </form>
    </div>
  </div>
</template>
<script>
import myPage  from "~/routes/MyPage"
const storage = window.sessionStorage;
import axios from "axios";

export default {
  components:{
    myPage
  },
  data:()=>{
    return{
      id:storage.getItem("authId"),
      name:'',
      password:'',
      NewPassword:'',
      NewPasswordCheck:'',
      email:'',
      address:'',
      address2:'',
      invalidCheck:'',
      passwordMatch:true
    }
  },
  created() {
    this.fetchData();
  },
  methods:{
    fetchData(){
      let id = this.id;
      this.axios.get(`/api/myInfo/${id}`)
      .then(response => {
        console.log(response.data)
        this.name = response.data.name
        this.email = response.data.email
        this.address = response.data.address
        this.address2 = response.data.address2
      })
      .catch(error => {

      })
    },
    async passwordMach(){
      this.passwordMatch = true;
      const response = await checkPasswordMach(this.id,this.password);
      console.log(response)
      if (!response){
        this.passwordMatch = false;
      }else{
        this.passwordMatch = true;
      }
    },
    formCheck(){
      const forms = document.getElementsByClassName('validation-form');
      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
            form.classList.add("was-validated");
          }
        }, false);
      });
    },
    passCheck(){
      const passwordCheck = document.getElementById('NewPasswordCheck');
      if(this.NewPassword !== this.NewPasswordCheck || this.NewPassword === ""){
        passwordCheck.classList.add("is-invalid");
        passwordCheck.focus();
        return false;
      }else{
        passwordCheck.classList.remove("is-invalid");
        return true;
      }
    },
    myInfoUpdate(){
      let data = {
        id:this.id,
        NewPassword:this.NewPassword,
        email:this.email,
        address:this.address,
        address2:this.address2
      };
      if(this.passwordMatch === false) {
        return false
      }else if(this.password===""){
        alert("비밀번호를 입력하세요")
        return false
      }else if(this.NewPassword===""){
        return false
      }else if(this.NewPasswordCheck===""){
        return false
      }else if(!this.passCheck()){
        return false
      }else if(this.invalidCheck !== true){
        return false
      }else{
        this.axios
            .put( "/api/member", data)
            .then(response => {
              console.log(response);
              alert("회원정보가 수정되었습니다.");
              this.$router.go(0)
            })
            .catch(error =>{
              console.log(error)
              alert("수정에 실패했습니다.");
            })
      }

    }
  }//methods end
} //export default end
function checkPasswordMach(id,password){
  const data = {
    id:id,
    password:password,
  }
  return new Promise((resolve, reject) => {
    axios.post("/api/PasswordCheck",data)
      .then(response => {
        console.log(response.data)
        resolve(response.data)
      })
      .catch(error =>{
        console.log(error)
        reject(error.message)
      })
  });
}
</script>
<style lang="scss" scoped>
.container {
  .input-form {
    max-width: 680px;
    margin-top: 20px;
    padding: 30px;
    background: #fff;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    border-radius: 10px;
    -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
    -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
    box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)

  }
}
.btn-lg {
  color: #ffffff;
}
.pass-feedback{
  width: 100%;
  margin-top: 0.25rem;
  font-size: 0.875em;
  color: #dc3545;
}
</style>
