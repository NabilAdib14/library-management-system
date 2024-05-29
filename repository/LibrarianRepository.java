package repository;

import java.io.*;

import javax.swing.JOptionPane;

import entity.*;

public class LibrarianRepository {
    public LibrarianRepository(){};

    public void addLibrarian(Librarian librarian){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("repository\\data\\librarian.txt",true));
            bw.write(librarian.getUsername()+"\t"+librarian.getPassword()+"\t"+librarian.getMail()+"\n");
            bw.flush();
            bw.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Could not add librarian");
        }
    }
    public void updateLibrarian(Librarian librarian){
        StringBuilder updatedContent = new StringBuilder();
        boolean updated = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("repository\\data\\librarian.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data[0].equals(librarian.getUsername())) {
                    String updatedLine = data[0] + "\t" + librarian.getPassword() + "\t" + librarian.getMail();
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
                BufferedWriter bw = new BufferedWriter(new FileWriter("repository\\data\\librarian.txt"));
                bw.write(updatedContent.toString());
                bw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
}
