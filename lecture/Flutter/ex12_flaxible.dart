import 'package:flutter/material.dart';

class ExFlaxible extends StatelessWidget {
  const ExFlaxible({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Column(
            children: [
              Text('flexible, expanded 미사용 경우'),
              Row(children: [
                Container(width: 300, height: 50, color: Colors.green),
                Container(width: 300, height: 50, color: Colors.lightBlue)
              ],),
              SizedBox(height: 20),
              Text('flexible/FlexFit.loose 사용 경우'),
              Row(children: [
                //Flexible : 화면에서 차지해야 하는 영역내에서만 비율적으로 배치하는 위젯
                //fit:FlexFit.loose 기본값으로, 생략 가능
                //-> 여백이 존재한다면 여백 보존!
                Flexible(fit: FlexFit.loose, child: Container(width: 200, height: 50, color: Colors.green)),
                Flexible(child: Container(width: 200, height: 50, color: Colors.lightBlue))
              ],),

              SizedBox(height: 20),

              Text('flexible/FlexFit.tight 사용 경우'),
              Row(children: [
                //fit:FlexFit.tight : 여백 보존 안하고 다 채움
                Flexible(fit: FlexFit.tight, child: Container(width: 150, height: 50, color: Colors.green)),
                Flexible(fit: FlexFit.tight, child: Container(width: 100, height: 50, color: Colors.lightBlue))
              ],),
              SizedBox(height: 20),
              Text('flexible/flex 사용 경우'),
              Row(children: [
                //flex: 생략되어 있다면 비율이 1의 값으로 설정되어 있음
                //flex의 경우는 비율도 중요하지만 비율보다는 해당 위젯의 크기에 영향을 받음
                Flexible(flex: 10, child: Container(width: 150, height: 50, color: Colors.green)),
                Flexible(flex: 40, child: Container(width: 100, height: 50, color: Colors.lightBlue))
              ],),
              SizedBox(height: 20),
              Text('Expanded 사용 경우'),
              Row(children: [
                //Expanded: 위젯의 크기와 상관없이 flex에 대한 비율이 가장 먼저 반영되는 기능
                //flex가 생략되어 있다면 비율은 1!, width상관없이 just flex 비율만 반영됨
                Expanded(child: Container(width: 150, height: 50, color: Colors.lightBlue)),
                Expanded(flex:2, child: Container(width: 100, height: 50, color: Colors.green))
              ],),
            ],
          )),
    );
  }
}
