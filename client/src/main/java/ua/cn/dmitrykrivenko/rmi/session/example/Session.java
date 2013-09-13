package ua.cn.dmitrykrivenko.rmi.session.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.exceptions.SessionException;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public interface Session extends Remote {

    public List<Integer> getData(boolean reverse, UUID sessionId) throws SessionException, RemoteException;

    public void logout(UUID sessionId) throws RemoteException;
}
