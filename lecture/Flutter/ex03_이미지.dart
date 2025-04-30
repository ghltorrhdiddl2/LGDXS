//화면을 디자인 하기 위한 파일!
//테마 가져오기 -> 화면 종류 지정하기
//1. 화면 디자인을 위한 테마 가져오기!
import 'package:flutter/material.dart';

//2.화면 종류 지정하기
class ExImage extends StatelessWidget {
  const ExImage({super.key});

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
        body: Center(
          child:
              Column(children: [
                Image.asset('images/반갑.jpg'), Text('반가워요'),],))
            
    ));
  }
}

