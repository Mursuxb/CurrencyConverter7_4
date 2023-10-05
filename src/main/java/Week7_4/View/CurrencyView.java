package Week7_4.View;

import Week7_4.Controller.ConverterController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CurrencyView extends Application {
    private TextField conversion = new TextField("Currency conversion");
    private TextField amount = new TextField();

    private ConverterController controller = new ConverterController();

    public void start(Stage stage) {
        controller.createCurrencies2();
        controller.getCurrencies();
        amount.setPromptText("Amount to convert");
        Insets insets = new Insets(5, 5, 5, 5);
        GridPane layout = new GridPane();
        Button convertButton = new Button("Convert");
        layout.setMargin(convertButton, insets);
        layout.add(convertButton, 0, 2);
        Button addButton = new Button("Add currency");
        layout.setMargin(addButton, insets);
        layout.add(addButton, 1, 2);


        stage.setTitle("Currency Converter");
        stage.setMinWidth(1100);
        stage.setMinHeight(200);


        // reserve space for result
        amount.setMinWidth(200);
        layout.add(amount, 0, 1);
        Label amountLabel = new Label("Amount");
        amountLabel.setMinWidth(200);
        layout.setMargin(amountLabel, insets);
        layout.add(amountLabel, 0, 0);
        conversion.setMinWidth(200);
        layout.setMargin(conversion, insets);
        layout.add(conversion, 2, 1);
        Label conversionLabel = new Label("Conversion");
        conversionLabel.setMinWidth(200);
        layout.setMargin(conversionLabel, insets);
        layout.add(conversionLabel, 2, 0);

        Label currencyLabel = new Label("From");
        layout.setMargin(currencyLabel, insets);
        layout.add(currencyLabel, 1, 0);
        ChoiceBox<String> sourceCurrency = new ChoiceBox<>();
        sourceCurrency.getItems().addAll(controller.getAbbreviations());
        layout.setMargin(sourceCurrency, insets);
        layout.add(sourceCurrency, 1, 1);


        Label targetLabel = new Label("To");
        layout.setMargin(targetLabel, insets);
        layout.add(targetLabel, 3, 0);
        ChoiceBox<String> targetCurrency = new ChoiceBox<>();
        targetCurrency.getItems().addAll(controller.getAbbreviations());
        layout.setMargin(targetCurrency, insets);
        layout.add(targetCurrency, 3, 1);
        Label directions = new Label("Please enter the amount you want to convert and select the currencies");
        Label directions2 = new Label("you want to convert from and to. Then press the convert button.");
        Label gap = new Label(" ");
        layout.add(gap, 0, 3);
        layout.add(directions, 0, 4);
        layout.add(directions2, 0, 5);


        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();

        Stage newStage = new Stage();
        GridPane newLayout = new GridPane();
        newStage.setTitle("Add currency");
        newStage.setMinWidth(300);
        TextField abbreviation = new TextField();
        abbreviation.setPromptText("Insert Abbreviation");
        TextField name = new TextField();
        name.setPromptText("Insert Name");
        TextField rate = new TextField();
        rate.setPromptText("Insert Rate");
        Button add = new Button("Add");
        newLayout.add(abbreviation, 0, 0);
        newLayout.add(name, 0, 1);
        newLayout.add(rate, 0, 2);
        newLayout.add(add, 0, 3);
        newStage.setScene(new Scene(newLayout));

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    conversion.setText(controller.convert(Double.parseDouble(amount.getText()), sourceCurrency.getValue(), targetCurrency.getValue()));
                    controller.createTransaction(Double.parseDouble(amount.getText()), sourceCurrency.getValue(), targetCurrency.getValue());
                } catch (NumberFormatException e) {
                    conversion.setText("Please enter a number");

                }
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                newStage.showAndWait();
                sourceCurrency.getItems().clear();
                sourceCurrency.getItems().addAll(controller.getAbbreviations());
                targetCurrency.getItems().clear();
                targetCurrency.getItems().addAll(controller.getAbbreviations());
                controller.getCurrencies();
            }
        });

        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (abbreviation.getLength() > 3) {
                    abbreviation.setText("Abbreviation must be 3 characters or less");
                } else if (abbreviation.getLength() < 1) {
                    abbreviation.setText("Please enter an abbreviation");
                } else if (name.getLength() < 1) {
                    name.setText("Please enter a name");
                } else if (rate.getLength() < 1) {
                    rate.setText("Please enter a rate");
                } else {
                    try {
                        controller.createCurrency(abbreviation.getText(), name.getText(), Double.parseDouble(rate.getText()));
                        newStage.close();
                    } catch (Exception e) {
                        System.out.println("Please Check if your input contains a . and not a ,");
                    }
                }
            }
        });


        stage.show();
    }
}
