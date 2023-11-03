package com.example.ksushalab310;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Groups, String> column_count_group, column_count_stud,
            column_course, column_group, column_num, column_faculty;

    @FXML
    private Label l_count_group, l_count_stud, l_course, l_faculty, l_group,l_num;

    @FXML
    private TableView<Groups> tableview;

    private Stage stage;

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

    public void getStage(Stage stage)
    {this.stage = stage;}

    public File getTeachersFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(HelloApplication.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(HelloApplication.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Обновление заглавия сцены.
            stage.setTitle("Groups - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Обновление заглавия сцены.
            stage.setTitle("Groups");
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {

            JAXBContext context = JAXBContext
                    .newInstance(ListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            // Чтение XML из файла и демаршализация.
            ListWrapper wrapper = (ListWrapper) um.unmarshal(file);

            groups = FXCollections.observableList(wrapper.getGroups());
            tableview.setItems(groups);

            // Сохраняем путь к файлу в реестре.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не получилось открыть файл");
            alert.setContentText("Не получилось загрузить данные из файла:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Обёртываем наши данные об преподавателях.
            ListWrapper wrapper = new ListWrapper();
            wrapper.setGroups(groups);

            // Маршаллируем и сохраняем XML в файл.
            m.marshal(wrapper, file);

            // Сохраняем путь к файлу в реестре.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не получилось открыть файл");
            alert.setContentText("Не получилось загрузить данные из файла:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    @FXML
    private void new_file(ActionEvent event) {
        groups.clear();
        tableview.setItems(groups);
        setPersonFilePath(null);
    }

    @FXML
    private void open_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    /**
     * Сохраняет файл в файл адресатов, который в настоящее время открыт.
     * Если файл не открыт, то отображается диалог "save as".
     */
    @FXML
    private void save_file(ActionEvent event) {
        File personFile = getTeachersFilePath();
        if (personFile != null) {
            savePersonDataToFile(personFile);
        } else {
            save_as_file(event);
        }
    }

    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать файл, куда будут сохранены данные
     */
    @FXML
    private void save_as_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            savePersonDataToFile(file);
        }
    }

    /**
     * Открывает диалоговое окно about.
     */
    @FXML
    private void help_about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Groups");
        alert.setHeaderText("О нас");
        alert.setContentText("Автор: Криводанова Ксения \nГруппа: ИСТ-331");

        alert.showAndWait();
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
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
