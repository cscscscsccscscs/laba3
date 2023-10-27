package com.example.ksushalab310;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField text_num, text_faculty, text_group, text_course, text_count_stud, text_count_group;
    private Stage dialog;
    private ObservableList<Groups> groups = FXCollections.observableArrayList();

    @FXML
    void add(ActionEvent event) {
        if(!text_num.getText().isEmpty() && !text_faculty.getText().isEmpty()  && !text_group.getText().isEmpty()
                && !text_course.getText().isEmpty() && !text_count_stud.getText().isEmpty()
                && !text_count_group.getText().isEmpty()){
            groups.add(new Groups(text_num.getText(), text_faculty.getText(), text_group.getText(),
                    text_course.getText(), text_count_stud.getText(), text_count_group.getText()));
            dialog.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialog);
            alert.setTitle("Пустое поле!");
            alert.setHeaderText("Одно или несколько полей пусты!");
            alert.showAndWait();
        }
    }
    @FXML
    void cancel(ActionEvent event) {
        dialog.close();
    }

    public void getDialog(Stage dialog){
        this.dialog = dialog;
    }

    public void getGroups(ObservableList<Groups> groups) {
        this.groups = groups;
    }
}
