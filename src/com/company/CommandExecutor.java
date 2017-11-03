package com.company;

import com.company.commands.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lastc on 01.11.2017.
 */
public class CommandExecutor {
    private static Map<CommandList, Command> commandMap = new HashMap<>();
    static {
        commandMap.put(CommandList.DIR, new DirCommand());
        commandMap.put(CommandList.PWD, new PwdCommand());
        commandMap.put(CommandList.CD, new CDCommand());
    }

    public static boolean execute(String commandName, String ...args) throws NoSuchCommandException {
        try {
            return commandMap.get(CommandList.valueOf(commandName)).execute(args);
        } catch (IllegalArgumentException e) {
            throw new NoSuchCommandException();

        }
    }

    public static boolean executeProcess(String ...command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(Paths.get(System.getProperty("user.dir")).toFile());
        builder.inheritIO();
        try {
            builder.start().waitFor();
        } catch (InterruptedException e) {
            System.out.println("process " + command[0] + " was interrupted");
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }



}
