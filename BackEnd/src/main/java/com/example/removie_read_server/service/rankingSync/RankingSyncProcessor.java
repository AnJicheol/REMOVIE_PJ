package com.example.removie_read_server.service.rankingSync;

import com.example.removie_read_server.dto.RankingDTO;

import java.util.List;

public interface RankingSyncProcessor {
    List<RankingDTO> rankingSyncData(Integer version);
}
