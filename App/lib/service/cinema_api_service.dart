import 'dart:convert';

import 'package:removie_app/models/cinema_data.dart';

import '../config/api_config.dart';
import 'package:http/http.dart' as http;

class CinemaApiService{
  static const String cinemaDataUrl = '/cinema';

  Future<List<CinemaData>> fetchCinemaData(String movieCode) async {
    final uri = Uri.http(
      ApiConfig.baseUrl,
      '$cinemaDataUrl/$movieCode',
    );

    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final decodedBody = utf8.decode(response.bodyBytes);
      final Map<String, dynamic> jsonResponse = jsonDecode(decodedBody);


      if (jsonResponse.containsKey('cinemaDataList') && jsonResponse['cinemaDataList'] is List) {
        return (jsonResponse['cinemaDataList'] as List)
            .map((json) => CinemaData.fromJson(json as Map<String, dynamic>))
            .toList();
      } else {
        throw Exception('Invalid API response: Missing or invalid "cinemaList" key');
      }
    } else {
      throw Exception(
          'Failed to fetch movie data. Status Code: ${response.statusCode}, Response: ${response.body}');
    }
  }
}