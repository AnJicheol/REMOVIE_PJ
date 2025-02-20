import 'package:flutter/material.dart';
import 'package:removie_app/service/movie_api_service.dart';
import '../models/movie_data.dart';

class MovieDataTestWidget extends StatefulWidget {
  @override
  _MovieDataTestWidgetState createState() => _MovieDataTestWidgetState();
}

class _MovieDataTestWidgetState extends State<MovieDataTestWidget> {
  late Future<List<MovieData>> futureMovieData;

  @override
  void initState() {
    super.initState();
    
    futureMovieData = MovieApiService().fetchMovieData([
      '20240737',
      '20246939',
      '20225184',
    ]);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Movie Data Test'),
      ),
      body: FutureBuilder<List<MovieData>>(
        future: futureMovieData,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(
              child: Text('Error: ${snapshot.error}'),
            );
          } else if (snapshot.hasData) {
            final movieDataList = snapshot.data!;
            return ListView.builder(
              itemCount: movieDataList.length,
              itemBuilder: (context, index) {
                final movie = movieDataList[index];
                return Card(
                  child: ListTile(
                    title: Text(movie.title),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Movie Code: ${movie.movieCode}'),
                        Text('Info: ${movie.info}'),
                      ],
                    ),
                    leading: Image.network(
                      movie.posterIMG,
                      width: 50,
                      height: 50,
                      fit: BoxFit.cover,
                      errorBuilder: (context, error, stackTrace) {
                        return Icon(Icons.error, size: 50);
                      },
                    ),
                  ),
                );
              },
            );
          } else {
            return Center(
              child: Text('No data found'),
            );
          }
        },
      ),
    );
  }
}