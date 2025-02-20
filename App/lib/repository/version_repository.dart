import 'package:hive/hive.dart';

class VersionRepository {
  final versionBox = Hive.box<int>('versionBox');


  Future<void> saveVersion(int newVersion) async {
    await versionBox.put('version', newVersion);
  }

  int getVersion() {
    return versionBox.get('version') ?? 0;
  }

}
