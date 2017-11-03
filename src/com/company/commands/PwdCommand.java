package com.company.commands;

/**
 * Created by lastc on 01.11.2017.
 */
public class PwdCommand implements Command {
    @Override
    public void execute(String ...args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
