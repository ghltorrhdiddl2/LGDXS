import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class Ex20Lib extends StatelessWidget {
  const Ex20Lib({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(child: 
        Center(
          child: ElevatedButton(onPressed: (){
            Fluttertoast.showToast(
                msg: "메롱",
                toastLength: Toast.LENGTH_SHORT,
                gravity: ToastGravity.CENTER,
                timeInSecForIosWeb: 1,
                backgroundColor: Colors.red,
                textColor: Colors.white,
                fontSize: 30.0,
            );
          },
              child: Text('눌러', style: TextStyle(fontSize: 25),)),
        )),
    );
  }
}
