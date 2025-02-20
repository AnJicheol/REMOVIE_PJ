
import 'dart:convert';

import '../config/api_config.dart';
import '../models/release_data.dart';
import 'package:http/http.dart' as http;

class ReleaseApiService {
  static const String releaseInfo = '/release/info';

  Future<List<ReleaseData>> fetchReleaseData() async {

    final uri = Uri.http(
      ApiConfig.baseUrl,
      releaseInfo,
    );

    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final decodedBody = utf8.decode(response.bodyBytes);
      final List<dynamic> jsonList = jsonDecode(decodedBody);
      return jsonList.map((json) => ReleaseData.fromJson(json)).toList();
    } else {
      throw Exception('Failed to fetch movie data. Status Code: ${response.statusCode}, Response: ${response.body}');
    }
  }

}

