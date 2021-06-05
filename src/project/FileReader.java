package project;

import java.io.FileInputStream;
import java.util.Scanner;

import project.exceptions.IncorrectGrammarException;
import project.exceptions.NotCorrectFormatException;
public class FileReader {

    private FileInputStream fis;
    private Scanner sc;
    private String filename;

    /**
     * Constructor for the FileReader class, receives a file that has Productions in the correct format
     * @param filename The name of the input file
     * @throws Exception - If the filename does not lead to a file
     */
    public FileReader(String filename) throws Exception{
        this.filename = filename;
        this.fis = new FileInputStream(this.filename);       
        this.sc = new Scanner(fis);
    }

    /**
     * This method creates a Context-Free Grammar from the input file
     * @return Glc
     * @throws NotCorrectFormatException If the production is not of the form S -> A
     * @throws IncorrectGrammarException If the grammar is missing a core element
     */
    public Glc readFile() throws NotCorrectFormatException, IncorrectGrammarException {
        Glc grammar = new Glc();
        while(sc.hasNextLine()){  
            Production prod = new Production(sc.nextLine());
            grammar.insertProduction(prod); 
        }
        sc.close();
        grammar.setStartSymbol(grammar.getProductions().get(1).getLeftSide());
        for(Production p : grammar.getProductions().values()){
            grammar.insertNonTerminal(p.getLeftSide());
        }
        for(Production p : grammar.getProductions().values()){
            for(String s : p.getRightSide()){
                if(!grammar.getNonTerminals().contains(s)){
                    grammar.insertTerminal(s);
                }
            }
        }
        grammar.checkValidity();
        grammar.insertProduction(0, new Production(grammar.getStartSymbol() + "'", grammar.getStartSymbol()));
        return grammar;
    }

}  