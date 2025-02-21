package entity;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private int quantity;
    private Publisher publisher;
    public Book(Integer id, String title, String author, int quantity, Publisher publisher){
    	this.id=id;
        this. title=title;
        this.author=author;
        this.quantity=quantity;
        this.publisher=publisher;
    }
    public Book(String title, String author, int quantity, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.publisher = publisher;
    }
    public Integer getID(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public int getQuantity(){
        return quantity;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
