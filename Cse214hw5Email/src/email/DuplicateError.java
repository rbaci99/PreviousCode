package email;

/**
 * Class to represent error if duplicate folder is present
 * @author Robert Bacigalupo,112145826,Rec.01
 */

public class DuplicateError extends Exception{
    public DuplicateError(String message) {
        super(message);
    }
}
