import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class Controller {
    static Alphabet alphabet = new Alphabet();
    private StringBuilder cryptoText;
    @FXML
    private TextField textKey;
    @FXML
    private Text textNotification;
    @FXML
    private TextArea textInfo;
    @FXML
    private TextField textPath;

    private boolean connectFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(textPath.getText()))){
            cryptoText = new StringBuilder();
            while (reader.ready())
                cryptoText.append(reader.readLine()).append("\r\n");
            return true;
        } catch (FileNotFoundException e) {
            setNotification("File not found, try again", "RED");
            return false;
        } catch (IOException e) {
            setNotification("Error", "RED");
            return false;
        }
    }

    public void bruteForce() {
        if (connectFile())
            fileWrite(-26,25);
    }

    public void encrypt() {
        if (getKey() != 0 & connectFile())
            fileWrite(getKey(), 0);
    }

    public void decrypt() {
        if (getKey() != 0 & connectFile())
            fileWrite(-getKey(), 0);
    }

    public String directorySave(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose directory");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        return selectedFile.getPath();
    }

    public void setNotification(String notification, String color){
        textNotification.setText(notification);
        textNotification.setFill(Paint.valueOf(color));
    }

    public int getKey(){
        try {
            int num = Math.abs(Integer.parseInt(textKey.getText())) % 26;
            if (num != 0)
                return num;
            else {
                setNotification("Invalid value", "RED");
                return 0;
            }
        }catch (NumberFormatException e){
            setNotification("Invalid value", "RED");
            return 0;
        }
    }

    public void fileWrite(int key, int mode){
        String directory = directorySave();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(directory))){
            StringBuilder crypto = new StringBuilder();
            for (int k = mode; k > -1; k--)
                for (int i = 0; i < cryptoText.length(); i++)
                    crypto.append(alphabet.symbolShift(cryptoText.charAt(i), key + k));

            writer.write(crypto + "\r\n");
            setNotification("File saved: " + directory, "GREEN");
        }catch (IOException e){
            setNotification("Failed to create file", "RED");
        }
    }

    public void showInfo() {
        textInfo.setVisible(!textInfo.isVisible());
    }
}