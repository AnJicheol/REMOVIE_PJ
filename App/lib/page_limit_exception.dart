class PageLimitException implements Exception {
  final String message;
  PageLimitException([this.message = "end"]);

  @override
  String toString() => message;
}