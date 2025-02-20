// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movie_data.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class MovieDataAdapter extends TypeAdapter<MovieData> {
  @override
  final int typeId = 1;

  @override
  MovieData read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return MovieData(
      movieCode: fields[2] as String,
      info: fields[3] as String,
      title: fields[0] as String,
      posterIMG: fields[1] as String,
    );
  }

  @override
  void write(BinaryWriter writer, MovieData obj) {
    writer
      ..writeByte(4)
      ..writeByte(0)
      ..write(obj.title)
      ..writeByte(1)
      ..write(obj.posterIMG)
      ..writeByte(2)
      ..write(obj.movieCode)
      ..writeByte(3)
      ..write(obj.info);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is MovieDataAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
