package project;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    private String filename;
    private String slrcTable;
    private String lrTable;

    /**
     * Constructor for the FileWriter class
     * @param String slrcTable The SLRC Table HTML
     * @param String lrTable The LR Table HTML
     */
    public FileWriter(String slrcTable, String lrTable){
        this.filename = ((Long) System.currentTimeMillis()).toString() + ".html";
        this.slrcTable = slrcTable;
        this.lrTable = lrTable;
    }

    /**
     * This functions prints the tables to an HTML file
     * using the current timestamp as name
     * @throws IOException
     */
    public void writeFile() throws IOException{
        FileOutputStream outputStream = new FileOutputStream(this.filename);
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append(this.getHeader());
        bobTheBuilder.append(this.slrcTable);
        bobTheBuilder.append(this.getFooter());
        byte[] strToBytes = bobTheBuilder.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    /**
     * This function creates the header for an HTML document,
     * up to the start of the body tag
     * @return String HTML header
     */
    public String getHeader(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<DOCTYPE! html>\n");
        bobTheBuilder.append("<html lang=\"en\">\n");
        bobTheBuilder.append("<head>\n");
        bobTheBuilder.append("<meta charset=\"utf-8\">\n");
        bobTheBuilder.append("<title>Proyecto final</title>\n");
        bobTheBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
        bobTheBuilder.append("</head>\n");
        bobTheBuilder.append("<body style=\"font-family: monospace;\">\n");
        return bobTheBuilder.toString();
    }

    /**
     * This function creates the footer of an HTML document,
     * the closing body and html tags
     * @return
     */
    public String getFooter(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("</body>\n");
        bobTheBuilder.append("</html>\n");
        return bobTheBuilder.toString();
    }
    
}