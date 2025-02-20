import 'dart:ui';

import 'package:flutter/material.dart';

class Glassmorphism extends StatelessWidget{
  final Color color = Colors.white;
  final double blurSigmaX = 10.0;
  final double blurSigmaY = 10.0;
  final double borderWidth = 1.5;
  final Color borderColor = Colors.white;

  const Glassmorphism({super.key});

  @override
  Widget build(BuildContext context) {

    return BackdropFilter(
      filter: ImageFilter.blur(sigmaX: blurSigmaX, sigmaY: blurSigmaY),
      child: Container(
        decoration: BoxDecoration(
          color: color.withOpacity(0.2), // 투명도
          border: Border.all(
            color: borderColor.withOpacity(0.3), //테두리 투명도
            width: borderWidth,
        )

        ),
      ),
    );
  }
}