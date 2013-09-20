package ua.cn.dmitrykrivenko.rmi.session.example.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import ua.cn.dmitrykrivenko.rmi.session.example.Session;
import ua.cn.dmitrykrivenko.rmi.session.example.exceptions.SessionException;
import ua.cn.dmitrykrivenko.rmi.session.example.server.DataGenerator;
import ua.cn.dmitrykrivenko.rmi.session.example.server.SessionStorage;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class SessionImpl extends UnicastRemoteObject implements Session {

    private DataGenerator dataGenerator;

    public SessionImpl() throws RemoteException {
        super();
    }

    public SessionImpl(DataGenerator dataGenerator) throws RemoteException {
        super();
        this.dataGenerator = dataGenerator;
    }

    public List<Integer> getData(boolean reverse, UUID sessionId) throws SessionException, RemoteException {
        if (!SessionStorage.INSTANCE.sessionIdExists(sessionId)) {
            throw new SessionException("Session id does not exist", 2);
        }
        List<Integer> data = dataGenerator.getData();
        if (!reverse) {
            return data;
        }
        Collections.reverse(data);
        return data;
    }

    public void logout(UUID sessionId) throws RemoteException {
        SessionStorage.INSTANCE.removeSessionId(sessionId);
    }
}
