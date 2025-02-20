
import 'package:flutter/material.dart';
import '../models/movie_data.dart';

class MovieCard extends StatelessWidget {
  final MovieData movie;

  const MovieCard({super.key, required this.movie});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // 이미지
        ClipRRect(
          borderRadius: BorderRadius.circular(15.0), // 이미지 모서리 둥글게
          child: Image.network(
            movie.posterIMG,
            width: 150, // 고정된 너비
            height: 225, // 고정된 높이
            fit: BoxFit.cover, // 비율 유지하며 이미지를 채움
          ),
        ),
        const SizedBox(height: 8), // 간격 추가
        // 텍스트
        Text(
          movie.title,
          textAlign: TextAlign.center,
          style: const TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
            color: Colors.white,
          ),
        ),
      ],
    );
  }
}