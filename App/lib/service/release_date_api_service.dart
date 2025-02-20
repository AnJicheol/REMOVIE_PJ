
import 'dart:convert';

import 'package:removie_app/models/release_date_data.dart';

import '../config/api_config.dart';
import 'package:http/http.dart' as http;

class ReleaseDateApiService {
  static const String releaseDateUrl = '/movie/info/date';

  Future<List<ReleaseDateData>> fetchReleaseDate (String movieCode) async {
    final uri = Uri.http(
      ApiConfig.baseUrl,
      '$releaseDateUrl/$movieCode',
    );

    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final decodedBody = utf8.decode(response.bodyBytes);
      final List<dynamic> jsonList = jsonDecode(decodedBody);

      return jsonList.map((json) => ReleaseDateData.fromJson(json)).toList();
    } else {
      throw Exception(
          'Failed to fetch movie data. Status Code: ${response.statusCode}, Response: ${response.body}');
    }

  }
}