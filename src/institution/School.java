package institution;

import system.FileHandling;
import users.Student;
import users.Teacher;
import users.User;

import java.util.ArrayList;

public final class School {

    // INSTANCE FIELDS==============================================================================

    private final String NAME = "KEA Københavns Erhvervsakademi";

    private ArrayList<Subject> subjects;
    private ArrayList<User> users;

    private FileHandling fileHandling;

    // CONSTRUCTORS==============================================================================

    public School(){

        this.fileHandling = new FileHandling();
        this.subjects = fileHandling.loadSubjects();
        this.users = fileHandling.loadUsers();
        setupUsersAndSubjects();
    }

    // PRIVATE METHODS==============================================================================

    private void setupUsersAndSubjects(){
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);

            for (int j = 0; j < this.subjects.size(); j++) {
                Subject subject = this.subjects.get(j);
                String subjectName = subject.getName();
                for (int k = 0; k < user.getSubjectNames().size(); k++) {
                    String userSubjectName = user.getSubjectNames().get(k);

                    if(userSubjectName.equals(subjectName) && !user.getSubjects().contains(this.subjects.get(j))){
                        user.addSubject(subject);
                        subject.addUser(user);
                    }
                }
            }
        }
    }

    // PUBLIC METHODS===============================================================================

    public void saveUsers(){

        fileHandling.saveUsers(this.users);
    }

    public void saveSubjects(){

        fileHandling.saveSubjects(this.subjects);
    }

    public String getNAME() {

        return NAME;
    }

    public ArrayList<Subject> getSubjects() {

        return subjects;
    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public void addSubject(Subject newSubject) {

        boolean subjectExist = false;

        for (Subject subject : this.subjects) {
            if (newSubject.getName().equals(subject.getName())){
                subjectExist = true;
                break;
            }
        }

        if (!subjectExist){
            this.subjects.add(newSubject);
        }
        else{
            System.out.println(newSubject.getName() + " already exist");
        }
    }

    public void removeSubject(Subject subject){

        this.subjects.remove(subject);
    }

    public void addUser(User newUser) {

        boolean userExist = false;

        for (User user : this.users) {
            if(newUser.getID().equals(user.getID())){
                userExist = true;
                break;
            }
        }

        if(!userExist){
            this.users.add(newUser);
        }
        else{
            System.out.println("The user " + newUser.getID() + " already exist");
        }
    }

    public void removeUser(User user){

        this.users.remove(user);
        fileHandling.removeUser(user.getID());
    }

    public String getUserSubjectsAsString(User user){

        String toReturn = "";

        for (Subject subject : this.subjects) {
            if (subject.containUser(user)){
                toReturn += "- " + subject.getName() + " (" + subject.getEcts() + " ECTS-points)\n";
            }
        }
        return user.getFullName() + " attends:\n" + toReturn;
    }

    public void viewSubjects(){

        int count = 1;
        for (Subject subject : this.subjects) {
            System.out.println(count + ") " + subject.getName() + " (" + subject.getEcts() + " ECTS-points)");
            count ++;
        }
    }

    public String getPrintoutOfAllUsers(){

        String header = "------------------------------\n" +
                        "LIST OF CURRENT USERS\n" +
                        "------------------------------\n";
        String teachers = "TEACHERS:\n------------------------------\n";
        String students = "STUDENTS:\n------------------------------\n";

        int teacherCount = 0;
        int studentCount = 0;
        for (User user : this.users) {
            if(user.getClass().equals(Teacher.class)){
                teachers += (teacherCount + 1) + ") " + user.getFullName() + " - (" + user.getID() + ")\n";
                teachers += "   Birth Year - " + user.getBirthYear() + "\n";
                teachers += "   E-mail - " + user.getEmail() + "\n\n";
                teacherCount ++;
            }
            else if (user.getClass().equals(Student.class)){
                students += (studentCount + 1) + ") " + user.getFullName() + " - (" + user.getID() + ")\n";
                students += "   Birth Year - " + user.getBirthYear() + "\n";
                students += "   E-mail - " + user.getEmail() + "\n\n";
                studentCount ++;
            }
        }

        String numberOfUsers = "There are currently " + this.users.size() + " users in total\n" +
                "Number of teachers: " + (teacherCount) + "\n" +
                "Number of students: " + (studentCount)+ "\n" +
                "------------------------------\n";

        return header + numberOfUsers + teachers + "------------------------------\n" + students;
    }

    public String getListOfNumberedUsers(){

        String userList = "";

        int count = 1;
        for (User user : this.users) {
            userList += count + ") " + user.getFullName() + " - (" + user.getID() + ") - " + user.getClass() + "\n";
            count ++;
        }
        return userList;
    }

    @Override
    public String toString() {

        String toReturn =   "-----------------------------------------------\n" +
                            "School Name: " + this.NAME. toUpperCase() + "\n\n" +
                            "Users: \n" + getListOfNumberedUsers() + "\n" +
                            "Subjects: " + subjects.toString() +
                            "-----------------------------------------------";
        return toReturn;
    }
}
