package com.company.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lastc on 01.11.2017.
 */
public class CDCommand implements Command{

    @Override
    public void execute(String... args) {
        String userdir = System.getProperty("user.dir");
        Path newPath = Paths.get(userdir, args[0]);
        if (Files.exists(newPath) && Files.isDirectory(newPath)) {
            System.setProperty("user.dir", newPath.toString());
        } else {
            System.out.println("Wrong path");
        }

    }
}
