package entity;

public class Student extends User{    
	public Student(String username, String password, String mail, String phone) {
        super(null, username, password, mail, phone); 
    }
    
    public Student(Integer id, String username, String password, String mail, String phone) {
        super(id, username, password, mail, phone);
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
    public String getPhone(){
        return super.getPhone();
    }
    public Integer getID(){
        return super.getID();
    }
    public void setUsername(String username) {
        super.setUsername(username);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setMail(String mail) {
        super.setMail(mail);
    }

    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    public void setID(Integer id) {
        super.setID(id);
    }



}

