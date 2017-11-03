package com.company.commands;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lastc on 01.11.2017.
 */
public class CDCommand implements Command{

    @Override
    public void execute(String... args) {
        Path path = Paths.get(args[0]).normalize();
        if (path.isAbsolute()) {
            System.setProperty("user.dir", path.toString());
            return;
        }
        try {
            Path newPath = Paths.get(System.getProperty("user.dir"), path.toString());
            System.setProperty("user.dir", newPath.toRealPath().toString());

        } catch (NoSuchFileException e) {
            System.out.println("No such file");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
