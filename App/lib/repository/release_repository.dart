import 'package:hive/hive.dart';
import '../models/new_movie_data.dart';
import '../models/ranking_data.dart';
import '../models/release_data.dart';
import '../models/retire_movie_data.dart';

class ReleaseRepository{
  final Box<ReleaseData> releaseBox;

  ReleaseRepository(this.releaseBox);

  Future<void> initReleaseBox(List<ReleaseData> releaseDataList) async{
    await releaseBox.clear();
    for (var data in releaseDataList) {
      await releaseBox.add(data);
    }
  }

  List<ReleaseData> getReleaseDataList(){
    return releaseBox.values.toList();
  }

  Future<void> addNewMovie(List<NewMovie> newMovieList) async {
    final releaseList = getReleaseDataList();

    for(NewMovie data in newMovieList){
      releaseList.insert(data.ranking, ReleaseData(movieCode: data.movieCode, removie: data.removie));
    }
    await initReleaseBox(releaseList);
  }

  Future<void> removeByMovieCode(List<RetireMovie> retireMovieList) async {
    final releaseList = getReleaseDataList();

    for(RetireMovie retireMovie in retireMovieList){
      releaseList.removeWhere((data) => data.movieCode == retireMovie.movieCode);
    }
    await initReleaseBox(releaseList);
  }

  Future<void> rankingChange(List<RankingSync> rankingSyncList) async {
    final releaseList = getReleaseDataList();

    for (RankingSync rankingSync in rankingSyncList) {

      final existingData = releaseList.firstWhere(
            (data) => data.movieCode == rankingSync.movieCode);

      releaseList.removeWhere((data) => data.movieCode == rankingSync.movieCode);

      releaseList.insert(
        rankingSync.insertRanking,
        existingData,
      );
    }
    await initReleaseBox(releaseList);
  }

  List<String> getMovieCodesWithRemovieTrue(List<ReleaseData> releaseDataList) {
    return releaseDataList
        .where((release) => release.removie)
        .map((release) => release.movieCode)
        .toList();
  }

  List<ReleaseData> getRemovieListByReleaseData(List<ReleaseData> releaseDataList) {
    return releaseDataList.where((data) => data.removie).toList();
  }

  List<ReleaseData> getRemovieList() {

    final data = releaseBox.values;
    final removieList = data.where((data) => data.removie).toList();
    return removieList;
  }
}