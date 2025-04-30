import 'package:flutter/material.dart';

class ExKeyPad extends StatelessWidget {
  const ExKeyPad({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('KeyPad'), backgroundColor: Colors.green[200],),
      body: Column(children: [
        Expanded(flex: 1, child: Container()),
        Expanded(flex: 2, child: Container(
          decoration: BoxDecoration(
            color: Colors.green[100],
            borderRadius: BorderRadius.circular(14),
          ),
          margin: EdgeInsets.all(10),
          child: Column(children: [
            Expanded(
              child: Row(children: [
                Expanded(child: TextButton(onPressed: (){}, child: Text('1', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('2', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('3', style: TextStyle(fontSize: 30))))
              ],),
            ),
            //두 번째 라인의 키패스 생성
            Expanded(
              child: Row(children: [
                Expanded(child: TextButton(onPressed: (){}, child: Text('4', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('5', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('6', style: TextStyle(fontSize: 30))))
              ],),
            ),
            //세 번째 라인의 키패스 생성
            Expanded(
              child: Row(children: [
                Expanded(child: TextButton(onPressed: (){}, child: Text('7', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('8', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('9', style: TextStyle(fontSize: 30))))
              ],),
            ),
            Expanded(
              child: Row(children: [
                Expanded(child: TextButton(onPressed: (){}, child: Text('*', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('0', style: TextStyle(fontSize: 30)))),
                Expanded(child: TextButton(onPressed: (){}, child: Text('#', style: TextStyle(fontSize: 30))))
              ],),
            ),
          ],),
        ),),
      ],),
    );
  }
}
