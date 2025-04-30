import 'package:flutter/material.dart';

class ExDomino extends StatelessWidget {
  const ExDomino({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Container(
            width: double.infinity, height: 90,
            margin: EdgeInsets.all(14),
            padding: EdgeInsets.fromLTRB(0, 0, 14, 0),
            decoration: BoxDecoration(
              color: Colors.grey[200],
              borderRadius: BorderRadius.circular(14),
            ),
            child: Row(
              children: [
              Expanded(
                flex: 2,
                child: Column( mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text('아이유와 도미노를 더 맛있게', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),),
                    SizedBox(height: 2),
                    Text('도미노 매니아되고 ~40% 할인받자!', style: TextStyle(fontSize: 13, color: Colors.grey[500]),),
                ],),
              ),
              Expanded(
                flex: 1,
                  child: Image.asset('images/domino.png'))
            ],),
          ),)
    );
  }
}
