<template>
  <div class="container">
    <router-link to="/QnAWrite">
      <button class="btn btn-primary" >작성하기</button>
    </router-link>
    <table class="table table-striped">
      <thead>
      <tr>
        <th >&nbsp;&nbsp;&nbsp;&nbsp; 질문</th>
        <th style="width: 120px">작성자</th>
        <th style="width: 120px">날짜</th>
      </tr>
      </thead>
<!--      <tbody>
      <tr v-for="(item,idx) in list" :key="item.id" >
        <td>{{item.id}}</td>
        <td>{{item.title}}</td>
        <td>{{item.name}}</td>
        <td>{{item.date}}</td>
      </tr>
      </tbody>-->
    </table>
    <vue-collapsible-panel-group base-color="#314144" accordion>
      <vue-collapsible-panel v-for="(item,idx) in list" :key="item.id" :expanded="false" ref="collapse"  >
        <template #title>
          <span class="que-icon">Q</span>
          <span class="vcp-title">
            {{item.title}}
          </span>
          <span class="vcp-name">
            {{item.name}}
          </span>
          <span class="vcp-date">
            {{item.date}}
          </span>
          <button :id="item.id" class="btn btn-danger" @click="deleteQnA(item.id)">X</button>
        </template>
        <template #content>
          {{item.content}}

          <div v-show="item.answer" class="ans-icon">A</div>
          <div style="display: flex">
            <button  v-if="!item.answer" class="btn btn-primary btn-answer" @click="answerOn">답변</button>
          </div>
          <div v-show="item.answer">{{item.answer}}</div>

          <form v-show="!item.answer" v-on:submit="createAnswer(item.id,$event)">
            <div v-show="isAnswerOn">
              <textarea  id="answer" v-model="answer" class="form-control" placeholder="답변을 입력하세요" ></textarea>
              <button class="btn btn-primary btn-answer">저장</button>
            </div>
          </form>
        </template>

      </vue-collapsible-panel>
    </vue-collapsible-panel-group>
  </div>
</template>

<script>
import '@dafcoe/vue-collapsible-panel'
export default{
  name:'QnAList',
  created() {
    this.fetchData()
  },
  data(){
    return{
      selected:false,
      list:[],
      item:[],
      answer:'',
      isAnswerOn: false,
    }
  },
  methods:{
    fetchData(){ // QnA 리스트 출력
      this.axios.get('/api/v1/QnA')
          .then(response => {
            console.log(response.data.data);
            const result = response.data.data;
            this.list = result;
          })
          .catch(error => {
            console.log(error)
          })
    },
    deleteQnA(idx){ // QnA 삭제
      if (event.stopImmediatePropagation) event.stopImmediatePropagation();
      else event.isImmediatePropagationEnabled = false; // IE 대응
      if (!confirm("삭제 하시겠습니까?")) {

      } else {
        this.axios.delete('/api/v1/QnA/' + idx)
            .then(response => {
              console.log(response.data);
              this.fetchData();
            })
            .catch(error => {
              console.log(error);
            })
      }



    },
    answerOn(){
      this.isAnswerOn = !this.isAnswerOn;
    },
    createAnswer(idx,event){  //QnA 답변 기능

      //this.$refs.collapse.isExpanded = false;
      event.target.reset();
      this.axios.put('/api/v1/QnA/'+idx, {answer: this.answer})
        .then(response=>{
          console.log(response);
          alert("작성되었습니다.")
          this.$router.go(0);
        })
        .catch(error => {
          alert("작성안됨.")
          console.log(error);
        })

    }
  },
  computed:{
  }

}
</script>
<style lang="scss" scoped>
@import "~@dafcoe/vue-collapsible-panel/dist/vue-collapsible-panel.css";
.container {
  margin-top: 10px;
}
.btn{
  width: 120px;
  //height: 50px;
  font-weight: 700;
  flex-shrink: 0;
  color: #fff;
  float: right;
}
.btn-danger{
  width: 40px;
  height: 30px;
  margin-right: 10px;
  line-height: 0.3;
}
.btn-answer{
  width: 60px;
  height: 30px;
  margin: 10px;
  line-height: 0.3;
}
.que-icon{
  color: #4695ad;
  font-weight: 700;
  margin-right: 15px;
}
.ans-icon{
  color: #4695ad;
  font-weight: 700;
  margin-right: 15px;
  margin-top: 10px;
}
.vcp-title{
  width: 100%;
}
.vcp-name{
  width: 120px;
}
.vcp-date{
  width: 120px;
}
.vcp__body{
  height: 100% !important;
}
</style>