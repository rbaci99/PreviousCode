package email;
/**
 * Class to represent
 * @author Robert Bacigalupo,112145826,Rec.01
 */
import java.util.Comparator;

public class SubjectComparator implements Comparator<Email> {
    @Override
    public int compare(Email email1,Email email2) {
        return email1.getSubject().compareTo(email2.getSubject());
    }
}
