package email;
/**
 * Class to represent Folder of email
 * @author Robert Bacigalupo,112145826,Rec.01
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Folder implements Serializable {
    private ArrayList<Email> emails = new ArrayList<Email>();
    private String name;// name of folder
    final String ascendingDate = "AscendingDate";//sorting method by Ascending Date
    final String descendingDate = "DescendingDate";//sorting method by descending date
    final String  ascending= "Ascending";// sorting method by ascending subject
    final String descending = "descending";//sorting method by descending subject
    private String currentSortingMethod = descendingDate;//string to represent sorting method of folder
    public Folder() {

    }

    public Folder(String name) {
        this.name = name;
    }

    /**
     * method to remove email at index i
     * @param i index of email to remove
     * @return the email removed
     */
    public Email removeEmail(int i){
        Email removed = emails.get(i);
        emails.remove(i);
        return removed;
    }

    /**
     * method to add email to folder
     * @param email email to add to folder
     */
    public void addEmail(Email email){
        emails.add(email);
        if(currentSortingMethod.equals(descendingDate)){
            this.sortByDateDescending();
        }
        if(currentSortingMethod.equals(ascendingDate)){
            this.sortByDateAscending();
        }
        if(currentSortingMethod.equals(ascending)){
            this.sortBySubjectAscending();
        }
        if(currentSortingMethod.equals(descending)){
            this.sortBySubjectDescending();
        }
    }

    /**
     * method to folder by descending date
     */
    public void sortByDateDescending(){
        Collections.sort(this.emails);
        Collections.reverse(this.emails);
    }

    /**
     * method to sort folder by ascending date
     */
    public void sortByDateAscending(){
        Collections.sort(this.emails);

    }

    /**
     * method to sort folder by ascending subject
     */
    public void sortBySubjectAscending(){
        Collections.sort(this.emails,new SubjectComparator());
    }

    /**
     * method to sort folder by descending subject
     */
    public void sortBySubjectDescending(){
        Collections.sort(this.emails,new SubjectComparator());
        Collections.reverse(this.emails);
    }



    public ArrayList<Email> getEmails() {

        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to print folder and its contents in list format
     */
    public void print(){
        if(this.getEmails().size()!=0) {
            System.out.println(String.format("%5s%-5s%10s%5s%-10s","to","|" ,"time" , "|" , "subject")+ "\n");
            for (int i = 0; i < emails.size(); i++) {
                System.out.println((i+1) + "| "+emails.get(i));


            }
            System.out.println();
        }
        else{
            System.out.println("Folder is empty");
        }
    }
}
