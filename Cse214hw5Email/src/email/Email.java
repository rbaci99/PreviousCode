package email;
/**
 * Class to represent email
 * @author Robert Bacigalupo,112145826,Rec.01
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;


public class Email implements Serializable,Comparable{
    private String to;//who email is intended to
    private String cc;//String to represent Carbon copy
    private String bcc;//String to represent Blind Carbon Copy
    private String subject;//Subject of email
    private String body;//Content of email
    private GregorianCalendar timestamp;//when email was created
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");


    public Email(String to, String cc, String bcc, String subject, String body) {
        timestamp = new GregorianCalendar();
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
    }

    public Email() {
        timestamp = new GregorianCalendar();

    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setCC(String cc) {
        this.cc = cc;
    }

    public String getCC() {
        return this.cc;
    }

    public void setBCC(String bcc) {
        this.bcc = bcc;

    }
    public int compareTo(Object obj){
        Email toCompare = (Email) obj;
        return this.getTimestamp().compareTo(toCompare.getTimestamp());
    }

    public String getBcc() {
        return bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

@Override
/**
 * toString method for String
 */
    public String toString() {
       String time = sdf.format(timestamp.getTime());
       return String.format("%s%-5s%10s%5s%10s",to,"|" ,time , "|" , subject);
    }
}

