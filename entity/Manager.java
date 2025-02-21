package entity;

public class Manager{
    private static final String name ="admin";
    private static final String password="123";
    private static final String mail="admin@mail.com";
    private static final String phn="0123456789";
    private static Student students[];
    private static Librarian librarians[];
    public Manager(){
        students=new Student[100]; 
        librarians=new Librarian[100];
    }
    public static String getName(){
        return name;
    }
    public static String getPassword(){
        return password;
    }
    public static String getMail(){
        return mail;
    } 
    public static String getPhn() {
    	return phn;
    }
    public static Student[] getStudents(){
        return students;
    }
    public static Librarian[] getLibrarians(){
        return librarians;
    }
    
}