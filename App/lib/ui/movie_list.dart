
import 'package:flutter/cupertino.dart';
import '../models/movie_data.dart';
import 'movie_card.dart';

class MovieList extends StatelessWidget {
  final List<MovieData> movieList;

  const MovieList({super.key, required this.movieList});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(

      scrollDirection: Axis.horizontal,
      itemCount: movieList.length,
      itemBuilder: (context, index) {

        final movie = movieList[index];
        return Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8.0),
          child: MovieCard(movie: movie),
        );
      },
    );
  }
}