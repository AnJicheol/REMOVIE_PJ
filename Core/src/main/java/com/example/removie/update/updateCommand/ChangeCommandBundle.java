package com.example.removie.update.updateCommand;

import com.example.removie.update.updateCommand.command.ChangeCommand;
import com.example.removie.version.NewVersion;

import java.util.ArrayList;
import java.util.List;


/**
 * 명령을 포장하는 객체입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
public class ChangeCommandBundle {
    private final List<ChangeCommand> commandList = new ArrayList<>();

    public void addChangeCommand(ChangeCommand changeCommand) {
        if(validateCommand(changeCommand)) commandList.add(changeCommand);
    }

    public void executeCommands(NewVersion newVersion){
        for(ChangeCommand changeCommand : commandList){
            changeCommand.update(newVersion.getVersion());
        }
    }

    public boolean isEmpty(){
        return commandList.isEmpty();
    }

    private boolean validateCommand(ChangeCommand changeCommand){
        return !changeCommand.isEmpty();
    }
}
