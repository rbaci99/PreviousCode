package email;
/**
 * Class to represent mailbox
 * @author Robert Bacigalupo,112145826,Rec.01
 */

import java.io.*;
import java.util.*;
public class Mailbox implements Serializable {
    private Folder inbox = new Folder("Inbox");// main inbox
    private Folder trash = new Folder("Trash");//folder to hold emails to be deleted
    private ArrayList<Folder> folders = new ArrayList<Folder>();// list of custom folders
    public static Mailbox mailbox;

    public Mailbox() {
    }

    /**
     * Method to add email, uses user input to create email then add it to inbox
     */

    public void composeEmail() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter recipient(To): ");
        String to = stdin.nextLine();
        System.out.println("Enter carbon copy recipients (CC): ");
        String cc = stdin.nextLine();
        System.out.println("Enter blind carbon copy recipients (BCC): ");
        String bcc = stdin.nextLine();
        System.out.println("Enter subject line: ");
        String subject = stdin.nextLine();
        System.out.println("Enter body: ");
        String body = stdin.nextLine();
        Email email = new Email(to, cc, bcc, subject, body);
        inbox.addEmail(email);
        System.out.println("Email successfully added to inbox");
    }

    /**
     * adds folder to folders list
     * @param folder folder to add to folders
     * @throws DuplicateError thrown if folder with same name is already present
     */
    public void addFolder(Folder folder) throws DuplicateError {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(folder.getName())) {
                throw new DuplicateError("Duplicate folder");

            }
        }
        folders.add(folder);

    }

    /**
     * removes folder from folders list, leaving emails to be collected by JVM garbage collection
     * @param folder folder to remove
     */
    public void deleteFolder(Folder folder) {
        folders.remove(folder);

    }

    /**
     * method to delete email, removing email from current folder leaving it for JVM Garbage collection
     * @param email email to delete
     */
    public void deleteEmail(Email email) {
        if (inbox.getEmails().indexOf(email) != -1) {
            trash.addEmail(inbox.removeEmail(inbox.getEmails().indexOf(email)));
        }
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getEmails().indexOf(email) != -1) {
                folders.get(i).removeEmail(folders.get(i).getEmails().indexOf(email));
            }
        }


    }

    /**
     * method to move email from folder to another
     * @param email email to move
     * @param target destination to move email to
     */
    public void moveEmail(Email email, Folder target) {
        if (folders.indexOf(target) != -1) {

            if (inbox.getEmails().indexOf(email) != -1) {
                inbox.removeEmail(inbox.getEmails().indexOf(email));
            }

                for (int i = 0; i < folders.size(); i++) {
                    if (folders.get(i).getEmails().indexOf(email) != -1) {
                        folders.get(i).removeEmail(folders.get(i).getEmails().indexOf(email));
                    }
                }
            if (target.getEmails().indexOf(email) == -1)
                target.addEmail(email);
            }
        else
            inbox.addEmail(email);
    }

    /**
     * method to delete all emails in trash folder
     */
    public void clearTrash() {
        for (int i = 0; i < trash.getEmails().size(); i++) {
            trash.removeEmail(i);
        }

    }

    public Folder getFolder(String name) {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(name))
                return folders.get(i);
        }
        return null;
    }
/**
 * helper method to run submenu options on folder in main method
 * Methods are implemented depending on user input in a switch case
 */
    public void runSubMenu(Folder folder) {
        boolean running = true;
        Scanner stdin = new Scanner(System.in);
        while (running) {
            printSubMenu();
            try {
                String input = stdin.next();
                switch (input.toUpperCase()) {
                    case "M":
                        folder.print();
                        System.out.print("Enter email index");
                        int i = stdin.nextInt();
                        System.out.println(-1+"|"+"Trash");
                        System.out.println(0+"|"+"Inbox");
                        printMailbox();
                        System.out.print("Enter index of destination");
                        int loc = stdin.nextInt();
                        if (loc == -1) {
                            moveEmail(folder.getEmails().get(i-1), trash);
                            System.out.println("Email moved to trash");
                        } else if (loc == 0) {
                            moveEmail(folder.getEmails().get(i-1), inbox);
                            System.out.println("Email moved to inbox");
                        } else if(loc >0 && loc <= folder.getEmails().size()+1) {
                            moveEmail(folder.getEmails().get(i-1), folders.get(loc - 1));
                            System.out.println("Email moved to "+ folders.get(loc-1).getName());
                        }else{
                            moveEmail(folder.getEmails().get(i), inbox);
                            System.out.println("No folder at that index so email moved to inbox");
                        }


                        break;
                    case "V":
                        System.out.println("Enter index of email to view");
                        int ind = stdin.nextInt();
                        Email viewed = folder.getEmails().get(ind - 1);
                        System.out.println("To: " + viewed.getTo());
                        System.out.println("Cc: " + viewed.getCC());
                        System.out.println("Bcc: " + viewed.getBcc());
                        System.out.println("Subject: " + viewed.getSubject());
                        System.out.println(viewed.getBody());
                        break;
                    case "SA":
                        folder.sortBySubjectAscending();
                        folder.setCurrentSortingMethod(folder.ascending);
                        folder.print();
                        break;
                    case "SD":
                        folder.sortBySubjectDescending();
                        folder.setCurrentSortingMethod(folder.descending);
                        folder.print();
                        break;
                    case "DD":
                        folder.sortByDateDescending();
                        folder.setCurrentSortingMethod(folder.descendingDate);
                        folder.print();
                        break;
                    case "DA":
                        folder.sortByDateAscending();
                        folder.setCurrentSortingMethod(folder.ascendingDate);
                        folder.print();
                        break;
                    case "D":
                        System.out.println("Enter index of email to delete");
                        int idx = stdin.nextInt();
                        deleteEmail(folder.getEmails().get(idx - 1));
                        break;
                    case "R":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid folder option was entered");
                }
            }catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid index");
            }
            catch(InputMismatchException er){
                System.out.println("Invalid input entered");
            }catch (Exception e){
                System.out.println("Unexpected error occurred");
            }
        }


    }

    /**
     * method to print sub menu options
     */
    public static void printSubMenu() {
        System.out.println("M-move \nD-Delete \nV-View Content of email \nSA-Sort by Subject ascending");
        System.out.println("SD-Sort by subject descending \nDA-Sort by date ascending \nDD-Sort by date descending");
        System.out.println("R-return");
    }
/**
 * prints contents of mailbox
 */
    public void printMailbox() {

        for (int i = 0; i < folders.size(); i++) {
            System.out.println((i+1)+"|"+folders.get(i).getName());

        }
    }


    public Folder getTrash() {
        return trash;
    }

    public void setTrash(Folder trash) {
        this.trash = trash;
    }

    public Folder getInbox() {
        return inbox;
    }

    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }
/**
 * prints options for mailbox
 */
    public static void printMenu() {
        System.out.println("A-Add folder \nR-Remove folder \nC-Compose Email \nF-View Folder");
        System.out.println("I-View inbox \nT-View Trash \nE-Empty trash");
        System.out.println("Q-Quit");

    }

    /**
     *main method, opens mailbox from save file, if no file is found, file is created
     * menu is printed and presented to user prompting user to enter option into switch case
     * to implement mailbox methods
     * when finished mailbox is saved to file
     *
     */
    public static void main(String[] args) {
        Mailbox mailbox = new Mailbox();
        Scanner stdin = new Scanner(System.in);
        try {
            FileInputStream file = new FileInputStream("mailbox.obj");
            ObjectInputStream fin = new ObjectInputStream(file);
            mailbox = (Mailbox) fin.readObject();
            file.close();
        } catch (IOException a) {

        } catch (ClassNotFoundException c) {

        }

        boolean running = true;
        while (running) {
            try {

                printMenu();
                String input = stdin.nextLine();
                switch (input.toUpperCase()) {
                    case "A":
                        System.out.println("Enter name of folder");
                        String name = stdin.nextLine();
                        Folder folder = new Folder(name);
                        mailbox.addFolder(folder);
                        break;
                    case "R":
                        System.out.println("Enter name to be removed");
                        String remove = stdin.nextLine();
                        boolean found = false;
                        for (int i = 0; i < mailbox.getFolders().size(); i++) {
                            if (mailbox.getFolders().get(i).getName().equals(remove)) {
                                mailbox.getFolders().remove(i);
                                System.out.println("Folder removed");
                                found = true;
                            } else {
                                found = false;
                            }

                        }
                        if (found == false)
                            System.out.println("Folder not found(Inbox and Trash cannot be removed)");

                        break;
                    case "C":
                        mailbox.composeEmail();
                        break;
                    case "F":
                        boolean isFound = true;
                        mailbox.printMailbox();
                        System.out.println("\nEnter name of folder to view");
                        String viewed = stdin.nextLine();
                        for (int i = 0; i < mailbox.getFolders().size(); i++) {
                            if (mailbox.getFolders().get(i).getName().equals(viewed)) {
                                Folder toView = mailbox.getFolders().get(i);
                                toView.print();
                                mailbox.runSubMenu(toView);
                            } else {
                                isFound = false;
                            }

                        }
                        if (isFound == false) {
                            System.out.println("Folder not found");
                        }
                        break;
                    case "I":
                        mailbox.getInbox().print();
                        mailbox.runSubMenu(mailbox.getInbox());
                        break;
                    case "T":
                        mailbox.getTrash().print();
                        mailbox.runSubMenu(mailbox.getTrash());
                        break;
                    case "E":
                        mailbox.clearTrash();
                        break;
                    case "Q":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid menu option");


                }
            } catch (InputMismatchException ex) {
                System.out.println("Input Mismatch occurred");
            }catch(DuplicateError er){
                System.out.println("Duplicate folder");
            }
        }
        stdin.close();
        try {
            FileOutputStream file = new FileOutputStream("mailbox.obj");
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(mailbox);
            fout.close();
        } catch (IOException a) {
            System.out.println("IO exception occurred");
        }
        catch(Exception e){
           System.out.println("Unexpected exception occurred");
        }
    }
}
