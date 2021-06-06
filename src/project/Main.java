package project;

public class Main {
    public static void main(String[] args){
        try {
            FileReader input = new FileReader(args[0]);
            Glc grammar = input.readFile();
            System.out.println(grammar.toString());
            SLRClosure slrc = new SLRClosure(grammar);
            System.out.println(slrc.toString());
            FileWriter fileWriter = new FileWriter(slrc.toHTML(), "lrTabl");
            fileWriter.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}