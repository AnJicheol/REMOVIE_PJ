import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:removie_app/config/api_config.dart';

class MovieSyncApiService {
  static const String syncUrl = '/movie/info/sync';

  Future<Map<String, dynamic>> fetchMovieInfoSync(int version) async {
    final uri = Uri.http(
      ApiConfig.baseUrl, 
      syncUrl,
      {'version': version.toString()},
    );
    try {

      final response = await http.get(uri);

      if (response.statusCode == 200) {

        return jsonDecode(response.body) as Map<String, dynamic>;
      } else {

        throw Exception(
            'Failed to fetch data. Status Code: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Error fetching data: $e');
    }
  }
}
