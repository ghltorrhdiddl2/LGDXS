//테마 불러오기 -> 화면생성(stl) -> 클래스 이름: ExButton
//-> main.dart 열결 수정 => 검정색 화면
import 'package:flutter/material.dart';

class ExButton extends StatelessWidget {
  const ExButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(child: Center(
        child: Column(
          children: [IconButton(onPressed: (){
            print('버튼이 눌렸습니다!');
          }, icon: Icon(Icons.add_box_rounded)),
            TextButton(onPressed: (){}, child: Text('Text Button')),
            ElevatedButton(onPressed: (){}, child: Text('Elevated Button')),
            OutlinedButton(onPressed: (){}, child: Text('Outline Button'))
          ],
        ),
      )),
    );
  }
}
