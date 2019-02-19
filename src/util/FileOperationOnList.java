package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperationOnList {
    List<String> document;
    String filePath;
    String newline = "\n";

    //constructor
    public FileOperationOnList(){
        document = new ArrayList<>();
        filePath = System.getProperty("user.dir") + File.separatorChar + "history.html";
    }

    public FileOperationOnList(List<String> document, final String filePath) {
        this.document = document;
        this.filePath = (System.getProperty("user.dir") + File.separatorChar + filePath);
    }

    //Getter and Setter
    public List<String> getDocument() {
        return document;
    }

    public void setDocument(List<String> document) {
        this.document = document;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //Read File
    public void readFile() throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            document = readLines(file);
        }
    }

    //Write a File
    public void writeFile() throws FileNotFoundException {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            StringBuilder builder = new StringBuilder();
            
            for (String value : document) {
                builder.append(value);
            }
            String text = builder.toString();
            String html =   "<html> " + newline +
                    "<head> " + newline +
                    "<title> " +
                    "</title>" + newline +
                    "</head> " + newline +
                    "<body>" + newline +
                    text +
                    "</body>" + newline +
                    "</html>";
            writer.println(html);
        }
    }

    public List<String> readLines(File file) throws IOException {
        List<String> results;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            results = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                results.add(line);
                line = reader.readLine();
            }
        }
        return results;
    }
}
