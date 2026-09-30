package com.example.listak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try(BufferedReader br = new BufferedReader(new FileReader("listak.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                String[] prcLine = line.split(";");
                if(Objects.equals(prcLine[0], "1")) {
                    leftRkt.add(prcLine[1]);
                } else if (Objects.equals(prcLine[0], "2")) {
                    rightRkt.add(prcLine[1]);
                }

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String everything = sb.toString();

            leftView.setItems(leftRkt);
            rightView.setItems(rightRkt);
        } catch (IOException e) {
            //Throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }

        runUpdate();

    }

    @FXML
    public int kitties = 0, mushies = 0, birdies = 0;

    @FXML
    public Label kittyCount = new Label(), birdCount = new Label(), mushCount = new Label(), stateCount = new Label();

    @FXML
    ObservableList<String> leftRkt = FXCollections.observableArrayList(), rightRkt = FXCollections.observableArrayList();

    @FXML
    public ListView<String> leftView = new ListView<>(), rightView = new ListView<>();

    public void runUpdate() {
        kittyCount.setText(String.valueOf(kitties));
        birdCount.setText(String.valueOf(birdies));
        mushCount.setText(String.valueOf(mushies));
        stateCount.setText(leftRkt.size() + "/" + rightRkt.size() + " elem van a listákban.");
        leftView.setItems(leftRkt);
        rightView.setItems(rightRkt);

    }

    public void addKitty() {
        kitties++;
        leftRkt.add("Kitty");
        kittyCount.setText(String.valueOf(kitties));
        runUpdate();
    }

    public void addMushies() {
        mushies++;
        leftRkt.add("Gomba");
        mushCount.setText(String.valueOf(mushies));
        runUpdate();
    }

    public void addBirdies() {
        birdies++;
        leftRkt.add("Madár");
        birdCount.setText(String.valueOf(birdies));
        runUpdate();
    }

    public void delLeft() {
        leftRkt.clear();
        kitties = 0;
        mushies = 0;
        birdies = 0;
        runUpdate();
    }

    public void delRight() {
        rightRkt.clear();
        runUpdate();
    }

    public void moveToRight() {

        int idx = ControllerTest.isRunningTest ? 0 : leftView.getSelectionModel().getSelectedIndex();
        if (idx < 0) return;

        if(Objects.equals(leftView.getSelectionModel().getSelectedItem(), "Madár")) {
            birdies--;
        } else if(Objects.equals(leftView.getSelectionModel().getSelectedItem(), "Gomba")) {
            mushies--;
        } else if(Objects.equals(rightView.getSelectionModel().getSelectedItem(), "Kitty")) {
            kitties--;
        }

        rightRkt.add(leftRkt.get(idx));
        leftRkt.remove(idx);

        runUpdate();
    }

    public void remRight() {
        int idx = ControllerTest.isRunningTest ? 0 : leftView.getSelectionModel().getSelectedIndex();
        if (idx < 0) return;

        rightRkt.remove(idx);
        runUpdate();
    }

    public void saveList() {
        try (FileWriter myWriter = new FileWriter("listak.txt", false)) {

            for(String s : leftRkt) {
                myWriter.write("1;" + s + "\n");
            }
            myWriter.write("\n");
            for(String z : rightRkt) {
                myWriter.write("2;" + z + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
