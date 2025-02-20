import 'package:removie_app/service/movie_api_service.dart';

import '../models/movie_data.dart';
import '../models/release_data.dart';
import '../page_limit_exception.dart';
import '../repository/movie_repository.dart';
import '../repository/release_repository.dart';

class MovieService {
  final MovieApiService movieApiService;
  final MovieDataRepository movieRepository;
  final ReleaseRepository releaseRepository;

  MovieService(this.movieApiService, this.movieRepository, this.releaseRepository);


  Future<List<MovieData>> getRemovieDataList() async{

    List<ReleaseData> releaseDataList = releaseRepository.getRemovieList();


    List<MovieData> movieList = await movieApiService.fetchMovieData(movieRepository.getMissingMovieCodes(releaseDataList));
    movieRepository.saveMovieData(movieList);

    return movieRepository.getMovieDataList(releaseDataList);
  }

  Future<List<MovieData>> movieDataUpdatePage(int offset, int limit) async{
    List<ReleaseData> releaseDataList = getCutPage(
        releaseRepository.getReleaseDataList(),
        offset,
        limit
    );

    List<MovieData> movieList = await movieApiService.fetchMovieData(
        movieRepository.getMissingMovieCodes(
            releaseDataList
        ));
    movieRepository.saveMovieData(movieList);

    return movieRepository.getMovieDataList(releaseDataList);
  }

  List<ReleaseData> getCutPage(List<ReleaseData> releaseDataList, int offset, int limit) {
    if(offset >= releaseDataList.length) throw PageLimitException;

    return releaseDataList.sublist(offset, (limit < releaseDataList.length)? limit : releaseDataList.length);
  }

}

