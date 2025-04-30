import 'package:flutter/material.dart';

class ExSizedbox extends StatelessWidget {
  const ExSizedbox({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Column(
            children: [
              Row(
                children: [
                  Text('첫번째 text 영역'),
                  //SizedBox를 사용하는 목적
                  //1. 위젯과 위젯 간의 공간을 확보하기 위해 사용
                  //2. Container와 같은 위젯들의 크기를 제한하기 위해(안에 버튼 들어갈 수 있음)
              
                  //1번을 목적으로 사용한 경우
                  SizedBox(
                    width: 30, height: 30,
                    child: Container(color: Colors.grey),), //두 text 사이에 띄어쓰기 주는 효과
                  Text('두번째 text 영역'),
                ],
              ),
              
              //2번을 목적으로 사용한 경우
              SizedBox(
                child: ElevatedButton(onPressed: (){}, child: Text('button')),
                width: 200, height: 50
              )
            ],
          )),
    );
  }
}
