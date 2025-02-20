package com.example.removie.update;

import com.example.removie.update.updateCommand.ChangeCommandBundle;
import com.example.removie.version.NewVersion;

/**
 * 버전 관리를 위해 고안된 설계입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
public abstract class UpdateCommandExecutor {

    public void updateProcess(ChangeCommandBundle changeCommandBundle){
        NewVersion newVersion = getNewVersion();
        executeCommand(changeCommandBundle, newVersion);
        saveNewVersion(newVersion);
    }

    protected abstract NewVersion getNewVersion();
    protected abstract void saveNewVersion(NewVersion newVersion);
    protected abstract void executeCommand(ChangeCommandBundle changeCommandBundle, NewVersion newVersion);
}
