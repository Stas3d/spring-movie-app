import com.epam.springapp.exception.AppException;
import com.epam.springapp.exception.NoEventException;
import com.epam.springapp.exception.NoUserException;

import org.junit.Test;

/**
 *@author Stanislav_Kryzhanovs
 */
//@UnitTest
public class GeneralAppExceptionTests {

    @Test(expected = AppException.class)
    public void shouldThrowAppException() {
        throw new AppException();
    }

    @Test(expected = AppException.class)
    public void shouldThrowAppExceptionWithMessage() {
        throw new AppException("message");
    }

    @Test(expected = NoEventException.class)
    public void shouldThrowNoEventException() {
        throw new NoEventException();
    }

    @Test(expected = NoEventException.class)
    public void shouldThrowNoEventExceptionWithMessage() {
        throw new NoEventException("message");
    }

    @Test(expected = NoUserException.class)
    public void shouldThrowNoUserException() {
        throw new NoUserException();
    }

    @Test(expected = NoUserException.class)
    public void shouldThrowNoUserExceptionWithMessage() {
        throw new NoUserException("message");
    }
}
