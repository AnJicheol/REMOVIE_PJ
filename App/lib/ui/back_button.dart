import 'package:flutter/material.dart';
import 'package:glassmorphism/glassmorphism.dart';

class CustomBackButton extends StatelessWidget {
  const CustomBackButton({super.key});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => Navigator.of(context).pop(),
      child: GlassmorphicContainer(
        width: 40,
        height: 40,
        borderRadius: 25,
        blur: 2, // 고정된 블러 강도
        alignment: Alignment.center,
        border: 0.1,
        linearGradient: const LinearGradient(
          colors: [
            Color(0x33D9D9D9),
            Color(0x33D9D9D9),
          ],
          stops: [0.1, 1],
        ),
        borderGradient: LinearGradient(
          colors: [
            Colors.white.withOpacity(0.1),
            Colors.black.withOpacity(0.2),
          ],
        ),
        child: const Text(
          '<',
          style: TextStyle(
            color: Colors.white,
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ),

        ),
      ),
    );
  }
}