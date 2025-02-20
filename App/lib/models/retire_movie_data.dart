class RetireMovie {
  final String movieCode;

  RetireMovie({required this.movieCode});

  factory RetireMovie.fromJson(Map<String, dynamic> json) {
    return RetireMovie(
      movieCode: json['movieCode'],
    );
  }
}