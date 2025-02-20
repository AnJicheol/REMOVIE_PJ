import 'package:removie_app/models/release_data.dart';
import 'package:removie_app/repository/version_repository.dart';
import 'package:removie_app/service/release_api_service.dart';


import '../models/new_movie_data.dart';
import '../models/ranking_data.dart';
import '../models/retire_movie_data.dart';
import '../repository/release_repository.dart';
import 'movie_sync_api_service.dart';

class MovieSyncService {
  final MovieSyncApiService apiService;
  final VersionRepository versionRepository;
  final ReleaseApiService releaseApiService;
  final ReleaseRepository releaseRepository;


  MovieSyncService(
      this.apiService,
      this.versionRepository,
      this.releaseApiService,
      this.releaseRepository
      );

  Future<void> updateMovieData() async {
    try {

      final data = await apiService.fetchMovieInfoSync(versionRepository.getVersion());

      final results = await Future.wait([
        Future(() => (data['newMovie'] as List<dynamic>? ?? [])
            .map((json) => NewMovie.fromJson(json as Map<String, dynamic>))
            .toList()),

        Future(() => (data['retireMovie'] as List<dynamic>? ?? [])
            .map((json) => RetireMovie.fromJson(json as Map<String, dynamic>))
            .toList()),

        Future(() => (data['rankingSync'] as List<dynamic>? ?? [])
            .map((json) => RankingSync.fromJson(json as Map<String, dynamic>))
            .toList()),
      ]);

      final List<NewMovie> newMovieList = results[0] as List<NewMovie>;
      final List<RetireMovie> retireMovieList = results[1] as List<RetireMovie>;
      final List<RankingSync> rankingSyncList = results[2] as List<RankingSync>;

      final int latestVersion = data['latestVersion']?['latestVersion'] as int? ?? 0;
      final bool isValid = data['valid'] as bool? ?? false;


      if (!isValid) {
        List<ReleaseData> releaseData = await releaseApiService.fetchReleaseData();
        await releaseRepository.initReleaseBox(releaseData);

      } else {
        await Future.wait([
          releaseRepository.removeByMovieCode(retireMovieList),
          releaseRepository.addNewMovie(newMovieList),
          releaseRepository.rankingChange(rankingSyncList),
        ]);
      }

      versionRepository.saveVersion(latestVersion);

    } catch (e, stackTrace) {
      print('Error occurred: $e');
      print('Stack trace: $stackTrace');
      throw Exception('Failed to update movie data: $e');
    }
  }
}

