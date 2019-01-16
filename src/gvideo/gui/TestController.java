package gvideo.gui;

import gvideo.model.Video;
import gvideo.platforms.youtube.YoutubeUserVideoSource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private ListView<Video> videoList;

    @FXML
    private BorderPane videoParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            YoutubeUserVideoSource source = new YoutubeUserVideoSource("oplkanal");
            for(Video v : source){
                videoList.getItems().add(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoList.getSelectionModel().selectedItemProperty().addListener(
                (videoObservable, oldValue, newValue) -> {
                    if(newValue != null){
                        videoParent.setCenter(newValue.createNode());
                    }
                }
        );
    }

}
