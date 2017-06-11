package c15;

/**
 * Created by Eugene on 6/11/2017.
 */
public class FileReaderExample {
    public static void main(String[] args) {
        FileReader f = new FileReader();
        System.out.println(f.read("D:\\words.txt"));
    }
}
