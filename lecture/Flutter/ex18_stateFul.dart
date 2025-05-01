import 'package:flutter/material.dart';

// stl -> Stateless 단축키
// stf -> Stateful 단축키
class ExStateful extends StatefulWidget {
  const ExStateful({super.key});

  //클래스 내부에서 변수를 사용하지 못하므로 반스디 final 키워드 사용!
  final int data1 = 5;

  //밑에서 상태가 변한 화면을 여기서 적용해준다
  @override
  State<ExStateful> createState() => _ExStatefulState();
}

//바뀌는 화면
class _ExStatefulState extends State<ExStateful> {
  //여기서 일반 변수 지정 가능 -> 상태를 체크하기 위한 기능을 상속받아 생성되므로,
  //데이터 값이 언제든 바뀔 수 있음!
  bool isChecked = false;
  bool isChecked2 = false;

  @override
  Widget build(BuildContext context) {
    //build 메소드는 화면을 설계하기 위해서 확정되어있는 내용등만 사용 가능!
    //즉, 변수(int)는 값이 바뀌므로 사용 불가 ==> 문제점 완화방법 : set state()
    //set state() : 상태가 바뀌거나 변수의 데이터가 바뀜을 감지할 수 있는 메소드
    //=> 값이 바뀌면 바뀐 내용을 활용하여 build 메소드에게 화면 재구성을 자동적으로 요청
    return Scaffold(
      body: SafeArea(
          child: Column(children: [
            // value 값 : true(체크O), false(체크X)
            Row(
              children: [
                Checkbox(value: isChecked, onChanged: (value){
                  setState(() {
                    isChecked=value!;
                  });
                }),
                Text('오늘은 맛있는 저녁을 즐기겠습니다.', style: TextStyle(fontSize: 20),)
              ],
            ),
            Row(
              children: [
                Checkbox(value: isChecked2, onChanged: (value){
                  setState(() {
                    isChecked2=value!;
                  });
                }),
                Text('점심 고민중', style: TextStyle(fontSize: 20),)
              ],
            ),
            //상태가 바뀌면 어떤 작업을 할지 로직을 세울 수 있는 영역!
            
          ],)),
    );
  }
}

///////////////////////////////////////////////////////


