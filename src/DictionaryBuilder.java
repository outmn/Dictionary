import java.io.*;
import java.text.DateFormat;
import java.util.*;

public class DictionaryBuilder {
    private String dictionaryName;
    private File[] files;
    private Map<String, HashMap<String, Integer>> dictionary = new TreeMap<String, HashMap<String, Integer>>();

    public DictionaryBuilder(String dictionaryName, File[] files) throws IOException {
        this.dictionaryName = dictionaryName;
        this.files = files;
        createDictionary();
    }

    public void createDictionary() throws IOException  {
        for (int i = 0; i < files.length; i++) {
            readWords(files[i]);
        }
        writeToFile();
    }

    private void readWords(File file) throws IOException {
        BufferedReader br;
        StringTokenizer tz;

            List<String> words = new ArrayList<String>();
            String wordLine = "";
            String[] word;

            Scanner scanner = new Scanner(new FileReader(file));

            while (scanner.hasNext())
                wordLine += scanner.nextLine().toLowerCase();
            scanner.close();

            word = wordLine.split("[\"?!*,-.\\;/:..._)(+\\s]+");

            for (int j = 0; j < word.length; j++) {
                words.add(word[j]);
            }

            StopWords stopWords = new StopWords();
            words = StopWords.removeStopWords(words);

//            for (int j = 0; j < words.size(); j++) {
//                //System.out.print(words.get(j) + "/");
//            }
        coutntWords(words, file.getName());

    }

    private void coutntWords(List<String> words, String documentsName) {

        for (int i = 0; i < words.size(); i++) {
            HashMap<String, Integer> hashMap = new HashMap<String,Integer>();

            if (dictionary.containsKey(words.get(i))) {
                hashMap = dictionary.get(words.get(i));

                int countWords = 1;

                if (hashMap.containsKey(documentsName)) {
                    countWords += hashMap.get(documentsName);
                    hashMap.remove(documentsName);
                    hashMap.put(documentsName, countWords);
                } else {
                    hashMap.put(documentsName, countWords);
                }
                //System.out.println(dictionary.get(words.get(i)));
            } else {
                hashMap.put(documentsName, 1);
                dictionary.put(words.get(i), hashMap);
            }
        }
    }

    private void writeToFile() {

        try {
            Date date = new Date();
            String fileName = dictionaryName + " "  + date.toString() + ".txt";
            File dictionaryFile = new File(fileName);
            dictionaryFile.createNewFile();

            String string = "";

            PrintWriter printWriter = new PrintWriter(dictionaryFile.getAbsoluteFile());
            printWriter.print(dictionaryName);
            for (Map.Entry entry : dictionary.entrySet()) {
                string = entry.getKey() + " - " + entry.getValue() + "\n";
                string.split("[\\{\\}]+");
                printWriter.println(string);
            }


        } catch ( IOException ioe ) { ioe.printStackTrace(); }


    }
}