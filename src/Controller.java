import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class Controller {
    private StringBuilder cryptoText = new StringBuilder();
    private StringBuilder textForAnalysis = new StringBuilder();
    @FXML
    private TextField textKey;
    @FXML
    private TextField textPathAnalysis;
    @FXML
    private Text textNotification;
    @FXML
    private TextArea textInfo;
    @FXML
    private TextField textPath;

    private boolean connectFile(TextField textField, StringBuilder text){
        text.setLength(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(textField.getText()))){
            while (reader.ready())
                text.append(reader.readLine()).append("\r\n");
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
        if (connectFile(textPath, cryptoText))
            fileWrite(BruteForce.getKey(String.valueOf(cryptoText)));
    }

    public void encrypt() {
        if (getKey() != 0 & connectFile(textPath, cryptoText))
            fileWrite(getKey());
    }

    public void decrypt() {
        if (getKey() != 0 & connectFile(textPath, cryptoText))
            fileWrite(-getKey());
    }

    public void staticalAnalysis() {
        if (connectFile(textPathAnalysis, textForAnalysis) & connectFile(textPath, cryptoText))
            fileWrite(StaticalAnalysis.getKey(String.valueOf(textForAnalysis)));
    }

    private String directorySave(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose directory for save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        return selectedFile.getPath();
    }

    private void setNotification(String notification, String color){
        textNotification.setText(notification);
        textNotification.setFill(Paint.valueOf(color));
    }

    private int getKey(){
        try {
            int num = Math.abs(Integer.parseInt(textKey.getText())) % 33;
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

    private void fileWrite(int key){
        String directory = directorySave();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(directory))){
            StringBuilder crypto = new StringBuilder();

            for (int i = 0; i < cryptoText.length(); i++)
                crypto.append(Alphabet.symbolShift(cryptoText.charAt(i), key));
            writer.write(String.valueOf(crypto));
            setNotification("File saved! " + "key = " + key, "GREEN");
        } catch (IOException e) {
            setNotification("Failed to create file, try change directory", "RED");
        }
    }

    public void showInfo() {
        textInfo.setVisible(!textInfo.isVisible());
    }


}
