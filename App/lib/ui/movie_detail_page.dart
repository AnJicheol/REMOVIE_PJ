import 'package:flutter/material.dart';
import 'package:removie_app/models/cinema_data.dart';
import 'package:removie_app/models/movie_data.dart';
import 'package:removie_app/models/release_date_data.dart';
import 'package:removie_app/ui/release_info.dart';
import 'package:removie_app/ui/release_tag.dart';
import '../service/detail_ui_service.dart';
import '../setup_di.dart';
import 'back_button.dart';


class DetailPage extends StatefulWidget {
  final MovieData movieData;

  DetailPage({Key? key, required this.movieData}) : super(key: key);

  @override
  MovieDetailPage createState() => MovieDetailPage();
}

class MovieDetailPage extends State<DetailPage>{

  final detailUIService = getIt<DetailUIService>();
  final bool testRe = true;
  bool isExpanded = false;
  bool isShowingScreeningInfo = true;
  bool isLoading = true;

  List<CinemaData> cinemaDataList = [];
  List<ReleaseDateData> dateList = [];

  @override
  void initState() {
    super.initState();
    fetchData();
  }

  Future<void> fetchData() async {
    try {
      List<CinemaData> cinemaData = await detailUIService.getCinemaDataList(widget.movieData.movieCode);
      List<ReleaseDateData> date = await detailUIService.getDateList(widget.movieData.movieCode);

      setState(() {
        cinemaDataList = cinemaData;
        dateList = date;

        if (cinemaDataList.isEmpty) {
          isShowingScreeningInfo = false;
        }
        isLoading = false;
      });

    } catch (e) {
      print("Error fetching data: $e");
      setState(() => isLoading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    final gradientEnd = screenHeight * 0.4;

    return Scaffold(
      body: Stack(
        children: [

          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: NetworkImage(widget.movieData.posterIMG),
                fit: BoxFit.cover,
                alignment: Alignment.topCenter,
              ),
            ),
          ),

          Container(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                colors: [
                  const Color(0xFF212121),
                  const Color(0xFF212121).withOpacity(0.7),
                  Colors.transparent,
                ],
                stops: const [0.4, 0.5, 0.6],
                begin: Alignment.bottomCenter,
                end: Alignment.topCenter,
              ),
            ),
          ),

          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20.0), // 전역 가로 패딩
            child: Stack(
              children: [
                // 뒤로 가기 버튼
                const Positioned(
                  top: 40,
                  child: CustomBackButton(),
                ),


                Positioned(
                  top: gradientEnd,
                  left: 0,
                  right: 0,
                  bottom: 0,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      if (testRe) const ReleaseTag(),

                      Text(
                        widget.movieData.title,
                        style: TextStyle(
                          fontSize: 40,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),

                      Text(
                        widget.movieData.info,
                        style: TextStyle(
                          fontSize: 24,
                          color: Colors.white,
                        ),
                      ),

                      const SizedBox(height: 24),

                      cinemaDataList.isNotEmpty
                          ? Row(
                        children: [
                          Expanded(
                            child: TextButton(
                              onPressed: () => setState(() => isShowingScreeningInfo = true),
                              child: Text(
                                '상영 정보',
                                style: TextStyle(
                                  fontSize: 32,
                                  color: Colors.white,
                                  fontWeight: isShowingScreeningInfo ? FontWeight.bold : FontWeight.normal,
                                ),
                              ),
                            ),
                          ),
                          Expanded(
                            child: TextButton(
                              onPressed: () => setState(() => isShowingScreeningInfo = false),
                              child: Text(
                                '개봉 정보',
                                style: TextStyle(
                                  fontSize: 32,
                                  color: Colors.white,
                                  fontWeight: !isShowingScreeningInfo ? FontWeight.bold : FontWeight.normal,
                                ),
                              ),
                            ),
                          ),
                        ],
                      )
                          : Align(
                        alignment: Alignment.centerLeft, // 왼쪽 정렬

                        child: const Text(
                          '개봉 정보',
                          style: TextStyle(
                            fontSize: 32,
                            color: Colors.white,
                            fontWeight: FontWeight.bold,
                          ),
                        ),

                      ),

                      if (cinemaDataList.isNotEmpty)
                        Row(
                          children: [
                            Expanded(
                              child: AnimatedContainer(
                                duration: const Duration(milliseconds: 300),
                                height: 4,
                                color: isShowingScreeningInfo ? Colors.yellow : Colors.transparent,
                              ),
                            ),
                            Expanded(
                              child: AnimatedContainer(
                                duration: const Duration(milliseconds: 300),
                                height: 4,
                                color: !isShowingScreeningInfo ? Colors.yellow : Colors.transparent,
                              ),
                            ),
                          ],
                        ),

                      const SizedBox(height: 24),

                      // 본문 내용 (리스트 or 로딩)
                      Expanded(
                        child: isLoading
                            ? const Center(child: CircularProgressIndicator())
                            : (isShowingScreeningInfo && cinemaDataList.isNotEmpty)
                            ? ListView.separated(
                          padding: const EdgeInsets.symmetric(horizontal: 16.0),
                          itemCount: cinemaDataList.length,
                          itemBuilder: (context, index) {
                            return ReleaseInfo(cinemaDataList[index]);
                          },
                          separatorBuilder: (context, index) => const Divider(
                            thickness: 1,
                            color: Colors.grey,
                            height: 16,
                          ),
                        )
                            : ListView.separated(
                          padding: const EdgeInsets.symmetric(horizontal: 16.0),
                          itemCount: dateList.length,
                          itemBuilder: (context, index) {
                            return ListTile(
                              title: Text(
                                dateList[index].date,
                                style: const TextStyle(fontSize: 24, color: Colors.white),
                              ),
                            );
                          },
                          separatorBuilder: (context, index) => const Divider(
                            thickness: 1,
                            color: Colors.grey,
                            height: 16,
                          ),
                        ),
                      ),



                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}