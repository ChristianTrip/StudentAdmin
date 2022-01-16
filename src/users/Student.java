package users;

import institution.School;
import java.util.ArrayList;

public class Student extends User {

    // INSTANCES VARIABLES======================================================================

    public static int studNumber;

    // CONSTRUCTORS==============================================================================

    public Student(String firstName, String lastName, int birthYear) {
        super(firstName, lastName, birthYear);
        createID();
        createEmail("KEA");
        studNumber ++;
    }

    public Student(String firstName, String lastName, int birthYear, int key, String id, String eMail, ArrayList<String> subjects) {
        super(firstName, lastName, birthYear, key, id, eMail, subjects);
        studNumber ++;
    }

    // PROTECTED METHODS==============================================================================

    @Override
    protected void createID() {

        String studentID = "";
        String a = this.firstName.toLowerCase().substring(0, 3);
        String b = this.lastName.toLowerCase().substring(0, 2);
        String c = String.valueOf(this.birthYear).substring(2, 4);
        studentID = a + b + c + studNumber;

        this.id = studentID;
    }

    @Override
    protected void createEmail(String schoolName) {

        String emailAdress = this.id + "@" + "stud." + schoolName.substring(0, 3).toLowerCase() + ".dk";

        this.eMail = emailAdress;
    }

    // PUBLIC METHODS==============================================================================

    @Override
    public String toString() {
        return "Student " + getFullName() +
                "\nsubjects: " + getSubjects();
    }

}
