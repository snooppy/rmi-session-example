package ua.cn.dmitrykrivenko.rmi.session.example.client;

import java.io.Console;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.Session;
import ua.cn.dmitrykrivenko.rmi.session.example.exceptions.SessionException;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class ClientConsole {

    private static final String HELP_COMMAND = "help";
    private static final String GET_DATA_COMMAND = "getdata";
    private static final String GET_DATA_REVERSE_COMMAND = "getrev";
    private static final String EXIT_COMMAND = "exit";
    private static final String COMMANDS = "Commands:\n"
            + "- " + HELP_COMMAND + " - to see all available commands\n"
            + "- " + GET_DATA_COMMAND + " - to get data\n"
            + "- " + GET_DATA_REVERSE_COMMAND + " - to get reverse data\n"
            + "- " + EXIT_COMMAND + " - to exit\n";
    private Console console;
    private String login;
    private char[] password;

    public ClientConsole() {
        console = System.console();
        if (console == null) {
            System.out.println("Cannot initialize console");
            System.exit(1);
        }
    }

    public void readLoginPassword() {
        login = console.readLine("Enter your username: ");
        password = console.readPassword("Enter %s's password: ", getLogin());
        console.printf("Welcome, %1$s.", getLogin());
        console.printf("\n");
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    public void clearPassword() {
        Arrays.fill(password, ' '); // delete password from memory
    }

    public void run(Session session, UUID sessionId) throws SessionException, RemoteException {
        String command;
        console.printf(COMMANDS);
        while (true) {
            command = console.readLine();
            if (command.equals(HELP_COMMAND)) {
                console.printf(COMMANDS);
            } else if (command.equals(GET_DATA_COMMAND)) {
                List<Integer> data = session.getData(false, sessionId);
                printData(data);
            } else if (command.equals(GET_DATA_REVERSE_COMMAND)) {
                List<Integer> data = session.getData(true, sessionId);
                printData(data);
            } else if (command.equals(EXIT_COMMAND)) {
                break;
            }
        }
    }

    private void printData(List<Integer> data) {
        for (Integer d : data) {
            console.printf(d + "\t");
        }
        console.printf("\n");
    }
}
