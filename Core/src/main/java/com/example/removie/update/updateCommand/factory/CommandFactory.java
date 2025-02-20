package com.example.removie.update.updateCommand.factory;


import com.example.removie.update.updateCommand.command.ChangeCommand;

public interface CommandFactory {
    ChangeCommand createCommand();
}
