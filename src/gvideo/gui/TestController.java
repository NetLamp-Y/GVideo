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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.io.FileInputStream;
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

    @FXML
    private SplitPane splitPane;

    private Storage storage;

    private int videoSourceCounter = 0;

    private final String FILE_NAME = "./storage.txt";
    private final String VIDEO_SOURCE_KEY = "videoSource";

    private static final VideoSourceGenerator[] generators = {
            new YoutubeChannelVideoSourceGenerator(),
            new YoutubeUserVideoSourceGenerator()
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storage = new Storage();
        loadData();
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

    private void loadData() {
        try {
            storage.load(new FileInputStream(FILE_NAME));
            for(;storage.getString(VIDEO_SOURCE_KEY + videoSourceCounter) != null;videoSourceCounter++){
                String url = storage.getString(VIDEO_SOURCE_KEY + videoSourceCounter);
                addSourceByUrl(url, false);
            }
        } catch (FileNotFoundException e) {

        }
    }

    private boolean addSourceByUrl(String url, boolean addToStorage){
        for(VideoSourceGenerator g : generators){
            if(g.isMatch(url)){
                VideoSource source = g.generateIfMatch(url);
                if(source != null){
                    StoredVideoSource stored = new StoredVideoSource(source, url);
                    sourceList.getItems().add(stored);
                    if(addToStorage){
                        addToStorage(stored);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void addSource(ActionEvent event){
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setContentText("Gib eine URL ein, wo die gewünschten Videos zu finden sind");
        Optional<String> optional = inputDialog.showAndWait();
        if(optional.isPresent()){
            if(!addSourceByUrl(optional.get(), true)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Video source could not be added.");
                alert.show();
            }
        }
    }

    private void addToStorage(StoredVideoSource stored) {
        storage.storeString(VIDEO_SOURCE_KEY + videoSourceCounter, stored.getUrl());
        videoSourceCounter++;
        try {
            storage.store(new FileOutputStream(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO benachrichtigung an benutzer
        }
    }

    public void showOrHideLists(){
        if(splitPane.getItems().contains(videoList)){
            splitPane.getItems().remove(videoList);
        }else{
            splitPane.getItems().add(0, videoList);
        }

        if(splitPane.getItems().contains(sourceList)){
            splitPane.getItems().remove(sourceList);
        }else{
            splitPane.getItems().add(0, sourceList);
        }
    }

}
