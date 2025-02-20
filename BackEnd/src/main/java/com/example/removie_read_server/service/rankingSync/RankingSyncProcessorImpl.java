package com.example.removie_read_server.service.rankingSync;

import com.example.removie_read_server.dto.RankingDTO;
import com.example.removie_read_server.dto.ReleaseProjection;
import com.example.removie_read_server.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RankingSyncProcessorImpl implements RankingSyncProcessor {
    private final ReleaseService releaseService;

    @Autowired
    public RankingSyncProcessorImpl(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }


    public List<RankingDTO> rankingSyncData(Integer version){
        List<RankingDTO> rankingDTOList = new ArrayList<>();
        List<ReleaseProjection> syncVersion = releaseService.getReleaseInfoByVersion(version);

        Map<String, Integer> latestVersion = getLatestVersion(releaseService.getReleaseInfo());

        for(ReleaseProjection existingRelease : syncVersion){
            int latestRanking = latestVersion.getOrDefault(existingRelease.getMovieCode(), 0);
            int rankingChange = latestRanking - existingRelease.getRanking();

            if(rankingChange > 0){
                rankingDTOList.add(RankingDTO.of(existingRelease.getMovieCode(), latestRanking));

                String existingMovie = syncVersion.get(existingRelease.getRanking() - 1).getMovieCode();
                Integer newRanking = latestVersion.getOrDefault(existingMovie, -1);

                if(newRanking.equals(existingRelease.getRanking())){
                    rankingDTOList.add(RankingDTO.of(existingMovie, newRanking));
                }
            }
        }
        return rankingDTOList;
    }

    private Map<String, Integer> getLatestVersion(List<ReleaseProjection> releaseInfoList){
        return releaseInfoList.stream()
                .collect(Collectors.toMap(
                        ReleaseProjection::getMovieCode, 
                        ReleaseProjection::getRanking
                ));
    }

}
