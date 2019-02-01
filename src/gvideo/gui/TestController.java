package gvideo.gui;

import gvideo.model.*;
import gvideo.platforms.youtube.YoutubeChannelVideoSource;
import gvideo.platforms.youtube.YoutubeChannelVideoSourceGenerator;
import gvideo.platforms.youtube.YoutubeUserVideoSource;
import gvideo.platforms.youtube.YoutubeUserVideoSourceGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private ListView<Video> videoList;

    @FXML
    private ListView<StoredVideoSource> sourceList;

    @FXML
    private BorderPane videoParent;

    private Storage storage;

    private int videoSourceCounter = 0;

    private static final VideoSourceGenerator[] generators = {
            new YoutubeChannelVideoSourceGenerator(),
            new YoutubeUserVideoSourceGenerator()
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storage = new Storage();
        //TODO daten laden

        sourceList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    videoList.getItems().clear();
                    if(newValue != null){
                        for(Video v : newValue){
                            videoList.getItems().add(v);
                        }
                    }
                }
        );

        videoList.getSelectionModel().selectedItemProperty().addListener(
                (videoObservable, oldValue, newValue) -> {
                    if(newValue != null){
                        videoParent.setCenter(newValue.createNode());
                    }
                }
        );
    }

    public void addSource(ActionEvent event){
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setContentText("Gib eine URL ein, wo die gewünschten Videos zu finden sind");
        Optional<String> optional = inputDialog.showAndWait();
        if(optional.isPresent()){
            for(VideoSourceGenerator g : generators){
                if(g.isMatch(optional.get())){
                    VideoSource source = g.generateIfMatch(optional.get());
                    if(source != null){
                        StoredVideoSource stored = new StoredVideoSource(source, optional.get());
                        sourceList.getItems().add(stored);
                        addToStorage(stored);
                        return;
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Video source could not be added.");
            alert.show();
        }
    }

    private void addToStorage(StoredVideoSource stored) {
        storage.storeString("videoSource" + videoSourceCounter, stored.getUrl());
        videoSourceCounter++;
        try {
            storage.store(new FileOutputStream("./storage.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO benachrichtigung an benutzer
        }
    }

}
