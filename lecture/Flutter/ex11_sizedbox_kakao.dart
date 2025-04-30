import 'package:flutter/material.dart';

class Sizedbox_kakao extends StatelessWidget {
  const Sizedbox_kakao({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Container(
            decoration: BoxDecoration(
                color: Colors.yellow[600],
                borderRadius: BorderRadius.circular(16)
            ),
            width: double.infinity, height: 60,
            margin: EdgeInsets.all(16),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Image.asset('images/klogo.png', width: 30, height: 30),
                SizedBox(width: 20),
                Text('카카오톡으로 로그인하기', style: TextStyle(fontSize: 15))
              ],)
          )),
    );
  }
}
