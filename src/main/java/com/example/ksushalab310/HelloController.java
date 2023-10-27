package com.example.ksushalab310;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Groups, String> column_count_group, column_count_stud,
            column_course, column_group, column_num, column_faculty;

    @FXML
    private Label l_count_group, l_count_stud, l_course, l_faculty, l_group,l_num;

    @FXML
    private TableView<Groups> tableview;

    ObservableList<Groups> groups = FXCollections.observableArrayList(
            new Groups("1", "КТиИБ", "ИСТ-331", "3", "17",
                    "2"),
            new Groups("1", "КТиИБ", "ИСТ-332", "3", "20",
                    "2"),
            new Groups("1", "КТиИБ", "ПИ-332", "3", "19",
                    "2"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column_num.setCellValueFactory(new PropertyValueFactory<Groups, String>("id"));
        column_faculty.setCellValueFactory(new PropertyValueFactory<Groups, String>("faculty"));
        column_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("group"));
        column_course.setCellValueFactory(new PropertyValueFactory<Groups, String>("course"));
        column_count_stud.setCellValueFactory(new PropertyValueFactory<Groups, String>("count_stud"));
        column_count_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("count_group"));

        tableview.setItems(groups);
    }
}
