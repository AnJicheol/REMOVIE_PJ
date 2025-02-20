package com.example.removie.version;

import com.example.removie.movie.entity.VersionEntity;
import org.springframework.stereotype.Component;

@Component
public class VersionEntityMapper {
    public VersionEntity gerVersionEntityForNewVersion(NewVersion newVersion){
        return new VersionEntity(newVersion.getVersion());
    }
}
