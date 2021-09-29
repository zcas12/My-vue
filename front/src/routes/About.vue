<template>
  <div class="container">
    <div class="content">
      <div class="left-textEl">
        <h2 style="font-size:40px">
          <span>About <span>소개</span></span>
        </h2>
        <p style="line-height: 1.5em;">&nbsp</p>
        <p style="line-height: 1.5em;font-size: 15px;">
          <span>
            여기를 클릭해 내용을 입력하세요. 마우스로 텍스트 상자의 위치와 크기를 변경하고, 텍스트 에디터에서 글꼴과 색상을 선택해 보세요. 다양한 한글 글꼴을 사용하려면, 글꼴 언어를 한국어로 지정하세요. 나만의 콘텐츠, 나만의 스타일을 위한 나만의 홈페이지를 완성해 보세요!
          </span>
        </p>
        <p style="line-height: 0.1em;">&nbsp</p>
        <p style="line-height: 1.5em;font-size: 15px;">
          <span>
            사용자 친화적인 드래그-앤-드롭 방식의 홈페이지 제작 도구를 사용해 코딩없이 홈페이지를 만들어보세요. 간단한 조작으로 데스크톱 홈페이지를 모바일 기기에 최적화 할수도 있습니다. 홈페이지를 게시하기전, 방문자들의 입장에서 홈페이지를 미리 사용해보세요. '미리보기' 메뉴를 통해 홈페이지를 게시하기전에 방문자들이 사용하는 그대로 홈페이지의 기능을 시험해볼수 있습니다. 전세계 7000만명 이상이 홈페이지 제작을 위해 Wix를 선택한 이유를 확인해보세요.
          </span>
        </p>
      </div>
      <div class="right-el">
        <img src="https://static.wixstatic.com/media/cda177_d41dfc6ad87643bcb876790af0c7ef5b.jpg/v1/fill/w_448,h_301,al_c,q_80,usm_0.66_1.00_0.01/cda177_d41dfc6ad87643bcb876790af0c7ef5b.webp"/>
      </div>
      <div class="left-el-FAQ">
        <h2 style="font-size: 40px;">
          <span style="letter-spacing: 0.05em;">FAQ</span>
        </h2>
        <h6 style="letter-spacing: 1.5em;">&nbsp</h6>
        <h6 style="font-size: 16px;"><span style="letter-spacing: 1.8px;">국제 배송도 가능합니까?</span></h6>
        <p style="line-height: 0.1em;">&nbsp</p>
        <p style="line-height: 1.5em;"><span>여기를 클릭해 내용을 입력하세요. 마우스로 텍스트 상자의 위치와 크기를 변경하고, 텍스트 에디터에서 글꼴과 색상을 선택해 보세요. 다양한 한글 글꼴을 사용하려면, 글꼴 언어를 한국어로 지정하세요.</span></p>
      </div>
      <div class="right-el-FAQ">
        <h6 style="font-size: 16px;"><span style="letter-spacing: 1.8px;">주문 확인은 어떻게 하나요?</span></h6>
        <p style="line-height: 0.1em;">&nbsp</p>
        <p style="line-height: 1.5em;"><span>여기를 클릭해 내용을 입력하세요. 마우스로 텍스트 상자의 위치와 크기를 변경하고, 텍스트 에디터에서 글꼴과 색상을 선택해 보세요. 다양한 한글 글꼴을 사용하려면, 글꼴 언어를 한국어로 지정하세요.</span></p>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container{
  .content {
    display: grid;
    width: 100%;
    height: auto;
    min-height: 700px;
    grid-template-rows: repeat(2, min-content) 1fr;
    grid-template-columns: 100%;
    padding-bottom: 20px;
    .left-textEl {
      width: 430px;
      height: auto;
      left: 40px;
      margin: 44px 0px 10px 10px;
      grid-area: 1 / 1 / 2 / 2;
      justify-self: start;
      align-self: start;
    }
    .right-el{
      position: relative;
      margin: 138px 0px 10px 10px;
      left: 490px;
      grid-area: 1 / 1 / 2 / 2;
      justify-self: start;
      align-self: start;
    }
    .left-el-FAQ{
      width: 430px;
      position: relative;
      margin: 0px 0px 10px 10px;
      grid-area: 3 / 1 / 4 / 2;
      justify-self: start;
      align-self: start;
    }
    .right-el-FAQ{
      width: 430px;
      position: relative;
      margin: 89px 0px 0 10px;
      left: 490px;
      grid-area: 3 / 1 / 4 / 2;
      justify-self: start;
      align-self: start;
    }
  }
}
</style>