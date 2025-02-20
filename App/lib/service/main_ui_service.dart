
import '../models/movie_data.dart';
import 'movieService.dart';


class MainUIService{
  final MovieService movieService;


  MainUIService(this.movieService);

  Future<List<MovieData>> getRemovieList(){
    return movieService.getRemovieDataList();
  }

  Future<List<MovieData>> getMovieData(int offset, int limit){
    return  movieService.movieDataUpdatePage(offset, limit);
  }


}