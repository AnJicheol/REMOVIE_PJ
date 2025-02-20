class RankingSync {
  final String movieCode;
  final int insertRanking;

  RankingSync({required this.movieCode, required this.insertRanking});

  factory RankingSync.fromJson(Map<String, dynamic> json) {
    return RankingSync(
      movieCode: json['movieCode'],
      insertRanking: json['insertRanking'],
    );
  }
}