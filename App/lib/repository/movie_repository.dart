import 'package:hive/hive.dart';
import 'package:removie_app/models/movie_data.dart';

import '../models/release_data.dart';

class MovieDataRepository{
  final Box<Map<String, dynamic>> movieDataBox;

  MovieDataRepository(this.movieDataBox);

  Future<void> saveMovieData(List<MovieData> movieDataList) async {

    await Future.wait(movieDataList.map((movieData) {
      return movieDataBox.put(
        movieData.movieCode,
        {
          'title': movieData.title,
          'posterIMG': movieData.posterIMG,
          'info': movieData.info,
        },
      );
    }));
  }

  MovieData? getMovieData(String movieCode) {
    final data = movieDataBox.get(movieCode);
    if (data != null) {
      return MovieData(
        movieCode: movieCode,
        title: data['title'],
        posterIMG: data['posterIMG'],
        info: data['info'],
      );
    }
    return null;
  }

  List<MovieData> getMovieDataList(List<ReleaseData> releaseDataList) {
    List<String> movieCodes = releaseDataList.map((release) => release.movieCode).toList();

    List<MovieData> movieDataList = movieCodes.map((movieCode) {
      var data = movieDataBox.get(movieCode);
      if (data != null) {
        return MovieData(
          title: data['title'],
          posterIMG: data['posterIMG'],
          movieCode: movieCode,
          info: data['info'],
        );
      } else {
        throw Exception('MovieData not found for movieCode: $movieCode');
      }
    }).toList();

    return movieDataList;
  }

  List<String> getMissingMovieCodes(List<ReleaseData> releaseDataList) {

    List<String> missingMovieCodes = releaseDataList
        .where((release) => !movieDataBox.containsKey(release.movieCode))
        .map((release) => release.movieCode)
        .toList();

    return missingMovieCodes;
  }

}

