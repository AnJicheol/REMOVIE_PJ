package com.example.removie.update;

import com.example.removie.update.updateCommand.ChangeCommandBundle;
import com.example.removie.version.NewVersion;
import com.example.removie.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 커맨드 객체에 명령을 실행하는 클래스입니다
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Component
public class UpdateCommandExecutorImpl extends UpdateCommandExecutor {
    private final VersionService versionService;

    @Autowired
    public UpdateCommandExecutorImpl(VersionService versionService) {
        this.versionService = versionService;
    }

    @Override
    protected NewVersion getNewVersion() {
        return versionService.getNewVersion();
    }

    @Override
    protected void saveNewVersion(NewVersion newVersion) {
        versionService.saveNewVersion(newVersion);
    }

    @Override
    @Transactional
    protected void executeCommand(ChangeCommandBundle changeCommandBundle, NewVersion newVersion) {
        changeCommandBundle.executeCommands(newVersion);
    }
}
