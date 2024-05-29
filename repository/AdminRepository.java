package repository;
import entity.*;
import java.io.*;

import javax.swing.JOptionPane;


public class AdminRepository{
    public AdminRepository(){};
    
    public int getNumberofStudents(){
        FileReader fr = null; 
        BufferedReader br =  null;
        int number=0;
        try{
            fr = new FileReader("repository/data/student.txt");
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
    public int getNumberofLibrarians(){
        FileReader fr = null; 
        BufferedReader br =  null;
        int number=0;
        try{
            fr = new FileReader("repository/data/librarian.txt");
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
    public Student[] getStudents(){
        FileReader fr = null; 
        BufferedReader br =  null;
        String[] data =new String[3];
        Student[] students = new Student[getNumberofStudents()];
        for(int i=0; i<getNumberofStudents(); i++){
        try{
            fr = new FileReader("repository\\data\\student.txt");
            br = new BufferedReader(fr);
            String line;
            int counter=0;
            while ((line = br.readLine()) != null) {
                data = line.split("\t");
                Student s1 = new Student(data[0],data[1],data[2]);
                students[counter++]=s1;
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
        }
        return students;
    }
    public Librarian[] getLibrarians(){
        FileReader fr = null; 
        BufferedReader br =  null;
        String[] data =new String[3];
        Librarian[] librarians = new Librarian[getNumberofLibrarians()];
        for(int i=0; i<getNumberofLibrarians(); i++){
        try{
            fr = new FileReader("repository\\data\\librarian.txt");
            br = new BufferedReader(fr);
            String line;
            int counter=0;
            while ((line = br.readLine()) != null) {
                data = line.split("\t");
                Librarian l1 = new Librarian(data[0],data[1],data[2]);
                librarians[counter++]=l1;
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
        }
        
        return librarians;
      
    }
    public void addLibrarian(Librarian librarian)throws IOException{
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter("repository\\data\\librarian.txt",true);
            bw = new BufferedWriter(fw);
            bw.write(librarian.getUsername()+"\t"+librarian.getPassword()+"\t"+librarian.getMail()+"\n");
            fw.flush();
            bw.flush();
            fw.close();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Could not add librarian");
        }
    }
    public void removeLibrarian(Librarian librarian) throws IOException {
        File originalFile = new File("repository\\data\\librarian.txt");
        File tempFile = new File("repository\\data\\temp_librarian.txt");

        try{
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].equals(librarian.getUsername())&& parts[1].equals(librarian.getPassword())&& parts[2].equals(librarian.getMail())) {
                    continue; 
                }
                bw.write(line + "\n");
            }
            br.close();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while removing librarian: " + ex.getMessage());
            return;
        }
        if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while removing librarian: Could not delete original file.");
            return;
        }
        if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while removing librarian: Could not rename temporary file.");
        }
    }
    public void updateLibrarian(Librarian oldLibrarian, Librarian newLibrarian) throws IOException{
        if(oldLibrarian==null || newLibrarian==null){
            JOptionPane.showMessageDialog(null, "Librarian to (be) replace(d) is null");
        }
        File originalFile = new File("repository\\data\\librarian.txt");
        File tempFile = new File("repository\\data\\temp_librarian.txt");
    
        try{
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));    
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].equals(oldLibrarian.getUsername()) && parts[1].equals(oldLibrarian.getPassword()) && parts[2].equals(oldLibrarian.getMail())) {
                    bw.write(newLibrarian.getUsername() + "\t" + newLibrarian.getPassword() + "\t" + newLibrarian.getMail()+ "\n");
                } else {
                    bw.write(line + "\n");
                }
            }
            br.close();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while updating Librarian: " + ex.getMessage());
            return;
        }
            if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while updating Librarian: Could not delete original file.");
            return;
        }
            if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while updating Librarian: Could not rename temporary file.");
        }
    }
    public void addStudent(Student student)throws IOException{
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter("repository\\data\\student.txt",true);
            bw = new BufferedWriter(fw);
            bw.write(student.getUsername()+"\t"+student.getPassword()+"\t"+student.getMail()+"\n");
            fw.flush();
            bw.flush();
            fw.close();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Could not add student");
        }
    }
    public void removeStudent(Student student) throws IOException {
        File originalFile = new File("repository\\data\\student.txt");
        File tempFile = new File("repository\\data\\temp_student.txt");

        try{
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].equals(student.getUsername())&& parts[1].equals(student.getPassword())&& parts[2].equals(student.getMail())) {
                    continue; 
                }
                bw.write(line + "\n");
            }
            br.close();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while removing student: " + ex.getMessage());
            return;
        }
        if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while removing student: Could not delete original file.");
            return;
        }
        if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while removing student: Could not rename temporary file.");
        }
    }
    public void updateStudent(Student oldStudent, Student newStudent) throws IOException{
        File originalFile = new File("repository\\data\\student.txt");
        File tempFile = new File("repository\\data\\temp_student.txt");
    
        try{
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));    
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].equals(oldStudent.getUsername()) && parts[1].equals(oldStudent.getPassword()) && parts[2].equals(oldStudent.getMail())) {
                    bw.write(newStudent.getUsername() + "\t" + newStudent.getPassword() + "\t" + newStudent.getMail()+ "\n");
                } else {
                    bw.write(line + "\n");
                }
            }
            br.close();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while updating student: " + ex.getMessage());
            return;
        }
            if (!originalFile.delete()) {
            JOptionPane.showMessageDialog(null, "Error while updating Student: Could not delete original file.");
            return;
        }
            if (!tempFile.renameTo(originalFile)) {
            JOptionPane.showMessageDialog(null, "Error while updating Student: Could not rename temporary file.");
        }
    }
        
}