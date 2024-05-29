package entity;

public class Admin{
    private static final String name ="admin";
    private static final String password="123";
    private static Student students[];
    private static Librarian librarians[];
    public Admin(){
        students=new Student[100]; //had to initialize
        librarians=new Librarian[100]; // had to initialize
    }
    public static String getName(){
        return name;
    }
    public static String getPassword(){
        return password;
    }
    public static Student[] getStudents(){
        return students;
    }
    public static Librarian[] getLibrarians(){
        return librarians;
    }
    
}