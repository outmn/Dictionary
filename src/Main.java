import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Map<String, HashMap<String, Integer>> dictionary = new TreeMap<String, HashMap<String, Integer>>();

        while(fileChooser.files == null) {
            System.out.print("");
        }

        System.out.println();

        DictionaryBuilder dictionaryBuilder = new DictionaryBuilder("Dictionary", fileChooser.files);
        dictionaryBuilder.createDictionary();


    }
}
