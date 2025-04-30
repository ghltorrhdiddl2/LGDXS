import 'package:flutter/material.dart';

class ExContainer extends StatelessWidget {
  const ExContainer({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(child: Column(
        children: [
          Text('flutter',
            style: TextStyle(backgroundColor: Colors.amber,
              fontSize: 40),),
            Container(
              //child 요소는 필수적인 요소는 아니다!
              child: Text('flutter', style: TextStyle(fontSize: 20),),
              color: Colors.green, width: 80, height: 80,
              //Container 위젯은 크기를 가지고 있지 않다면 전체적으로 크기를 잡을 수 있다!
            ),
        ],
      )),
    );
  }
}
