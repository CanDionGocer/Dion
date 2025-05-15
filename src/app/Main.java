package app;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage stage) {
        window = stage;

        Label welcomeLabel = new Label("Welcome to RecyQuest!");
        Button startButton = new Button("Start Game");

        startButton.setOnAction(e -> showGameScene());

        VBox mainLayout = new VBox(20, welcomeLabel, startButton);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 40px;");
        Scene mainMenu = new Scene(mainLayout, 400, 300);

        window.setTitle("RecyQuest - Main Menu");
        window.setScene(mainMenu);
        window.show();
    }


    private void showGameScene() {
    final int[] score = {0};
    Label scoreLabel = new Label("Score: 0");
    scoreLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");

    // Atıklar
    Label banana = new Label("🍌 Banana Peel");
    Label bottle = new Label("🥤 Plastic Bottle");
    Label newspaper = new Label("📰 Newspaper");
    Label jar = new Label("🍯 Glass Jar");

    List<Label> trashItemsList = List.of(banana, bottle, newspaper, jar);
    HBox trashItems = new HBox(20);
    trashItems.setAlignment(Pos.CENTER);
    trashItems.setPadding(new Insets(10));

    for (Label item : trashItemsList) {
        item.setStyle("-fx-font-size: 18px; -fx-background-color: white; -fx-padding: 10px 20px; -fx-border-color: black;");
        trashItems.getChildren().add(item);

        item.setOnDragDetected(e -> {
            Dragboard db = item.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(item.getText());
            db.setContent(content);
            e.consume();
        });
    }

    // Kutular ve eşleşme isimleri
    Map<String, Label> bins = new LinkedHashMap<>();
    bins.put("Plastic", new Label("🗑️ Plastic Bin"));
    bins.put("Organic", new Label("🗑️ Organic Bin"));
    bins.put("Paper", new Label("🗑️ Paper Bin"));
    bins.put("Glass", new Label("🗑️ Glass Bin"));

    HBox binBox = new HBox(20);
    binBox.setAlignment(Pos.CENTER);
    binBox.setPadding(new Insets(20));

    bins.forEach((type, bin) -> {
        // İlk stil tanımı
        String defaultStyle = "-fx-font-size: 16px; -fx-background-color: lightgray; -fx-padding: 20px 30px; -fx-border-color: black;";
        bin.setStyle(defaultStyle);

        bin.setOnDragOver(e -> {
            if (e.getGestureSource() != bin && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        bin.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                String text = db.getString();

                boolean correct = (type.equals("Plastic") && text.contains("Plastic")) ||
                                  (type.equals("Organic") && text.contains("Banana")) ||
                                  (type.equals("Paper") && text.contains("Newspaper")) ||
                                  (type.equals("Glass") && text.contains("Jar"));

                if (correct) {
                    for (Label l : trashItemsList) {
                        if (l.getText().equals(text)) {
                            trashItems.getChildren().remove(l);
                            break;
                        }
                    }

                    score[0]++;
                    scoreLabel.setText("Score: " + score[0]);

                    bin.setStyle("-fx-background-color: lightgreen; -fx-font-size: 16px; -fx-padding: 20px 30px; -fx-border-color: black;");
                    success = true;
                } else {
                    bin.setStyle("-fx-background-color: lightcoral; -fx-font-size: 16px; -fx-padding: 20px 30px; -fx-border-color: black;");
                }

                // 0.5 saniye sonra eski stile geri dön
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(ev -> bin.setStyle(defaultStyle));
                pause.play();
            }

            e.setDropCompleted(success);
            e.consume();
        });

        binBox.getChildren().add(bin);
    });

    Button backButton = new Button("Back to Main Menu");
    backButton.setStyle("-fx-font-size: 14px;");
    backButton.setOnAction(e -> start(window));

    VBox layout = new VBox(20, scoreLabel, trashItems, binBox, backButton);
    layout.setStyle("-fx-alignment: center; -fx-padding: 30px;");
    Scene scene = new Scene(layout, 600, 500);

    window.setScene(scene);
}





    public static void main(String[] args) {
        launch();
    }
    
}
