
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:hive_flutter/adapters.dart';
import 'package:removie_app/models/movie_data.dart';
import 'package:removie_app/page_limit_exception.dart';
import 'package:removie_app/service/main_ui_service.dart';
import 'package:removie_app/service/movie_sync_service.dart';
import 'package:removie_app/setup_di.dart';
import 'package:removie_app/ui/movie_card.dart';
import 'package:removie_app/ui/movie_detail_page.dart';
import 'models/release_data.dart';



const Color searchBarColors = Color(0x007e7e7e);
const Color backGroundColors = Color(0xFF212121);

Future<void> _firebaseMessagingBackgroundHandler(RemoteMessage message) async {
  print("백그라운드 메시지 수신: ${message.data}");
}
final GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();


void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();

  FirebaseMessaging.onBackgroundMessage(_firebaseMessagingBackgroundHandler);

  await Hive.initFlutter();

  Hive.registerAdapter(ReleaseDataAdapter());
  Hive.registerAdapter(MovieDataAdapter());

  await Hive.openBox<Map<String, dynamic>>('movieDataBox');
  await Hive.openBox<ReleaseData>('releaseBox');
  await Hive.openBox<int>('versionBox');

  setupDI(); // DI 설정

  await initializeData();

  final movieSyncService = getIt<MovieSyncService>();
  await movieSyncService.updateMovieData();

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: navigatorKey, 
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        scaffoldBackgroundColor: backGroundColors,
      ),
      home: const MainPage(),
    );
  }
}


class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  MainPageState createState() => MainPageState();
}

class MainPageState extends State<MainPage> {
  final ScrollController _scrollController = ScrollController();
  final mainUIService = getIt<MainUIService>();

  List<MovieData> movieList   = [];
  List<MovieData> removieList = [];

  final int pageSize = 10;
  int currentPage = 0;
  bool update = false;
  bool pageLimit = false;

  @override
  void initState() {
    super.initState();
    removieUpdate();
    updateData();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      setupFCM();
    });
    _scrollController.addListener(_scrollListener);
  }

  void setupFCM() async {
    FirebaseMessaging messaging = FirebaseMessaging.instance;

    const String FCM_TOPIC = "REMOVIE_WISHLIST";
    await messaging.subscribeToTopic(FCM_TOPIC);


    FirebaseMessaging.onMessage.listen((RemoteMessage message) {

      if (message.data.isNotEmpty) {
        WidgetsBinding.instance.addPostFrameCallback((_) {
          if (navigatorKey.currentContext != null) { 
            showDialog(
              context: navigatorKey.currentContext!,
              builder: (context) => AlertDialog(
                title: const Text("FCM 알림"),
                content: Text("영화 코드: ${message.data['movieCodes']}"),
                actions: [
                  TextButton(
                    onPressed: () => Navigator.pop(context),
                    child: Text("닫기"),
                  ),
                ],
              ),
            );
          } else {
            print("navigatorKey.currentContext is null");
          }
        });
      }
    });
  }

  Future<void> removieUpdate() async {
    final List<MovieData> removie = await mainUIService.getRemovieList();
    setState(() {
      removieList.addAll(removie);
    });
  }

  Future<void> updateData() async {
    if (update) return;
    if (mounted) setState(() => update = true);

    try {
      final newMovies = await mainUIService.getMovieData(currentPage, currentPage + pageSize);
      setState(() {
        movieList.addAll(newMovies);
        currentPage += pageSize;
      });
    } on PageLimitException {
      pageLimit = true;
    } catch (e) {
      print("Error fetching movies: $e");
    } finally {
      if (mounted) setState(() => update = false);
    }
  }

  void _scrollListener() {
    if (_scrollController.position.pixels >= _scrollController.position.maxScrollExtent - 200 && !update && !pageLimit) {
      updateData();
    }
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,

        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              style: const TextStyle(
                color: Colors.white,
                fontSize: 24,
              ),

              decoration: InputDecoration(
                hintText: 'Search...',
                hintStyle: const TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                ),

                suffixIcon: const Icon(
                  Icons.search,
                  color: Colors.white,
                  size: 32,
                ),

                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(16),
                  borderSide: const BorderSide(width: 1, color: searchBarColors),
                ),


                filled: true,
                fillColor: const Color(0x007E7E7E),
                contentPadding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              ),
            ),
          ),

          // 제목 1
          const Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(
              '재개봉 영화',
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),

          SizedBox(
            width: double.infinity,
            height: 270,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: removieList.length,
              itemBuilder: (context, index) {
                final movie = removieList[index];

                return GestureDetector(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => DetailPage(movieData: movie),
                      ),
                    );
                  },
                  child: Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 8.0),
                    child: MovieCard(movie: movie), // MovieCard 위젯 사용
                  ),
                );
              },
            ),
          ),

          const Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(
              '박스 오피스',
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),

          SizedBox(
            width: double.infinity,
            height: 270,
            child: ListView.builder(
              controller: _scrollController,

              scrollDirection: Axis.horizontal,
              itemCount: movieList.length,
              itemBuilder: (context, index) {
                final movie = movieList[index];

                return GestureDetector(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => DetailPage(movieData: movie),
                      ),
                    );
                  },
                  child: Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 8.0),
                    child: MovieCard(movie: movie),
                  ),
                );
              },
            ),
          ),

        ],
      ),
    );
  }
}

Future<void> initializeData() async {

  final movieDataBox = Hive.box<Map<String, dynamic>>('movieDataBox');
  final releaseBox = Hive.box<ReleaseData>('releaseBox');
  final versionBox = Hive.box<int>('versionBox');

  await movieDataBox.clear();
  await releaseBox.clear();
  await versionBox.clear();
  await versionBox.put('version', -1);
}
