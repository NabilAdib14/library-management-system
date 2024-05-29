package repository;

import java.io.*;

import javax.swing.JOptionPane;

import entity.*;

public class BookRepository {
    public BookRepository(){};

    public int getNumberOfTypesOfBooks(){
        FileReader fr = null; 
        BufferedReader br =  null;
        int number=0;
        try{
            fr = new FileReader("repository\\data\\book.txt");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                number++;
            }
        } catch (Exception ex) {
            ex.getMessage();
        } finally{
            try{
                br.close();
                fr.close();
            }catch(Exception ex){
                ex.getMessage();
            }
        }
        return number;
    }
    public int getNumberofBooks(){
        int counter =0;
        FileReader fr = null; 
        BufferedReader br =  null;
        String[] data =new String[3]; 
        try{
            fr = new FileReader("repository\\data\\book.txt");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                data = line.split("\t");
                int quantity = Integer.parseInt(data[2]);
                counter+=quantity;
            }
        } catch (Exception ex) {
            ex.getMessage();
        } finally{
            try{
                br.close();
                fr.close();
            }catch(Exception ex){
                ex.getMessage();
            }
        }
        return counter;
    }
    public Book[] getAvailableBooks() throws IOException{
        FileReader fr = null; 
        BufferedReader br =  null;
        String[] data =new String[3]; 
        Book[] books = new Book[getNumberOfTypesOfBooks()];
        int counter=0;
        try{
            fr = new FileReader("repository\\data\\book.txt");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                data = line.split("\t");
                String title = data[0];
                String author = data[1];
                int quantity = Integer.parseInt(data[2]);
                Book book = new Book(title, author, quantity);
                books[counter++]=book;
            }
        } catch (Exception ex) {
            ex.getMessage();
        } finally{
            try{
                br.close();
                fr.close();
            }catch(Exception ex){
                ex.getMessage();
            }
        }
        return books;
    }
    public void addBook(Book book)throws IOException{
        FileWriter fw=null;
        BufferedWriter bw=null;
        try{
            fw = new FileWriter("repository\\data\\book.txt",true);
            bw = new BufferedWriter(fw);
            bw.write(book.getTitle()+"\t"+book.getAuthor()+"\t"+Integer.toString(book.getQuantity())+"\n");
            fw.flush();
            bw.flush();
            fw.close();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Could not add Book");
        }
    }
    public void removeBook(Book book) throws IOException {
        File originalFile = new File("repository\\data\\book.txt");
        File tempFile = new File("repository\\data\\temp_book.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(originalFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].equals(book.getTitle())&& parts[1].equals(book.getAuthor())&& parts[2].equals(Integer.toString(book.getQuantity()))) {
                    continue; 
                }
                writer.write(line + "\n");
            }
            reader.close();
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while removing book: " + ex.getMessage());
            return;
        }
        if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while removing book: Could not delete original file.");
            return;
        }
        if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while removing book: Could not rename temporary file.");
        }
    }
    public void updateBook(Book oldBook, Book newBook) throws IOException{
        File originalFile = new File("repository\\data\\book.txt");
        File tempFile = new File("repository\\data\\temp_book.txt");
    
        try{
            BufferedReader reader = new BufferedReader(new FileReader(originalFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3 && parts[0].equals(oldBook.getTitle()) && parts[1].equals(oldBook.getAuthor()) && parts[2].equals(Integer.toString(oldBook.getQuantity()))) {
                    writer.write(newBook.getTitle() + "\t" + newBook.getAuthor() + "\t" + Integer.toString(newBook.getQuantity())+ System.lineSeparator());
                } else {
                    writer.write(line + System.lineSeparator());
                }
            }
            reader.close();
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while updating book: " + ex.getMessage());
            return;
        }
            if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while updating book: Could not delete original file.");
            return;
        }
            if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while updating book: Could not rename temporary file.");
        }
    }
}
