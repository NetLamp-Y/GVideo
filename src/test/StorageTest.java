package test;

import gvideo.model.Storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageTest {

    public static void main(String[] args) throws IOException {
        Storage test = new Storage();
        System.out.println(test.load(new FileInputStream("./storage.txt")));
        System.out.println(test);
    }

}
