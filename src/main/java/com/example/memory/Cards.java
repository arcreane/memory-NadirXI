package com.example.memory;

import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.ScaleTransition;

public class Cards {
    private final Image frontImage; // the front image of the card
    private final Image backImage; // the back image of the card
    private final ImageView view; // the image view of the card
    private boolean isFlipped;  // true if the card is flipped

    public Cards(Image frontImage, Image backImage) {
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.view = new ImageView(backImage);
        this.isFlipped = false;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    public Image getBackImage() {
        return backImage;
    }

    public ImageView getView() {
        return view;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flip() {
        if (isFlipped) {
            view.setImage(backImage);
            isFlipped = false;
        } else {
            view.setImage(frontImage);
            isFlipped = true;
        }
    }

    public void scale() {
        ScaleTransition scale = new ScaleTransition(Duration.millis(100), view);
        scale.setFromX(1);
        scale.setToX(0);
        scale.setFromY(1);
        scale.setToY(0);
        scale.play();
    }

    public void reset() {
        view.setImage(backImage);
        isFlipped = false;
    }
}
