package ru.haval.data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackgroundFileLoader {
    private HashMap<String, Image> images = new HashMap<>();
    private static BackgroundFileLoader instance = null;
    private AtomicBoolean runs = new AtomicBoolean(false);
    private BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    private BackgroundFileLoader() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String path = queue.take();
                    BufferedImage bufferedImage = ImageIO.read(new File(path));
                    synchronized (BackgroundFileLoader.class) {
                        System.out.println("loaded a new path: " + path);
                        images.put(path, SwingFXUtils.toFXImage(bufferedImage, null));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setDaemon(true);
        thread.start();
    }

    public static BackgroundFileLoader getInstance() {
        if (instance == null) {
            instance = new BackgroundFileLoader();
        }
        return instance;
    }

    public ImageView getImage(String path) {
//        try {
        if (!images.containsKey(path)) {
            BufferedImage bufferedImage = new BufferedImage(10, 10, 2);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            try {
                queue.put(path);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (BackgroundFileLoader.class) {
                images.put(path, null);
            }
            return new ImageView(image);
        } else {
            return new ImageView(images.get(path));
        }
    }
}
