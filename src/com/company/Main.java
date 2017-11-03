package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    public static boolean parseAndExecute(String[] args) {
        try {
            args = Arrays.stream(args).filter(s -> !s.isEmpty()).toArray(String[]::new);
            if (args[0].equals("!")) {
                if (args.length == 2) {
                    return CommandExecutor.executeProcess(args[1]);
                } else if (args.length > 2) {
                    String[] varargs = new String[args.length - 1];
                    System.arraycopy(args, 1, varargs, 0, args.length - 1);
                    return CommandExecutor.executeProcess(varargs);
                }
            } else {
                if (args.length == 1) {
                    return CommandExecutor.execute(args[0].toUpperCase());
                } else {
                    String[] varargs = new String[args.length - 1];
                    System.arraycopy(args, 1, varargs, 0, args.length - 1);
                    return CommandExecutor.execute(args[0].toUpperCase(), varargs);
                }
            }
        } catch (NoSuchCommandException e) {
            System.out.println("No such command");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (true){

                System.out.println("Type command:");
                line = reader.readLine();
                if (line.equals("exit")) break;

                //Checking for complex commands
                String[] commands;
                if (line.contains("&&")) {
                    //Executing each command from right to left
                    commands = line.split("&&");
                    for (int i = commands.length-1; i >=0 ; i--) {
                        args = commands[i].trim().split(" ");
                        if (!parseAndExecute(args)) break;
                    }
                } else if (line.contains("||")) {
                    commands = line.split(Pattern.quote("||"));
                    //Executing each command from left to right
                    for (int i = 0; i < commands.length; i++) {
                        args = commands[i].trim().split(" ");
                        if (parseAndExecute(args)) break;
                    }
                } else {
                    //Execute command if it is not complex
                    parseAndExecute(line.split(" "));
                }
            }
    }
}
