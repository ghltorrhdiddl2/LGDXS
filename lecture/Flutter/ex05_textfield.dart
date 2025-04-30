import 'package:flutter/material.dart';

class ExtextFild extends StatelessWidget {
  const ExtextFild({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(padding: const EdgeInsets.fromLTRB(50, 100, 50, 100),
          child: Column(
            children: [
              TextField(
                decoration: InputDecoration(label: Icon(Icons.email),
                hintText: 'email을 입력해주세요',
                    hintStyle: TextStyle(color: Colors.grey)
                ),
              ),
              TextField(
                decoration: InputDecoration(label: Icon(Icons.password)),
              ),
              Row(
                children: [
                  ElevatedButton(onPressed: (){}, child: Text('Join')),
                  ElevatedButton(onPressed: (){}, child: Text('Login'))
                ],
              ),
            ],
          ),
      ),),
    );
  }
}
