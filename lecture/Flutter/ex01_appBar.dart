//화면 디자인을 시작할 수 있는 테마 가져오기
//테마 단축키 -> fm
import 'package:flutter/material.dart';
// import 'package:flutter/cupertino.dart'; 앱 용

//화면을 구성하기 위해서는 기본적으로 widget에 대한 내용을 가지고 있는
//클래스를 물려받아서 생성이 되어야 함

//기본 widget의 디자인이나 기능을 가지고 있는 클래스
//-> 1. statefull : 화면이 계속적으로 수정되는 기능 -> 변화가 있는 화면
//-> 2. stateless : 화면이 정적으로 멈춰져 있는 기능 -> 변화가 없는 화면

//화면을 구성하기 위한 클래스 생성 단축키 -> stl + Enter
class ExAppBar extends StatelessWidget {
  const ExAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Super cat App"),
        backgroundColor: Colors.deepPurpleAccent[100],
        leading: Icon(Icons.menu),
        actions: [Icon(Icons.search), Icon(Icons.settings)],
        foregroundColor: Colors.amberAccent[100], //글씨 색
      ),
    );
  }
}

