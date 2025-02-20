import 'package:hive/hive.dart';

part 'movie_data.g.dart';

@HiveType(typeId: 1)
class MovieData {
  @HiveField(0)
  final String title;

  @HiveField(1)
  final String posterIMG;

  @HiveField(2)
  final String movieCode;

  @HiveField(3)
  final String info;

  MovieData({
    required this.movieCode,
    required this.info,
    required this.title,
    required this.posterIMG,
  });

  factory MovieData.fromJson(Map<String, dynamic> json) {
    return MovieData(
      title: json['title'],
      posterIMG: json['posterIMG'],
      movieCode: json['movieCode'],
      info : json['info'],
    );
  }
}


