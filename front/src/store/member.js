import axios from "axios";
import router from "../routes/index"
import VueCookies  from "vue3-cookies"
const storage = window.sessionStorage;
export default {
    // module
    namespaced:true,
    state: {
        id: '',
        name: '',
        token: '',
        loginSuccess: false,
        loginError: false
    },
    mutations: {
/*        login: function (state, payload) {
            state.id = payload.id
            state.name = payload.name
            //state.token = payload.token
        },*/
        loginSuccess(state,{id,password,token}) {

            state.token=token;
            state.loginSuccess = true;
            state.loginError = false;
        },
        loginError(state) {
            state.loginSuccess = false;
            state.loginError = true;
        }
    },
    actions: {
        login({commit}, payload) {
            console.log(payload);
            let data = {
                id: payload.id,
                password: payload.password
            };

            axios.post('/api/login', data)
                .then(response => {
                    if (response.status === 200) {
                        commit('loginSuccess', {
                            token: response.data.token
                        });
                        console.log(response.data);
                        router.push("/");
                        const token = response.data.token
                        const authId = response.data.id
                        const auth = response.data.authority
                        storage.setItem("token",token);
                        storage.setItem("authId",authId);
                        storage.setItem("auth",auth);
                    }else{
                        commit('loginError', {
                        });
                        alert("로그인 실패")
                    }

                })
                .catch(error =>{
                    commit('loginError', {
                    });
                    alert("로그인 실패")
                    console.log(error)
                })

        },
        refreshLogin({commit}, payload) {
            console.log(payload)
            commit('loginSuccess', {

            });
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getUserName: state => state.id,
        getUserPass: state => state.password
    },
}