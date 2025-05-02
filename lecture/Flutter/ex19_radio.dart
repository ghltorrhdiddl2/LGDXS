import 'package:flutter/material.dart';

class Ex19Radio extends StatefulWidget {
  const Ex19Radio({super.key});

  @override
  State<Ex19Radio> createState() => _Ex19RadioState();
}

enum Gender {man, woman}

class _Ex19RadioState extends State<Ex19Radio> {
  Gender g = Gender.woman; //기준이 되는 변수
  bool isChecked = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(child:
        Column(children: [
          //객체 타입
          //value는 radio 버튼의 고유 값
          //groupValue를 바탕으로 value값과 같으면 true, 다르면 false
          //여기에서 v는 Gender type이 된다
          RadioListTile(
              title: Text('남성'),
              value: Gender.man, groupValue: g, onChanged: (v){
                setState(() {
                  g = v!;
                });
          }),
          RadioListTile(
              title: Text('여성'),
              value: Gender.woman, groupValue: g, onChanged: (v){
                setState(() {
                  g = v!;
                });
          }),
          Switch(activeColor: Colors.yellow, value: isChecked, onChanged: (v){
            setState(() {
              isChecked = v!;
            });
          }),
      ],)),
    );
  }
}
