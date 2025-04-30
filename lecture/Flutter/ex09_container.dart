import 'package:flutter/material.dart';

class Ex09Container extends StatelessWidget {
  const Ex09Container({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Container(
            //double.infinity : 화면에 맞춰 넓이를 지정하는 기능
            width: double.infinity, height: 80,
            color: Colors.grey,
            margin: EdgeInsets.all(14),
          )),
    );

  }
}
