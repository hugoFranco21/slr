package project;

import java.io.FileInputStream;
import java.util.Scanner;

import project.exceptions.IncorrectGrammarException;
import project.exceptions.NotCorrectFormatException;
public class FileReader {

    private FileInputStream fis;
    private Scanner sc;
    private String filename;

    public FileReader(String filename) throws Exception{
        this.filename = filename;
        this.fis = new FileInputStream(this.filename);       
        this.sc = new Scanner(fis);
    }

    public Glc readFile() throws NotCorrectFormatException, IncorrectGrammarException {
        Glc grammar = new Glc();
        while(sc.hasNextLine()){  
            Production prod = new Production(sc.nextLine());
            grammar.insertProduction(prod); 
        }
        sc.close();
        grammar.setStartSymbol(grammar.getProductions().get(1).getLeftSide());
        grammar.checkValidity();
        grammar.insertProduction(0, new Production(grammar.getStartSymbol() + "'", grammar.getStartSymbol()));
        return grammar;
    }

    @Override
    public String toString() {
        return "";
    }
}  