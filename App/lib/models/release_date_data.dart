
class ReleaseDateData {
  final String date;

  ReleaseDateData({required this.date});

  factory ReleaseDateData.fromJson(Map<String, dynamic> json) {
    return ReleaseDateData(
      date: json['releaseDate'],
    );
  }

}