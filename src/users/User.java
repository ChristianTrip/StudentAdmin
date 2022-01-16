package users;

import institution.Subject;
import java.util.ArrayList;

public abstract class User {

    // INSTANCE FIELDS======================================================================

    protected String firstName;
    protected String lastName;
    protected int birthYear;
    protected String id;
    protected String eMail;
    protected int key;
    public static int userCount;


    protected ArrayList<Subject> subjects = new ArrayList<>();
    protected ArrayList<String> subjectNames;

    // CONSTRUCTORS==============================================================================

    public User(String firstName, String lastName, int birthYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.key = generateKey();
        this.subjects = new ArrayList<>();
        userCount ++;
    }

    public User(String firstName, String lastName, int birthYear, int key, String id, String eMail, ArrayList<String> subjects){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.key = key;
        this.id = id;
        this.eMail = eMail;
        this.subjectNames = subjects;
        userCount ++;
    }

    // PRIVATE METHODS==============================================================================

    private int generateKey(){

        int randomnumber = (int) (Math.random() * 10) + 1;

        return randomnumber;
    }

    // ABSTRACT METHODS==============================================================================

    abstract void createID(); // A user ID is a unique string that is made up by the users full name and year of birth and the number of total current users.


    abstract void createEmail(String SchoolName);

    // PUBLIC METHODS==============================================================================

    public ArrayList<String> getSubjectNames() {
        return subjectNames;
    }

    public String getSubjectNamesAsString(){

        String toReturn = "";

        for (Subject subject : this.subjects) {
            toReturn += subject.getName() + ";";
        }
        return toReturn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getEmail() {
        return eMail;
    }

    public String getID(){
        return this.id;
    }

    public int getKey(){
        return this.key;
    }

    public void addSubject(Subject subject){

        if(!this.subjects.contains(subject)){

            this.subjects.add(subject);
        }
        else{

        }
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
    }

    public void viewSubjects(){

        int count = 1;

        for (Subject subject : this.subjects) {
            System.out.println(count + ") " + subject.toString());
            count ++;
        }
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

}
