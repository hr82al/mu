package ru.haval.data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackgroundFileLoader {
    private HashMap<String, Image> images = new HashMap<>();
    private static BackgroundFileLoader instance = null;
    private AtomicBoolean runs = new AtomicBoolean(false);

    private BackgroundFileLoader() {

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
            images.put(path, null);
            loadImages();
            return new ImageView(image);
        } else {
            return new ImageView(images.get(path));
        }
    }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        }
//
//            if (!data.geticon().equals("1")) {
//                if (!priorImages.containsKey(data.getPrior_img())) {
//                    bufferedImage = ImageIO.read(new File(data.getPrior_img()));
//                    priorImages.put(data.getPrior_img(), SwingFXUtils.toFXImage(bufferedImage, null));
//                }
//                Image image = priorImages.get(data.getPrior_img());;
//                iv.setGraphic(new ImageView(image));
//            }
//
//            bufferedImage = new BufferedImage(10, 10, 2);
//            priorImages.put(data.getPrior_img(), SwingFXUtils.toFXImage(bufferedImage, null));
//            Image image = priorImages.get(data.getPrior_img());
//            iv.setGraphic(new ImageView(image));
//            //error
//            System.out.println(e.getMessage().toString() + " prior_controller " + "Ошибка загрузки изображения");
//        }


    public void loadImages() {
        if (images.containsValue(null)) {
            if (runs.get() == false) {
                runs.set(true);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (String path : images.keySet()) {
                            if (images.get(path) == null) {
                                try {
                                    BufferedImage bufferedImage = ImageIO.read(new File(path));
                                    images.put(path, SwingFXUtils.toFXImage(bufferedImage, null));
                                } catch (IOException e) {
                                    //If there is no file set up empty image
                                    BufferedImage bufferedImage = new BufferedImage(10, 10, 2);
                                    images.put(path, SwingFXUtils.toFXImage(bufferedImage, null));
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        runs.set(false);
                    }
                });
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.start();
            }
        }
    }

    public void update() {
        loadImages();
    }
}
