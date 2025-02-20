import 'package:hive/hive.dart';

part 'release_data.g.dart';

@HiveType(typeId: 0)
class ReleaseData {
  @HiveField(0)
  final String movieCode;
  @HiveField(1)
  final bool removie;


  ReleaseData({required this.movieCode, required this.removie});

  factory ReleaseData.fromJson(Map<String, dynamic> json) {
    return ReleaseData(
      movieCode: json['movieCode'],
      removie: json['removie'],
    );
  }
}