/**
 * 
 */
 
 //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


var userslide = {};
// 슬라이드
var recommend_slide = document.querySelector(".slide2");
var slideImg = document.querySelectorAll(".site__item");
var slideWidth = 0;
var slideIndex = 0;
var slideMove = true;
var movement = true;
var slideBtns = document.querySelectorAll(".slide_btn");

console.log("silde : " + slideImg.length);
// 슬라이드 사이즈 구하기
userslide.slideSize = function(){
	for(let i = 0; i < slideImg.length; i++){
		slideWidth += slideImg[i].offsetWidth;
		recommend_slide.style.transition = 'transform 1.5s ease-out';
	}
}

// 슬라이드 재생, 일시정지
if(recommend_slide != null){   // inc에 슬라이드가 있을 때
   /*
   userslide.slideMoveControl = function(movement){
      if(movement == true){
         slideMove = false;
         timer = setInterval(function(){
            userslide.slideNext();
         }, 8000)
      } else {
         slideMove = true;
         clearInterval(timer);
      }
   }*/
   userslide.slideSize();
   //userslide.slideMoveControl(movement);
}

// 슬라이드 움직임 이벤트
userslide.slidePrev = function(){
	slideIndex--;
	if(slideIndex < 0) {
		slideIndex = slideImg.length-1;
	}
	recommend_slide.style.transform = "translateX(" + -300 * slideIndex +"px)";
	userslide.btnColorChange(slideIndex);
}
userslide.slideNext = function(){
	slideIndex++;
	if(slideIndex == slideImg.length) {
		slideIndex = 0;
	}
	recommend_slide.style.transform = "translateX(" + -300 * slideIndex +"px)";
	userslide.btnColorChange(slideIndex);
}
userslide.slideControl = function(btn){
	let control = btn.innerHTML;
	userslide.slideMoveControl(slideMove)
	
	if(control == "Stop") btn.innerHTML = "Play";
	else btn.innerHTML = "Stop";
}
userslide.slideMove = function(moveIndex){
	recommend_slide.style.transform = "translateX(" + -300 * moveIndex +"px)";
	slideIndex = moveIndex;
	userslide.btnColorChange(slideIndex);
}

// 슬라이드 움직이면 버튼 색상 변경
userslide.btnColorChange = function(slideIndex){
	for(let i = 0; i < slideBtns.length; i++){
		slideBtns[i].style.background = "#ddd";	
	}
	slideBtns[slideIndex].style.background = "#fff";
}