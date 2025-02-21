package entity;


public class Librarian extends User {
    
    public Librarian(String username, String password, String mail, String phn){
        super(null, username, password, mail, phn);
    }
    public Librarian(Integer id, String username, String password, String mail, String phone) {
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
