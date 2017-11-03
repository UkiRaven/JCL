package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            do {
                System.out.println("Type command:");
                line = reader.readLine();
                args = line.split(" ");
                if (args[0].equals("!")) {
                    if (args.length == 2) {
                        CommandExecutor.executeProcess(args[1]);
                    } else if (args.length > 2) {
                        String[] varargs = new String[args.length - 1];
                        System.arraycopy(args, 1, varargs, 0, args.length - 1);
                        CommandExecutor.executeProcess(varargs);
                    }
                } else {
                    if (args.length == 1) {
                        CommandExecutor.execute(args[0].toUpperCase());
                    } else {
                        String[] varargs = new String[args.length - 1];
                        System.arraycopy(args, 1, varargs, 0, args.length - 1);
                        CommandExecutor.execute(args[0].toUpperCase(), varargs);
                    }
                }


            } while (!line.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchCommandException e) {
            System.out.println("No such command");
        }
    }
}
