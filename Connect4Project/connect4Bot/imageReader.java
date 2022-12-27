import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class imageReader {

    public static void main(String[] args) throws Exception {
        File inFile = new File("imUpdate.txt"); // Set input file
        Scanner freader = new Scanner(inFile); // Decleare scanner
        FileWriter writer = new FileWriter("imUpdate.txt");
        writer.write("0");
        writer.close();
          while (true) {
            freader = new Scanner(inFile); // Decleare scanner
            try {
            if(freader.nextLine().equals("1")){
                processing.load();
                writer.write("0");
            } else {
                writer.write("0");
            }
        } catch (Exception e){
            continue;
         }
            freader.close();
            writer.close();
        }
        
        
    }
}
