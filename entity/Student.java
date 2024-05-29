package entity;

public class Student extends User{
 
    public Student(String username, String password, String mail){
        super(username, password, mail);
    }
    public String getUsername(){
        return super.getUsername();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public String getMail(){
        return super.getMail();
    }
    public String toString(){
        return super.getUsername()+"\t"+super.getPassword()+"\t"+super.getMail();
    }
}

