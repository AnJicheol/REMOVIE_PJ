package com.example.removie.version;

import com.example.removie.version.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 파싱 결과에 버전을 생성하는 클래스입니다
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class VersionServiceImpl implements VersionService{
    private final VersionRepository versionRepository;
    private final VersionEntityMapper versionEntityMapper;

    @Autowired
    public VersionServiceImpl(VersionRepository versionRepository, VersionEntityMapper versionEntityMapper) {
        this.versionRepository = versionRepository;
        this.versionEntityMapper = versionEntityMapper;
    }

    @Override
    @Transactional
    public NewVersion getNewVersion(){
        Integer newVersion = versionRepository.findMaxVersion()
                .map(version -> version + 1)
                .orElse(1);

        return new NewVersion(newVersion);
    }

    @Override
    @Transactional
    public Integer getCurrentVersion(){
        return versionRepository.findMaxVersion().orElse(1);
    }

    @Override
    @Transactional
    public void saveNewVersion(NewVersion newVersion){
        versionRepository.save(versionEntityMapper.gerVersionEntityForNewVersion(newVersion));
    }
}
