class NewMovie {
  final String movieCode;
  final int ranking;
  final bool removie;

  NewMovie({required this.movieCode, required this.ranking, required this.removie});

  factory NewMovie.fromJson(Map<String, dynamic> json) {
    return NewMovie(
      movieCode: json['movieCode'],
      ranking: json['ranking'],
      removie: json['removie'],
    );
  }
}