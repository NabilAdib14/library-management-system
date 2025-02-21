package entity;

public class Publisher{
    private Integer id;
    private String username;
    private String mail;
    private String phn;
    
    public Publisher(Integer id, String username, String mail, String phn){
        this.id=id;
        this.username=username;
        this.mail=mail;
        this.phn = phn;
    }
    public Publisher(String username, String mail, String phn){
        this.id=null;
        this.username=username;
        this.mail=mail;
        this.phn = phn;
    }
    public String getUsername(){
        return username;
    }
    public String getMail(){
        return mail;
    }
    public String getPhone(){
        return phn;
    }
    public Integer getID(){
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phn) {
        this.phn = phn;
    }

}

