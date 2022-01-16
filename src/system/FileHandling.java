package system;

import institution.Subject;
import users.Student;
import users.Teacher;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandling {

    // CLASS FIELDS==============================================================================

    private PrintWriter printWriter;
    private Scanner reader;
    private final File LOGIN_FILE = new File("resources/loginFile.csv");
    private final File SUBJECT_FILE = new File("resources/subjectFile.csv");
    private final File USER_FILE = new File("resources/userFile.csv");

    // PUBLIC METHODS==============================================================================

    public void saveUsers(ArrayList<User> users){

        try{
            printWriter = new PrintWriter(USER_FILE);

            printWriter.println("firstName; lastName; birthYear; key; id; email; subjects[];");

            for (User user : users) {
                printWriter.println(user.getFirstName() +
                                    ";" + user.getLastName() +
                                    ";" + user.getBirthYear() +
                                    ";" + user.getKey() +
                                    ";" + user.getID() +
                                    ";" + user.getEmail() +
                                    ";" + user.getSubjectNamesAsString());
            }
            printWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public void saveSubjects(ArrayList<Subject> subjects){

        try{
            printWriter = new PrintWriter(SUBJECT_FILE);

            printWriter.println("subjectName; ECTS;");

            for (Subject subject : subjects) {
                printWriter.println(subject.getName() +
                                    "; " + subject.getEcts());
            }
            printWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public ArrayList<User> loadUsers(){

        ArrayList<User> users = new ArrayList<>();

        try{
            reader = new Scanner(USER_FILE);

            reader.nextLine();

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                String[] userList = line.split(";");

                String firstName = userList[0];
                String lastName = userList[1];
                int birthYear = Integer.parseInt(userList[2]);
                int key = Integer.parseInt(userList[3]);
                String id = userList[4];
                String eMail = userList[5];
                ArrayList<String> subjects = new ArrayList<>();

                for (int i = 6; i < userList.length; i ++){
                    subjects.add(userList[i]);
                }

                if (eMail.contains("teach")){
                    Teacher newTeacher = new Teacher(firstName, lastName, birthYear, key, id, eMail, subjects);
                    users.add(newTeacher);
                }
                else{
                    Student newStudent = new Student(firstName, lastName, birthYear, key, id, eMail, subjects);
                    users.add(newStudent);
                }
            }
        }
        catch (FileNotFoundException e){

        }
        return users;
    }

    public ArrayList<Subject> loadSubjects(){

        ArrayList<Subject> subjects = new ArrayList<>();

        try{
            reader = new Scanner(SUBJECT_FILE);
            reader.nextLine();

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                String[] userList = line.split("; ");

                String name = userList[0];
                double ects = Double.parseDouble(userList[1]);

                Subject subject = new Subject(name, ects);
                subjects.add(subject);
            }
        }
        catch (FileNotFoundException e){

        }
        return subjects;
    }

    public void addUserToFile(String userID, String password) {

        if(!loggedUser(userID)){

            try {
                FileWriter fileWriter = new FileWriter(this.LOGIN_FILE, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);

                printWriter.println(userID + ";;;;;" + password + ";;;;;");

                printWriter.flush();
                printWriter.close();

            } catch (FileNotFoundException e) {
                System.out.println("could not find file");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void removeUser(String userID){

        HashMap<String, String> currentPasswords = getLOGIN_FILE();
        LOGIN_FILE.delete();

        if(currentPasswords.containsKey(userID)){
            currentPasswords.remove(userID);
        }

        addUserToFile("ID;;;;;", "Password;;;;;");
        for (Map.Entry<String, String> entry : currentPasswords.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            addUserToFile(key, value);
        }
    }

    public HashMap<String, String> getLOGIN_FILE() {

        HashMap<String, String> logInData = new HashMap<>();

        try {
            Scanner fileScanner = new Scanner(this.LOGIN_FILE);

            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] list = line.split(";;;;;");

                String id = list[0];
                String password = list[1];

                logInData.put(id, password);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return logInData;
    }

    // PRIVATE METHODS==============================================================================

    private boolean loggedUser(String userID){

        HashMap<String, String> currentPasswords = getLOGIN_FILE();

        for (Map.Entry<String, String> entry : currentPasswords.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (userID.equals(key)){
                System.out.println(key + " is already logged");
                return true;
            }
        }
        return false;
    }

}


