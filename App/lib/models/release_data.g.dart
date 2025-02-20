// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'release_data.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class ReleaseDataAdapter extends TypeAdapter<ReleaseData> {
  @override
  final int typeId = 0;

  @override
  ReleaseData read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return ReleaseData(
      movieCode: fields[0] as String,
      removie: fields[1] as bool,
    );
  }

  @override
  void write(BinaryWriter writer, ReleaseData obj) {
    writer
      ..writeByte(2)
      ..writeByte(0)
      ..write(obj.movieCode)
      ..writeByte(1)
      ..write(obj.removie);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is ReleaseDataAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
