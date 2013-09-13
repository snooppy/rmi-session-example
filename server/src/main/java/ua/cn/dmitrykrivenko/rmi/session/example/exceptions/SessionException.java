package ua.cn.dmitrykrivenko.rmi.session.example.exceptions;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class SessionException extends Exception {

    public static final int SESSION_ID_REQUIRED = 1;
    public static final int SESSION_ID_DOES_NOT_EXIST = 2;
    private int errorCode;

    public SessionException(String cause, int newErrorCode) {
        super(cause);
        errorCode = newErrorCode;
    }

    public SessionException(String cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
