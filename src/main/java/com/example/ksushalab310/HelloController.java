package com.example.ksushalab310;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
        dialog.setScene(new Scene(loader.load(),600, 400));
        AddController controller = loader.getController();
        controller.getDialog(dialog);
        controller.getGroups(groups);
        dialog.showAndWait();
        tableview.setItems(FXCollections.observableList(groups));
    }

    @FXML
    void delete(ActionEvent event) {
        if (tableview.getSelectionModel().getSelectedItem()!=null) {
            groups.remove(tableview.getSelectionModel().getSelectedItem());
            tableview.setItems(FXCollections.observableList(groups));
            l_num.setText("Запись №");
            l_faculty.setText("Факультет: ");
            l_group.setText("Группа: ");
            l_course.setText("Курс: ");
            l_count_stud.setText("Кол-во студентов: ");
            l_count_group.setText("Кол-во подгрупп: ");
        }
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem()!=null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("edit-view.fxml"));
            stage.setScene(new Scene(loader.load(),600, 400));
            EditController controller = loader.getController();
            controller.getDialog(stage);
            int id = groups.indexOf(tableview.getSelectionModel().getSelectedItem());
            controller.getGroups(groups.get(id));
            stage.showAndWait();
            tableview.setItems(groups);
            tableview.getSelectionModel().clearSelection();
            tableview.getSelectionModel().select(id);
            tableview.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column_num.setCellValueFactory(new PropertyValueFactory<Groups, String>("id"));
        column_faculty.setCellValueFactory(new PropertyValueFactory<Groups, String>("faculty"));
        column_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("group"));
        column_course.setCellValueFactory(new PropertyValueFactory<Groups, String>("course"));
        column_count_stud.setCellValueFactory(new PropertyValueFactory<Groups, String>("count_stud"));
        column_count_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("count_group"));

        tableview.setItems(groups);
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Groups groups = tableview.getSelectionModel().getSelectedItem();
                l_num.setText("Запись №"+groups.getId());
                l_faculty.setText("Факультет: "+groups.getFaculty());
                l_group.setText("Группа: "+groups.getGroup());
                l_course.setText("Курс: "+groups.getCourse());
                l_count_stud.setText("Кол-во студентов: "+groups.getCount_stud());
                l_count_group.setText("Кол-во подгрупп: "+groups.getCount_group());
            }
        });
    }
}
