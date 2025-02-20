import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import '../models/cinema_data.dart';


class ReleaseInfo extends StatefulWidget {
  final CinemaData cinemaInfo;

  const ReleaseInfo(this.cinemaInfo, {super.key});

  @override
  ReleaseInfoList createState() => ReleaseInfoList();
}


class ReleaseInfoList extends State<ReleaseInfo> {

  bool isExpanded = false;

  Future<void> _launchURL() async {
    final Uri url = Uri.parse(widget.cinemaInfo.url);
    if (await canLaunchUrl(url)) {
      await launchUrl(url, mode: LaunchMode.externalApplication);
    } else {
      throw Exception('Could not launch ${widget.cinemaInfo.url}');
    }
  }

  @override
  Widget build(BuildContext context) {
    final cinemaInfo = widget.cinemaInfo;

    return Row(
      children: [

        Text(
          cinemaInfo.name,
          style: TextStyle(
            color: Colors.white,
            fontSize: 32,
          ),
        ),
        const Spacer(),

        TextButton(
          onPressed: _launchURL, // URL 열기
          child: const Text(
            '링크',
            style: TextStyle(
              color: Color(0xFFFFCA10),
              fontSize: 24,
            ),
          ),
        ),

      ],
    );
  }
}