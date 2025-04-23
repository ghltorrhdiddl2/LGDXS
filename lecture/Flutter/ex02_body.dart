import 'package:flutter/material.dart';

class ExBody extends StatelessWidget {
  const ExBody({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //Scaffold의 영역 -> appbar, body, bottom
      appBar: AppBar(
        backgroundColor: Colors.deepPurple,
      ),
      body: Text("Super cat's body area",
        style: TextStyle(fontSize: 40),
      ),
    );
  }
}
