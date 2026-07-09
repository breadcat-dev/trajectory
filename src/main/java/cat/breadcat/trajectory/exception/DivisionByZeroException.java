package cat.breadcat.trajectory.exception;

public class DivisionByZeroException extends ArithmeticException
{
    public DivisionByZeroException(String message)
    {
        super("Cannot divide by zero: " + message);
    }
}
