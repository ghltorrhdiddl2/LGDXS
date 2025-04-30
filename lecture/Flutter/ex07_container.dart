import 'package:flutter/material.dart';

 class Container02 extends StatelessWidget {
   const Container02 ({super.key});

   @override
   Widget build(BuildContext context) {
     return Scaffold(
       body: SafeArea(
           child: Container(
             //Container 위젯 사용시 주의사항!
             //color 속성은 단독적으로 사용하거나 아니면 decoration 속성에 포함하여 사용해야 함!
             width: 200, height: 200, //color: Colors.orangeAccent,
             margin: EdgeInsets.fromLTRB(10, 10, 0, 0), //주황색 컨테이너박스 왼쪽 여백 만들기
             decoration: BoxDecoration(
               border: Border.all(color: Colors.purple, width: 5),
               color: Colors.orangeAccent,
               //borderRadius: BorderRadius.all(Radius.circular(30)), //컨테이너박스 사각형 둥글게
               borderRadius: BorderRadius.circular(20)
             ),
             child: Center(child: Text('Container', style: TextStyle(fontSize: 30))),
           )),
     );
   }
 }
