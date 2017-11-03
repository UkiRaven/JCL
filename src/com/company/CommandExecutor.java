package com.company;

import com.company.commands.*;

import java.io.*;
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

    public static void execute(String commandName, String ...args) throws NoSuchCommandException {
        try {
            commandMap.get(CommandList.valueOf(commandName)).execute(args);
        } catch (IllegalArgumentException e) {
            throw new NoSuchCommandException();
        }
    }

    public static void executeProcess(String processName, String ...args) {
        try {
            ProcessBuilder builder = new ProcessBuilder(processName);
            builder.inheritIO();
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class OutWriter extends Thread {
        OutputStream out;
        OutWriter(OutputStream out) {
            this.out = out;
        }

        @Override
        public void run() {
            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
                for (int i = 0; i < 10; i++) {
                    writer.write("sup\n");
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
