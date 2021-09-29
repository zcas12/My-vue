import axios from "axios";

export default {
    // module
    namespaced:true,
    //data
    state: () => ({
        order: [],
        loading: false,
        theItem:{}
    }),
    //computed
    getters:{

    },
    //methods mutation, action
    // mutations 에서는 state만 수정
    mutations:{
        updateState(state, payload){
            Object.keys(payload).forEach(key => {
                state[key] = payload[key]
            })
        }
    },
    //비동기
   actions:{
       async itemDetailWidthId({state, commit}, payload){
           console.log(payload)
           try{
               const res = await _fetchItem(payload)
               commit('updateState',{
                   theItem:res.data.data
               })
           }catch (error) {
               commit('updateState',{
                   theItem:{}
               })
           }finally{
               commit('updateState', {
                   loading:false
               })
           }
       }
    }
}

function _fetchItem(payload){
    const{id} = payload
    const url = `/api/itemDetail/${id}`

    return new Promise((resolve, reject) => {
       axios.get(url)
           .then(res => {
               if(res.data.Error){
                   reject(res.data.Error)
               }
               resolve(res)
           })
           .catch(err => {
               reject(err.message)
           })
    });
}