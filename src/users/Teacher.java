package users;

import institution.School;
import java.util.ArrayList;

public class Teacher extends User {

    // INSTANCES VARIABLES======================================================================

    public static int teachNumber;

    // CONSTRUCTORS==============================================================================

    public Teacher(String firstName, String lastName, int birthYear) {
        super(firstName, lastName, birthYear);
        this.subjects = new ArrayList<>();
        createID();
        createEmail("KEA");
        teachNumber++;
    }

    public Teacher(String firstName, String lastName, int birthYear, int key, String id, String eMail, ArrayList<String> subjects) {
        super(firstName, lastName, birthYear, key, id, eMail, subjects);
        teachNumber++;
    }

    // PROTECTED METHODS==============================================================================

    @Override
    protected void createID() {

        String teacherID = "";
        String a = this.firstName.toLowerCase().substring(0, 3);
        String b = this.lastName.toLowerCase().substring(0, 2);
        String c = String.valueOf(this.birthYear).substring(2, 4);
        teacherID = a + b + c + teachNumber;

        this.id = teacherID;
    }

    @Override
    protected void createEmail(String schoolName) {

        String emailAdress = this.id + "@" + "teach." + schoolName.substring(0, 3).toLowerCase() + ".dk";

        this.eMail = emailAdress;
    }

    // PUBLIC METHODS==============================================================================

    @Override
    public String toString() {
        return "Teacher " + getFullName() +
                "\nsubjects: " + getSubjects();

    }

}
