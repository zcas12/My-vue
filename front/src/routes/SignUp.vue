<template>
  <div class="container">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">회원가입</h4>
        <form class="validation-form" v-on:submit="signInSubmit" novalidate >
          <div class="row">
            <div class="col-md-6 mb-3">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" v-model="id" placeholder="" @keyup="formCheck" @blur="checkDuplicate" required>
              <div class="id-feedback" v-if="!availableId">이미 사용중인 아이디입니다.</div>
              <div class="invalid-feedback">아이디를 입력해주세요.</div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="name">이름</label>
              <input type="text" class="form-control" id="name" v-model="name" placeholder="" @keyup="formCheck" required>
              <div class="invalid-feedback">이름을 입력해주세요.</div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="password">비밀번호</label>
              <input type="text" class="form-control" id="password" v-model="password" placeholder="" @keyup="formCheck" required>
              <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="passwordCheck">비밀번호 확인</label>
              <input type="text" class="form-control" id="passwordCheck" v-model="passwordCheck" placeholder="" @keyup="passCheck" required>
              <div class="invalid-feedback">비밀번호가 일치하지않습니다.</div>
            </div>
          </div>
          <div class="mb-3">
            <label for="email">
              이메일
            </label>
            <input type="email" class="form-control" id="email" v-model="email" placeholder="you@example.com" required>
            <div class="invalid-feedback">이메일을 입력해주세요.</div>
          </div>
          <div class="mb-3">
            <label for="address">
              주소
            </label>
            <input type="text" class="form-control" id="address" v-model="address" placeholder="서울특별시 강남구" required>
            <div class="invalid-feedback">주소를 입력해주세요.</div>
          </div>
          <div class="mb-3">
            <label for="address2">
              상세주소
              <span class="text-muted">
                &nbsp;(필수 아님)
              </span>
            </label>
            <input type="text" class="form-control" id="address2" v-model="address2" placeholder="상세주소를 입력해주세요.">
          </div>
          <hr class="mb-4">
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
            가입 완료
          </button>
        </form>
      </div>

  </div>

</template>
<script>
import axios from "axios";

export default {
  name:'SignUp',
  data:()=>{
    return{
      id:'',
      name:'',
      password:'',
      passwordCheck:'',
      email:'',
      address:'',
      address2:'',
      invalidCheck:'',
      availableId:true
    }
  },
  methods:{
    signInSubmit(){

      let data = {
        id:this.id,
        name:this.name,
        password:this.password,
        email:this.email,
        address:this.address,
        address2:this.address2
      };
      if(this.id===""){
        return false
      }else if(this.availableId === false) {
        return false
      }else if(this.name===""){
        return false
      }else if(this.password===""){
        return false
      }else if(this.passwordCheck===""){
        return false
      }else if(!this.passCheck()){
        return false
      }else if(this.invalidCheck !== true){
        return false
      }else{
        this.axios
            .post( "/api/auth/signUp", data)
            .then(response => {
              console.log(response);
              alert("회원가입이 되었습니다.");
              this.$router.push('/')
            })
            .catch(error =>{
              console.log(error)
              alert("회원가입에 실패했습니다.");
            })
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
      const passwordCheck = document.getElementById('passwordCheck');
      if(this.password !== this.passwordCheck || this.password === ""){
        passwordCheck.classList.add("is-invalid");
        passwordCheck.focus();
        return false;
      }else{
        passwordCheck.classList.remove("is-invalid");
        return true;
      }
    },
    async checkDuplicate(){
      this.availableId = true;

      const response = await checkDuplicateId(this.id);
      console.log(response)
      if (!response){
        this.availableId = true;
      }else{
        this.availableId = false;
      }
    }

  }

}
function checkDuplicateId(id){
  const url = `/api/MemberCheck/${id}`
  return new Promise((resolve, reject) => {
    axios.get(url)
        .then(res => {
          resolve(res.data.data)
        })
        .catch(err => {
          reject(err.message)
        })
  });
}
</script>
<style lang="scss" scoped>
.container {
  .input-form {
    max-width: 680px;
    margin-top: 80px;
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
.btn-lg {
  color: #ffffff;
}
.id-feedback{
  width: 100%;
  margin-top: 0.25rem;
  font-size: 0.875em;
  color: #dc3545;
}
</style>