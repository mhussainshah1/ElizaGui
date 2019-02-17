package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DatabaseWord {
    private String word;
    private List<String> words;

    DatabaseWord() {
        String filename = (System.getProperty("user.dir") + File.separatorChar + "google-10000-english.txt");
        File file = new File(filename);
        if (file.exists()) {
            try {
                words = readLines(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> readLines(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> results = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            if (line.length() > 6) {
                results.add(line);
            }
            line = reader.readLine();
        }
        reader.close();
        return results;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        words.add(word);
        this.word = word;
    }

    public String getWordAt(int index) {
        word = words.get(index);
        return word;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
