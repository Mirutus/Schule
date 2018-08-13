package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainViewController {
    @FXML
    Label pqOutput;
    @FXML
    TextField pqInput;
    @FXML
    TextField paInput;
    @FXML
    TextField pbInput;
    @FXML
    TextField pcInput;
    @FXML
    ImageView pImageView;

    public void onInit() {
        TextField[] a = new TextField[]{pbInput, pcInput, paInput};
        for (final TextField field : a) {
            field.textProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            String text = newValue;
                            boolean end = false;
                            int i = field.getSelection().getEnd();
                            if (i == newValue.length() - 1) {
                                end = true;
                            }
                            String o = "";
                            boolean bool = false;
                            for (char c : text.toCharArray()) {
                                switch (c) {
                                    case ',':
                                    case '.':
                                        if(!bool){
                                            bool = true;
                                        }else {
                                            break;
                                        }
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                        o += c;
                                        break;
                                    default:

                                        break;
                                }
                            }
                            field.setText(o);

                            if (end) {
                                field.selectEnd();
                            } else {
                                field.selectRange(i, i);
                            }
                        }
                    });
        }
        try {
            pImageView.setImage(new Image(getClass().getResource("../images/Pythagoras.png").toURI().toString(), pImageView.getFitWidth(), pImageView.getFitHeight(), true, true));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void pCalculate() {
        String sa = paInput.getText().replace(",",".");
        String sb = pbInput.getText().replace(",",".");
        String sc = pcInput.getText().replace(",",".");
        int empty = 0;
        HashMap<String, String> map = new HashMap<>();
        map.put("a", sa);
        map.put("b", sb);
        map.put("c", sc);
        List<String> l = new ArrayList<>();
        String sEmpty = "";
        if(sa.equals("")){
            empty++;
            sEmpty = "a";
        }
        if(sb.equals("")){
            empty++;
            sEmpty = "b";
        }
        if(sc.equals("")){
            empty++;
            sEmpty = "c";
        }
        if(empty != 1){
            JOptionPane.showMessageDialog(null,"Es muss genau ein Feld leer sein!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }else{
            switch (sEmpty){
                case "a":
                    boolean b1 = false;
                    String s2 = "";
                    int i1 = 0;
                    for (char ch : String.valueOf(Math.sqrt(Math.pow(Double.valueOf(sc),2.0)-Math.pow(Double.valueOf(sb),2.0))).toCharArray()) {
                        if (!b1) {
                            if (ch == '.') {
                                b1 = true;
                            }
                            s2 += ch;
                        } else {
                            i1++;
                            if (!(i1 > 4)) {
                                s2 += ch;
                            }
                        }
                    }
                    if(s2.endsWith(".0")){
                        s2.replace(".0","");
                    }
                    paInput.setText(s2);
                    break;
                case "b":
                    boolean b2 = false;
                    String s3 = "";
                    int i2 = 0;
                    for (char ch : String.valueOf(Math.sqrt(Math.pow(Double.valueOf(sc),2.0)-Math.pow(Double.valueOf(sa),2.0))).toCharArray()) {
                        if (!b2) {
                            if (ch == '.') {
                                b2 = true;
                            }
                            s3 += ch;
                        } else {
                            i2++;
                            if (!(i2 > 4)) {
                                s3 += ch;
                            }
                        }
                    }
                    if(s3.endsWith(".0")){
                        s3.replace(".0","");
                    }
                    pbInput.setText(s3);
                    break;
                case "c":
                    boolean b3 = false;
                    String s4 = "";
                    int i3 = 0;
                    for (char ch : String.valueOf(Math.sqrt(Math.pow(Double.valueOf(sa),2.0)+Math.pow(Double.valueOf(sb),2.0))).toCharArray()) {
                        if (!b3) {
                            if (ch == '.') {
                                b3 = true;
                            }
                            s4 += ch;
                        } else {
                            i3++;
                            if (!(i3 > 4)) {
                                s4 += ch;
                            }
                        }
                    }
                    if(s4.endsWith(".0")){
                        s4.replace(".0","");
                    }
                    pcInput.setText(s4);
                    break;
                default:
                    break;
            }
        }
    }


    @FXML
    public void pqCalcualate() {
        String s = pqInput.getText();
        s = s.replace(" ", "");
        if (s.endsWith("=0"))
            s = s.replace("=0", "");
        char[] c = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        String temp = "";
        for (int i = c.length - 1; i >= 0; i--) {
            if (isInt(c[i])) {
                temp = c[i] + temp;
            } else {
                System.out.println(temp);
                if (!temp.equals("")) {
                    if (isInt(temp)) {
                        list.add(Integer.valueOf(temp));
                    } else {
                        pqOutput.setText("Fehlerhafte Eingabe");
                    }
                    temp = "";
                }
            }
        }
        if (!temp.equals("")) {
            if (isInt(temp)) {
                list.add(Integer.valueOf(temp));
            } else {
                pqOutput.setText("Fehlerhafte Eingabe");
            }
            temp = "";
        }
        System.out.println(list.get(1) + " | " + list.get(0));
        String s1 = String.valueOf((-(list.get(1) / 2.0) + Math.sqrt(Math.pow(list.get(1) / 2.0, 2.0) - list.get(0))));
        String s2 = String.valueOf((-(list.get(1) / 2.0) - Math.sqrt(Math.pow(list.get(1) / 2.0, 2.0) - list.get(0))));
        String s3 = "";
        String s4 = "";
        boolean b1 = false;
        int i1 = 0;
        boolean b2 = false;
        int i2 = 0;
        for (char ch : s1.toCharArray()) {
            if (!b1) {
                if (ch == '.') {
                    b1 = true;
                }
                s3 += ch;
            } else {
                i1++;
                if (!(i1 > 4)) {
                    s3 += ch;
                }
            }
        }
        if(s3.endsWith(".0")){
            s3.replace(".0","");
        }
        for (char ch : s2.toCharArray()) {
            if (!b2) {
                if (ch == '.') {
                    b2 = true;
                }
                s4 += ch;
            } else {
                i2++;
                if (!(i2 > 4)) {
                    s4 += ch;
                }
            }
        }
        if(s4.endsWith(".0")){
            s4.replace(".0","");
        }
        pqOutput.setText(s3 + " | " + s4);
    }

    public boolean isInt(String s) {
        boolean bool = false;
        for (char c : s.toCharArray()) {
            System.out.println(c);
            switch (c) {
                case '+':
                case '-':
                    if (bool) {
                        System.out.println("a");
                        return false;
                    }
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    System.out.println("b");
                    bool = true;
                    break;

                default:
                    return false;
            }
        }
        return true;

    }

    public boolean isInt(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
            case '+':
            case '-':
                return true;

            default:
                return false;
        }

    }
}
