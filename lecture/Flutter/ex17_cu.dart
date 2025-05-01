import 'package:flutter/material.dart';

class ExCU extends StatelessWidget {
  const ExCU({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("더보기", style: TextStyle(fontWeight: FontWeight.bold),),
        actions: [IconButton(onPressed: (){}, icon: Icon(Icons.settings),
        onLongPress: (){print('설정버튼 클릭');},)],),

      body: Column(children: [
        Container(
          width: double.infinity, height: 80,
          decoration: BoxDecoration(
            color: Colors.grey[200],
            borderRadius: BorderRadius.circular(15)
          ),
          margin: EdgeInsets.all(8), padding: EdgeInsets.all(24),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text('고양이님', style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),),
              Row(children: [
                Text('Friend', style: TextStyle(fontSize: 16, color: Colors.purple[400], fontWeight: FontWeight.bold),),
                SizedBox(width: 5),
                Text('155p', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold))
              ],)
          ],),
        ),
        SizedBox(height: 30),
        Container(alignment: Alignment.bottomLeft,
            margin: EdgeInsets.all(15),
            child: Text('서비스', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold))),
        SizedBox(height: 30),
        Container(
          width: double.infinity, height: 80,
          child: Row(children: [
            SizedBox(width: 25),
            Column(children: [
              Stack(children: [
                Icon(Icons.copyright, size: 50),
                Positioned(bottom: 3, right: 3,
                    child: Container(
                      width: 15, height: 15,
                      decoration: BoxDecoration(color: Colors.pinkAccent, borderRadius: BorderRadius.circular(3)),
                      child: Center(child: Text('N', style: TextStyle(fontSize: 13, color: Colors.white, fontWeight: FontWeight.bold),)),
                    ))
              ]),
              Text('포인트 충전소', style: TextStyle(fontSize: 12, fontWeight: FontWeight.bold),),
          ],),
            SizedBox(width: 25),
            Column(children: [
              Stack(children: [
                Icon(Icons.chat, size: 50),
                Positioned(bottom: 3, right: 3,
                    child: Container(
                      width: 15, height: 15,
                      decoration: BoxDecoration(color: Colors.pinkAccent, borderRadius: BorderRadius.circular(3)),
                      child: Center(child: Text('N', style: TextStyle(fontSize: 13, color: Colors.white, fontWeight: FontWeight.bold),)),
                    ))
              ]),
              Text('상담하기', style: TextStyle(fontSize: 12, fontWeight: FontWeight.bold))
          ],),
          ],),
        )
      ],),
    );
  }
}