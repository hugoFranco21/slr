package project;

import java.io.FileInputStream;
import java.util.Scanner;
public class FileReader {

    private FileInputStream fis;
    private Scanner sc;
    private String filename;

    public FileReader(String filename) throws Exception{
        this.filename = filename;
        this.fis = new FileInputStream(this.filename);       
        this.sc = new Scanner(fis);
    }

    public Glc readFile(){
        Glc grammar = new Glc();
        while(sc.hasNextLine()){  
            Production prod = new Production(sc.nextLine()); 
        }  
        sc.close(); 
        return grammar;
    }
}  