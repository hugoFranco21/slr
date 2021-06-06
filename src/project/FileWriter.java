package project;

import java.io.FileOutputStream;

public class FileWriter {
    private String filename;
    private String slrcTable;
    private String lrTable;

    public FileWriter(String slrcTable, String lrTable){
        this.filename = ((Long) System.currentTimeMillis()).toString() + ".html";
        this.slrcTable = slrcTable;
        this.lrTable = lrTable;
    }

    public void writeFile(){
        FileOutputStream outputStream = new FileOutputStream(this.filename);
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append(this.getHeader());
    }

    public String getHeader(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<DOCTYPE! html>\n");
        
    }
    
}