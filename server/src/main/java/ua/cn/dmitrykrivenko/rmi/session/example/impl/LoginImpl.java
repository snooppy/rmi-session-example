package ua.cn.dmitrykrivenko.rmi.session.example.impl;

import ua.cn.dmitrykrivenko.rmi.session.example.exceptions.AuthorizationException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.Login;
import ua.cn.dmitrykrivenko.rmi.session.example.server.SessionStorage;
import ua.cn.dmitrykrivenko.rmi.session.example.server.utils.Encryptor;
import ua.cn.dmitrykrivenko.rmi.session.example.server.utils.SQLiteJDBC;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class LoginImpl extends UnicastRemoteObject implements Login {

    public LoginImpl() throws RemoteException {
        super();
    }

    public UUID login(String login, char[] password) throws AuthorizationException, RemoteException {
        String pass = Encryptor.encryptPassword(password);
        try {
            if (SQLiteJDBC.userExists(login, pass)) {
                return SessionStorage.INSTANCE.generateSessionId(login);
            } else {
                throw new AuthorizationException("User with such username/password combination does not exist", 1);
            }
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            throw new AuthorizationException("Exception while authorization. Root cause: " + e);
        }
    }
}
