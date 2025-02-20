//
// import 'package:flutter/material.dart';
//
// class CinemaLogo extends StatelessWidget {
//
//   final String name;
//   final Color topColor;
//   final Color bottomColor;
//
//   const CinemaLogo(this.name, this.topColor, this.bottomColor, {super.key});
//
//   @override
//   Widget build(BuildContext context) {
//     // 텍스트 스타일 정의
//     const textStyle = TextStyle(
//       fontSize: 32,
//       fontWeight: FontWeight.bold,
//     );
//
//     // 텍스트 크기 계산
//     final textPainter = TextPainter(
//       text: TextSpan(text: name, style: textStyle),
//       textDirection: TextDirection.ltr,
//     )..layout();
//
//     // 계산된 너비와 높이
//     final textWidth = textPainter.width;
//     final textHeight = textPainter.height;
//
//     return Text(
//       name,
//       style: textStyle.copyWith(
//         foreground: Paint()
//           ..shader = LinearGradient(
//             colors: [
//               topColor, // 시작 색상
//               bottomColor, // 끝 색상
//             ],
//             begin: Alignment.topCenter,
//             end: Alignment.bottomCenter,
//           ).createShader(Rect.fromLTWH(0, 0, textWidth, textHeight)), // 동적 크기 적용
//       ),
//     );
//   }
// }