package project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    private String filename;
    private String slrcTable;
    private String lrTable;

    public FileWriter(String slrcTable, String lrTable){
        this.filename = ((Long) System.currentTimeMillis()).toString() + ".html";
        this.slrcTable = slrcTable;
        this.lrTable = lrTable;
    }

    public void writeFile() throws IOException{
        FileOutputStream outputStream = new FileOutputStream(this.filename);
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append(this.getHeader());
        byte[] strToBytes = bobTheBuilder.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public String getHeader(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<DOCTYPE! html>\n");
        bobTheBuilder.append("<html lang=\"en\">\n");
        bobTheBuilder.append("<head>\n");
        bobTheBuilder.append("<meta charset=\"utf-8\">\n");
        bobTheBuilder.append("<title>Proyect final</title>\n");
        bobTheBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
        bobTheBuilder.append("</head>\n");
        bobTheBuilder.append("<body>\n");
        bobTheBuilder.append("<h1>HOLA</h1>\n");
        bobTheBuilder.append("</body>\n");
        bobTheBuilder.append("</html>\n");
        return bobTheBuilder.toString();
    }
    
}