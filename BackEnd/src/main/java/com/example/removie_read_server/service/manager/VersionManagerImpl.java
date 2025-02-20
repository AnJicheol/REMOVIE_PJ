package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.errorCode.DBDataMissingErrorCode;
import com.example.removie_read_server.exception.DBDataMissingException;
import com.example.removie_read_server.repository.VersionRepository;
import com.example.removie_read_server.vo.LatestVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VersionManagerImpl implements VersionManager {

    private final VersionRepository versionRepository;

    @Autowired
    public VersionManagerImpl(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public LatestVersion getLatestVersion() {
        return versionRepository.findTopByOrderByVersionDesc()
                .map(entity -> new LatestVersion(entity.getVersion()))
                .orElseThrow(() -> new DBDataMissingException(DBDataMissingErrorCode.DB_DATA_MISSING.getMessage()));
    }


}
