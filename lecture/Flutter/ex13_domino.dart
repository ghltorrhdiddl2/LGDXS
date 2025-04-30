import 'package:flutter/material.dart';

class Domino extends StatelessWidget {
  const Domino({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Container(width: double.infinity, height: 60,
          color: Colors.grey[200],
          child: Row(mainAxisAlignment: MainAxisAlignment.center,
            children: [
            Container(
              child: Column(children: [
                Text('아이유와 도미노를 더 맛있게', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                SizedBox(height: 2),
                Text('도미노 매니아되고 ~40% 할인받자!', style: TextStyle(fontSize: 18, fontWeight: FontWeight.normal, color: Colors.grey))
            ],),),
              SizedBox(width: 80, height: 60, child: Image.asset('images/domino.png')),
          ],),
        )),
    );
  }
}
