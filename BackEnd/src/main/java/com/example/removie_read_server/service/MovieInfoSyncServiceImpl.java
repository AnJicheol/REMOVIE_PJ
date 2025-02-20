package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.MovieSyncDTO;
import com.example.removie_read_server.service.manager.RetireMovieManager;
import com.example.removie_read_server.service.manager.VersionManager;
import com.example.removie_read_server.service.rankingSync.RankingSyncProcessor;
import com.example.removie_read_server.vo.LatestVersion;
import lombok.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoSyncServiceImpl implements MovieInfoSyncService{
    private final static int SYNC_SUPPORTED_VERSION_RANGE = 14;

    private final NewMovieService newMovieService;
    private final RetireMovieManager retireMovieManager;
    private final RankingSyncProcessor rankingSyncProcessor;
    private final VersionManager versionManager;

    public MovieInfoSyncServiceImpl(NewMovieService newMovieService, RetireMovieManager retireMovieManager, RankingSyncProcessor rankingSyncProcessor, VersionManager versionManager) {
        this.newMovieService = newMovieService;
        this.retireMovieManager = retireMovieManager;
        this.rankingSyncProcessor = rankingSyncProcessor;
        this.versionManager = versionManager;
    }


    @Override
    @Cacheable(value = "movieSyncInfo", key = "#version", sync = true)
    public MovieSyncDTO getMovieSyneData(Integer version) {
        LatestVersion latestVersion = versionManager.getLatestVersion();

        MovieSyncDTO.Builder builder = MovieSyncDTO.builder();
        builder.latestVersion(latestVersion);

        if(isVersionSynced(version, latestVersion)){
            builder.updateMovie(newMovieService.getNewMovieList(version))
                    .retireMovie(retireMovieManager.getRetireMovieList(version))
                    .rankingSync(rankingSyncProcessor.rankingSyncData(version));

        } else if (isLatestVersion(version, latestVersion)) {
            builder.rankingSync(rankingSyncProcessor.rankingSyncData(version));
        }
        else{
            builder.valid(false);
        }
        return builder.build();
    }


    private boolean isLatestVersion(@NonNull Integer version, LatestVersion latestVersion){
        int versionDiff = latestVersion.differenceFrom(version);
        return (versionDiff == 0);
    }

    private boolean isVersionSynced(@NonNull Integer version, LatestVersion latestVersion) {
        int versionDiff = latestVersion.differenceFrom(version);
        return (version > 0 && versionDiff <= SYNC_SUPPORTED_VERSION_RANGE && versionDiff > 0);
    }
}


