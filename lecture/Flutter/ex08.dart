import 'package:flutter/material.dart';

class Icon_call extends StatelessWidget {
  const Icon_call ({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Row(
            children: [
              Container(
                width: 120, height: 120,
                margin: EdgeInsets.fromLTRB(32, 32, 0, 0),
                decoration: BoxDecoration(
                    color: Colors.green[500],
                    borderRadius: BorderRadius.circular(40)
                ),
                child: Center(child: Icon(Icons.call, size: 80, color: Colors.white)),
              ),
              Container(
                width: 120, height: 120,
                margin: EdgeInsets.fromLTRB(32, 32, 0, 0),
                decoration: BoxDecoration(
                  color: Colors.red[500],
                  borderRadius: BorderRadius.circular(40)
                ),
                child: Center(child: Icon(Icons.camera_alt_outlined, size: 80, color: Colors.white)),
              )
            ],
          )
      ),
    );
  }
}


