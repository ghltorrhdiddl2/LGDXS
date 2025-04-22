
//JS를 작성하는 3가지 방법(내부, 외부, 인라인)
//1.내부방식 - HTML파일 안에 <script>태그를 통해서 직접 작성하는 방식
//단점 : 개발자도구에 코드가 노출됨, 유지보수 불편
//사용처: 로컬개발할때 사용 -> 내컴퓨터에서 나혼자 개발할 때
// const print = ()=>{
//     alert("함수가 호출되었습니다");
// }

// document.querySelector("#btn").addEventListener("click",print);

//2.인라인방식 - 태그안에 직접 JS코드를 작성하는 방법
//단점 : 코드가 노출됨, 유지보수 불편, 이벤트 한개밖에 처리하지 못함
//사용처: 간단한 내장함수(alret,confirm)는 사용해도 됨 -> 권장하지는 않음

//3.외부방식 - 철저하게 파일들의 역할을 분리하는 방식 -> 각각 파일로 제작해서 연결
//사용법: 파일로 제작 후, <script src='제작한 파일명'></script>
//장점 : 위에 있는 단점들을 다 보완!(노출x,유지보수 편리o)
//주의점: 1)파일을 불러오는 script태그에는 코드를 작성x -> 충돌나기 때문
//2)항상 태그가 다 만들어진 후에, 코드를 불러오자 -> </body>전에 작성
//실습목표 : 스타일을 필요할때마다 갈아끼우는 원리
let body = document.getElementsByTagName("body")[0];

const change_mode = () => {
    //실습 : 버튼을 눌렀을 때 클래스가 normal이면 dark로 아니면 noraml
    //조건 : 버튼의 이름도 변경
    if (body.className == "normal") {
        body.className = "dark";
        document.getElementById("btn").innerText = "되돌리기";
    } else {
        body.className = "normal";
        document.getElementById("btn").innerText = "다크모드 변경";
    }
}
document.getElementById("btn").addEventListener("click", change_mode);
