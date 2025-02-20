import 'dart:convert';

import '../config/api_config.dart';
import '../models/movie_data.dart';
import 'package:http/http.dart' as http;

class MovieApiService {
  static const String movieInfoPageUrl = '/movie/info';

  Future<List<MovieData>> fetchMovieData(List<String> movieCode) async {

    final uri = Uri.http(
      ApiConfig.baseUrl,
      movieInfoPageUrl,
      {
        'movieCode': movieCode.join(','),
      },
    );

    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final decodedBody = utf8.decode(response.bodyBytes);
      final List<dynamic> jsonList = jsonDecode(decodedBody);

      return jsonList.map((json) => MovieData.fromJson(json)).toList();
    } else {
      throw Exception(
          'Failed to fetch movie data. Status Code: ${response.statusCode}, Response: ${response.body}');
    }
  }

  Future<List<MovieData>> fetchPagedMovieInfo(int offset, int limit) async {
    final uri = Uri.http(
      ApiConfig.baseUrl,
      '/page',
      {
        'offset': offset.toString(),
        'limit': limit.toString(),
      },
    );

    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final List<dynamic> jsonData = jsonDecode(response.body);
      return jsonData.map((item) => MovieData.fromJson(item)).toList();
    } else {
      throw Exception('Failed to fetch paged movie info');
    }
  }
}

