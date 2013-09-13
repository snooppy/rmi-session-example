package ua.cn.dmitrykrivenko.rmi.session.example.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.Login;
import ua.cn.dmitrykrivenko.rmi.session.example.Session;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class RunClient {

    private static final String LOGIN_SERVICE = "rmi://localhost/RemoteLogin";
    private static final String SESSION_SERVICE = "rmi://localhost/RemoteSession";

    public static void main(String[] args) throws RemoteException {
        Session sessionService = null;
        UUID sessionId = null;
        ClientConsole console = new ClientConsole();
        try {
            Login loginService = (Login) Naming.lookup(LOGIN_SERVICE);
            sessionService = (Session) Naming.lookup(SESSION_SERVICE);

            console.readLoginPassword();

            sessionId = loginService.login(console.getLogin(), console.getPassword());

            console.clearPassword();

            console.run(sessionService, sessionId);
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            if (sessionService != null && sessionId != null) {
                sessionService.logout(sessionId);
            }
        }
    }
}
