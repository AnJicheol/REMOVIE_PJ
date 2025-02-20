
import 'package:removie_app/models/cinema_data.dart';
import 'package:removie_app/models/release_date_data.dart';
import 'package:removie_app/service/cinema_api_service.dart';
import 'package:removie_app/service/release_date_api_service.dart';

class DetailUIService{
  final CinemaApiService cinemaApiService;
  final ReleaseDateApiService releaseDateApiService;

  DetailUIService(this.cinemaApiService, this.releaseDateApiService);

  Future<List<CinemaData>> getCinemaDataList(String movieCode){
    return cinemaApiService.fetchCinemaData(movieCode);
  }

  Future<List<ReleaseDateData>> getDateList(String movieCode){
    return releaseDateApiService.fetchReleaseDate(movieCode);
  }
}