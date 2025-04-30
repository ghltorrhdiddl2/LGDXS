import 'package:flutter/material.dart';

class ExStack extends StatelessWidget {
  const ExStack({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Stack(
            alignment: Alignment.center,
            children: [
              //Positioned: 하나의 요소만 Positioned를 갖는게 아니라
              //여러 요소가 같이 Positioned를 가져야 기능을 완벽히 수행가능
              Positioned(
                top: 80,
                child: Container(width: 100, height: 100, color: Colors.green)),
              Positioned(
                top: 120, left: 20, bottom: 120,
                child: Container(width: 80, height: 80, color: Colors.red)),
            ],
          ),
      ),
    );
  }
}
