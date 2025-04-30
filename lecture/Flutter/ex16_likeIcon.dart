import 'package:flutter/material.dart';

class ExLikeIcon extends StatelessWidget {
  const ExLikeIcon({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
          child: Stack(
              children: [
                Icon(Icons.favorite_border_sharp, size: 90),
                Positioned(
                  left: 65, top: 8,
                  child: Container(
                  width: 20, height: 20,
                  decoration: BoxDecoration(color: Colors.red, borderRadius: BorderRadius.circular(10)),
                ))
              ]),
      )
    );
  }
}
