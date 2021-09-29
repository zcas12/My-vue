<template>
  <div className="container">
    <form v-on:submit="login({id, password})">
      <h2>Login</h2>
      <label><input placeholder="아이디" v-model="id" required></label>
      <label><input type="password" placeholder="패스워드" v-model="password" required></label>
      <div class="id-feedback" v-if="loginSuccess">로그인 성공</div>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<script>
import {mapState ,mapActions} from "vuex"
export default {
  name: 'Login',
  data: () => ({
    //loginSuccess: false,
    //loginError: false,
    id: '',
    password: ''
  }),
  computed:{
    ...mapState('member',["loginSuccess","loginError"])
  },
  methods: {
    ...mapActions('member',["login"])

/*      loginSubmit() {
        let data = {
          id: this.id,
          password: this.password
        };

        this.axios
          .post( "/api/login", data)
          .then(response => {
            if (response.status === 200) {
              // 로그인 성공시 처리해줘야할 부분
              this.$store.commit("member/login", response.data);
              this.$router.push("/");
            }
          })
          .catch(error =>{
            console.log(error)
          })
    }*/
  }
}
</script>
<style lang="scss" scoped>
.container {
  form {
    h2 {
      font-size: 2rem;
      margin-bottom: 20px;
    }

    label {
      input {
        padding: 10px;
        width: 350px;
        margin-bottom: 10px;
        border: none;
        background: #f5e5a9;
      }
    }

    button {
      margin-top: 15px;
      border: none;
      background: #134775;
      color: #f4f2db;
      font-size: 1.1rem;
      padding: 15px 60px;
      font-weight: bold;
      border-radius: 30px;
      cursor: pointer;
    }

    display: flex;
    width: 100%;
    height: 730px;
    background: #f4f2db;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: #134775;
  }
}
.id-feedback{
  width: 100%;
  margin-top: 0.25rem;
  font-size: 0.875em;
  color: #dc3545;
}
</style>