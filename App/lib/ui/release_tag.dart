
import 'package:flutter/material.dart';
import 'package:glassmorphism/glassmorphism.dart';

class ReleaseTag extends StatelessWidget{
  const ReleaseTag({super.key});

  @override
  Widget build(BuildContext context) {

    return GlassmorphicContainer(
      width: 112,
      height: 40,
      border: 2,
      borderRadius: 8,
      blur: 2,
      alignment: Alignment.center,

      linearGradient: const LinearGradient(
        colors: [
          Color(0x33D9D9D9),
          Color(0x33D9D9D9),
        ],
        stops: [0.1, 1],
      ),
      borderGradient: const LinearGradient(
        colors: [
          Color(0xFFFFCA10),
          Color(0xFFFFCA10),
        ],
      ),

      child: const Text(
        '상영중',
        style: TextStyle(
          color: Colors.white,
          fontSize: 24,
        ),

      ),

    );
  }
}