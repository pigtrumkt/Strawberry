package Common;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdUtils {

    public static void runCmd(String... cmd) {
        try {
            String cmds = "";
            for (String s : cmd) {
                cmds += s + " && ";
            }
            
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + cmds + "exit\"");
        } catch (IOException e) {
        }
    }

    public static void runPowershell(String cmd) {

        try {
            String[] commandList = { "powershell.exe", "-Command", cmd };
            ProcessBuilder pb = new ProcessBuilder(commandList);
            pb.start();
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("resource")
    public static boolean isAdmin() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe");
            Process process = processBuilder.start();
            PrintStream printStream = new PrintStream(process.getOutputStream(), true);
            Scanner scanner = new Scanner(process.getInputStream());
            printStream.println("@echo off");
            printStream.println(">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\"");
            printStream.println("echo %errorlevel%");

            boolean printedErrorlevel = false;
            while (true) {
                String nextLine = scanner.nextLine();
                if (printedErrorlevel) {
                    int errorlevel = Integer.parseInt(nextLine);
                    return errorlevel == 0;
                } else if (nextLine.equals("echo %errorlevel%")) {
                    printedErrorlevel = true;
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
