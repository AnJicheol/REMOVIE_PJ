
class CinemaData {
  final String name;
  final String url;

  CinemaData({required this.name, required this.url});

  factory CinemaData.fromJson(Map<String, dynamic> json) {
    return CinemaData(
      name: json['cinemaName'],
      url: json['cinemaUri'],
    );
  }
}