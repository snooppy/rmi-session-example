package ua.cn.dmitrykrivenko.rmi.session.example.exceptions;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class AuthorizationException extends Exception {

    public static final int USER_PASSWORD_DOES_NOT_EXIST = 1;
    private int errorCode;

    public AuthorizationException(String cause, int newErrorCode) {
        super(cause);
        errorCode = newErrorCode;
    }

    public AuthorizationException(String cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
