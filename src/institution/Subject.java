package institution;

import users.Student;
import users.Teacher;
import users.User;
import java.util.ArrayList;

public class Subject {

    // INSTANCE FIELDS==============================================================================

    private String name;
    private double ects;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    // CONSTRUCTORS==============================================================================

    public Subject(String name, double ects) {

        this.name = name;
        this.ects = ects;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    // METHODS==============================================================================

    public boolean containUser(User user){

        if (students.contains(user) || teachers.contains(user)){
            return true;
        }
        return false;
    }

    public double getEcts() {

        return ects;
    }

    public String getName(){

        return this.name;
    }

    private void addStudent(Student student){
        if (!this.students.contains(student)){
            this.students.add(student);
        }
        else{
            System.out.println(student.getFirstName() + " is already enrolled to " + this.name);
        }
    }

    private void addTeacher(Teacher teacher){

        if(this.teachers.contains(teacher)){
            System.out.println(teacher.getFullName() + " is already enrolled to " + this.name);
        }
        else if (hasTwoTeachers()){
            System.out.println("there can be only two teachers per subject");
        }
        else {
            this.teachers.add(teacher);
        }
    }

    public void addUser(User user){

        if(user.getClass().equals(Student.class)){
            addStudent((Student)user);
        }
        else if (user.getClass() == Teacher.class){
            addTeacher((Teacher)user);
        }
    }

    public void removeUser(User user){

        if (user.getClass() == Student.class){
            students.remove(user);
        }
        else{
            teachers.remove(user);
        }
    }

    private boolean hasTwoTeachers(){

        if (this.teachers.size() == 2){
            return true;
        }
        return false;
    }

    private String viewUsers(){

        String toReturn = "";

        toReturn += "- Teachers: \n";
        for (Teacher teacher : this.teachers) {
            toReturn += "   - " + teacher.getFullName() + "\n";
        }
        toReturn += "- Students: \n";
        for (Student student : this.students) {
            toReturn += "   - " + student.getFullName() + "\n";
        }

        return toReturn;
    }

    public void viewSubject(){

        System.out.println("-----------------------------");
        System.out.println(this.name + " (" + this.ects + " ECTS-points)");
        System.out.println("-----------------------------");
        viewUsers();
    }

    @Override
    public String toString() {

        return "\n" + this.name + " (" + this.ects + " ECTS-points)\n" + viewUsers();
    }
}
