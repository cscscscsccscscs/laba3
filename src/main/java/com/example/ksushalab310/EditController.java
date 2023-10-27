package com.example.ksushalab310;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML

    private TextField text_num, text_faculty, text_group, text_course, text_count_stud, text_count_group;

    private Stage dialog;

    private Groups groups;

    @FXML
    void edit(ActionEvent event) {
        if(!text_num.getText().isEmpty() && !text_faculty.getText().isEmpty()  && !text_group.getText().isEmpty()
                && !text_course.getText().isEmpty() && !text_count_stud.getText().isEmpty()
                && !text_count_group.getText().isEmpty()){
            groups.setId(text_num.getText());
            groups.setFaculty(text_faculty.getText());
            groups.setGroup(text_group.getText());
            groups.setCourse(text_course.getText());
            groups.setCount_stud(text_count_stud.getText());
            groups.setCount_group(text_count_group.getText());
            dialog.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialog);
            alert.setTitle("Пустое поле!");
            alert.setHeaderText("Одно или несколько полей пусты");
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

    public void getGroups(Groups groups){
        text_num.setText(groups.getId());
        text_faculty.setText(groups.getId());
        text_group.setText(groups.getFaculty());
        text_course.setText(groups.getCourse());
        text_count_stud.setText(groups.getCount_stud());
        text_count_group.setText(groups.getCount_group());
        this.groups = groups;
    }
}
