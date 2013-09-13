package ua.cn.dmitrykrivenko.rmi.session.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.exceptions.AuthorizationException;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public interface Login extends Remote {

    public UUID login(String login, char[] password) throws AuthorizationException, RemoteException;
}
