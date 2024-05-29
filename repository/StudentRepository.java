package repository;
import entity.*;
import java.io.*;

import javax.swing.JOptionPane;
public class StudentRepository {
    public StudentRepository(){};
    
    public void addStudent(Student student){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("repository\\data\\student.txt",true));
            bw.write(student.getUsername()+"\t"+student.getPassword()+"\t"+student.getMail()+"\n");
            bw.flush();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Could not add student");
        }
    }
    public void updateStudent(Student student){
        StringBuilder updatedContent = new StringBuilder();
        boolean updated = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("repository\\data\\student.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data[0].equals(student.getUsername())) {
                    String updatedLine = data[0] + "\t" + student.getPassword() + "\t" + student.getMail();
                    updatedContent.append(updatedLine).append(System.lineSeparator());
                    updated = true;
                } else {
                    updatedContent.append(line).append(System.lineSeparator());
                }
            }
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if (!updated) {
            JOptionPane.showMessageDialog(null, "Error updating profile");
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("repository\\data\\student.txt"));
                bw.write(updatedContent.toString());
                bw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
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
}
