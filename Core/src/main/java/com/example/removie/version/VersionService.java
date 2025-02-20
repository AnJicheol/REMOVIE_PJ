package com.example.removie.version;

public interface VersionService {
    NewVersion getNewVersion();
    void saveNewVersion(NewVersion newVersion);
    Integer getCurrentVersion();
}
