package com.company.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by lastc on 01.11.2017.
 */
public class DirCommand implements Command {
    @Override
    public void execute(String ...args) {
        try {
            Files.list(Paths.get(System.getProperty("user.dir"))).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
