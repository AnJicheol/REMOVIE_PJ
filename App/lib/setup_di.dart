import 'package:get_it/get_it.dart';
import 'package:hive/hive.dart';
import 'package:removie_app/repository/movie_repository.dart';
import 'package:removie_app/repository/version_repository.dart';
import 'package:removie_app/service/cinema_api_service.dart';
import 'package:removie_app/service/detail_ui_service.dart';
import 'package:removie_app/service/main_ui_service.dart';
import 'package:removie_app/repository/release_repository.dart';
import 'package:removie_app/service/movieService.dart';
import 'package:removie_app/service/movie_api_service.dart';
import 'package:removie_app/service/movie_sync_api_service.dart';
import 'package:removie_app/service/movie_sync_service.dart';
import 'package:removie_app/service/release_api_service.dart';
import 'package:removie_app/service/release_date_api_service.dart';
import 'models/release_data.dart';

final getIt = GetIt.instance;

void setupDI() {

  getIt.registerSingleton<MovieDataRepository>(
    MovieDataRepository(Hive.box<Map<String, dynamic>>('movieDataBox')),
  );
  getIt.registerSingleton<ReleaseRepository>(
    ReleaseRepository(Hive.box<ReleaseData>('releaseBox')),
  );
  getIt.registerSingleton<VersionRepository>(
    VersionRepository(),
  );

  getIt.registerSingleton<MovieApiService>(
    MovieApiService(),
  );

  getIt.registerSingleton<MovieSyncApiService>(
    MovieSyncApiService(),
  );
  getIt.registerSingleton<ReleaseApiService>(
    ReleaseApiService(),
  );

  getIt.registerSingleton<CinemaApiService>(
      CinemaApiService(),
  );

  getIt.registerSingleton<ReleaseDateApiService>(
    ReleaseDateApiService(),
  );

  getIt.registerSingleton<MovieService>(
    MovieService(
      getIt<MovieApiService>(),
      getIt<MovieDataRepository>(),
      getIt<ReleaseRepository>(),
    ),
  );

  getIt.registerSingleton<MovieSyncService>(
    MovieSyncService(
      getIt<MovieSyncApiService>(),
      getIt<VersionRepository>(),
      getIt<ReleaseApiService>(),
      getIt<ReleaseRepository>(),
    ),
  );

  getIt.registerSingleton<MainUIService>(
    MainUIService(
      getIt<MovieService>(),
    ),
  );

  getIt.registerSingleton<DetailUIService>(
      DetailUIService(
        getIt<CinemaApiService>(),
        getIt<ReleaseDateApiService>(),
      ),
  );
}