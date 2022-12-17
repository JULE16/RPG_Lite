package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;  

public class Game extends Application implements EventHandler<ActionEvent>{  
  
    static Stage window;

    static Button startButton;
    static Button attackButton;
    static Button deffendButton;
    static Button itemButton;

    static Label selectionTitleLabel;
    static Label selectionLabel;
    static TextField selectionTextField;
    static Button selectionValidation;
    static Button selectionConfirmButton;

    static Label fightStatusLabel;
    static ImageView battleLogo;
    static Label heroesMenu1Label;
    static Label heroesMenu2Label;
    static Button warriorButton;
    static Button hunterButton;
    static Button mageButton;
    static Button healerButton;

    static Scene menu;
    static Scene selection;
    static Scene fight1;

    static StackPane menuLayout;
    static BorderPane selectionLayout;
    static HBox selectionTitle;
    static HBox selectionConfirm;
    static VBox selectionBox;
    static VBox heroesMenu1;
    static VBox heroesMenu2;
    
    static BorderPane fight1Layout;
    static HBox fightStatus;
    static VBox commandsBox;
    static HBox inventoryBox;
    static HBox actionBar;
    
    @Override  
    public void start(Stage primaryStage) throws Exception {    
        window = primaryStage;
        window.setTitle("RPG Lite");
        
        //menu stuff
        startButton = new Button("Start");
        startButton.setPrefSize(200, 50);
        startButton.setFont(new Font(20));
        startButton.setOnAction(e -> window.setScene(selection));

        //selection stuff
        selectionTitleLabel = new Label("Team selection");
        selectionTitleLabel.setFont(new Font(40));
        selectionTitleLabel.setTextFill(Color.WHITE);
        selectionLabel = new Label("Choose the number of heroes in your team");
        selectionLabel.setFont(new Font(20));
        selectionLabel.setTextFill(Color.WHITE);
        selectionTextField = new TextField();
        selectionTextField.setMaxWidth(80);
        selectionValidation = new Button("Confirm");
        selectionValidation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String input = selectionTextField.getText();
                try {
                    if(Integer.parseInt(input) > 0) {
                        length = Integer.parseInt(input);
                        selectionLayout.setCenter(null);
                        selectionLayout.setLeft(heroesMenu1);
                        selectionLayout.setRight(heroesMenu2);
                        selectionLayout.setBottom(selectionConfirm);
                    }
                    else {
                        System.out.println("An integer superior to 0 !");
                    }
                }catch(NumberFormatException e) {
                    System.out.println("Enter an integer superior to 0");
                }
            }
        });
        
        selectionConfirmButton = new Button("confirm");
        selectionConfirmButton.setPrefSize(200, 50);
        selectionConfirmButton.setOnAction(this);
        
        heroesMenu1Label = new Label("Your team :");
        heroesMenu1Label.setFont(new Font(30));
        heroesMenu1Label.setTextFill(Color.WHITE);
        heroesMenu2Label = new Label("Choose :");
        heroesMenu2Label.setFont(new Font(30));
        heroesMenu2Label.setTextFill(Color.WHITE);
        
        warriorButton = new Button("Warrior");
        warriorButton.setOnAction(this);
        warriorButton.setPrefSize(300, 75);
        warriorButton.setFont(new Font(15));
        hunterButton = new Button("Hunter");
        hunterButton.setOnAction(this);
        hunterButton.setPrefSize(300, 75);
        hunterButton.setFont(new Font(15));
        mageButton = new Button("Mage");
        mageButton.setOnAction(this);
        mageButton.setPrefSize(300, 75);
        mageButton.setFont(new Font(15));
        healerButton = new Button("Healer");
        healerButton.setOnAction(this);
        healerButton.setPrefSize(300, 75);
        healerButton.setFont(new Font(15));
        
        //fights stuff
        fightStatusLabel = new Label();
        fightStatusLabel.setFont(new Font(20));
        fightStatusLabel.setTextFill(Color.WHITE);
        
        attackButton = new Button("Attack");
        attackButton.setPrefSize(300, 50);
        
        deffendButton = new Button("Deffend");
        deffendButton.setPrefSize(300, 50);
        
        itemButton = new Button("Use Item");
        itemButton.setPrefSize(300,50);

        //menu
        menuLayout = new StackPane();
        menuLayout.getChildren().add(startButton);
        menuLayout.setId("menu");

        //selection
        selectionTitle = new HBox();
        selectionTitle.getChildren().add(selectionTitleLabel);
        selectionTitle.setAlignment(Pos.CENTER);
        selectionTitle.setPadding(new Insets(30,0,0,0));
        
        selectionBox = new VBox();
        selectionBox.getChildren().addAll(selectionLabel,selectionTextField,selectionValidation);
        selectionBox.setAlignment(Pos.CENTER);
        selectionBox.setSpacing(10);
        
        selectionLayout = new BorderPane();
        selectionLayout.setTop(selectionTitle);
        selectionLayout.setCenter(selectionBox);
        selectionLayout.setId("selection");

        selectionConfirm = new HBox();
        selectionConfirm.getChildren().add(selectionConfirmButton);
        selectionConfirm.setAlignment(Pos.CENTER);
        selectionConfirm.setPadding(new Insets(0,0,30,0));
        
        heroesMenu1 = new VBox();
        heroesMenu1.getChildren().add(heroesMenu1Label);
        heroesMenu1.setAlignment(Pos.CENTER);
        heroesMenu1.setSpacing(10);
        heroesMenu1.setPadding(new Insets(0,0,0,100));

        heroesMenu2 = new VBox();
        heroesMenu2.getChildren().addAll(heroesMenu2Label, warriorButton, hunterButton, mageButton, healerButton);
        heroesMenu2.setAlignment(Pos.CENTER);
        heroesMenu2.setSpacing(10);
        heroesMenu2.setPadding(new Insets(0,100,0,0));
        
        //fight1
        fightStatus = new HBox();
        fightStatus.getChildren().add(fightStatusLabel);
        fightStatus.setPadding(new Insets(10,0,0,0));
        fightStatus.setAlignment(Pos.CENTER);
        
        inventoryBox = new HBox();

        actionBar = new HBox();
        actionBar.setSpacing(20);
        actionBar.setPadding(new Insets(0,0,10,0));
        actionBar.getChildren().addAll(attackButton,deffendButton, itemButton);
        actionBar.setAlignment(Pos.BOTTOM_CENTER);

        commandsBox = new VBox();
        commandsBox.setSpacing(20);
        commandsBox.getChildren().addAll(inventoryBox, actionBar);
        commandsBox.setAlignment(Pos.BOTTOM_CENTER);
        
        fight1Layout = new BorderPane();
        fight1Layout.setTop(fightStatus);
        fight1Layout.setBottom(commandsBox);
        fight1Layout.setId("fight1");
        
        //scenes
        menu = new Scene(menuLayout, 1280, 660);
        menu.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        selection = new Scene(selectionLayout, 1280, 660);
        selection.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        fight1 = new Scene(fight1Layout, 1280, 660);
        fight1.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        
        primaryStage.setScene(menu);
        primaryStage.show();

        
    }
    
    int counter = 0;
    @Override
    public void handle(ActionEvent event) {
        Button eventButton = (Button)event.getSource();
        String str = eventButton.getText();
        if(window.getScene() == selection ) {
            if(eventButton.getParent() == heroesMenu2) {
                if(counter < length) {
                    switch(str) {
                        case "Warrior" :
                            Button button1 = new Button(str);
                            button1.setPrefSize(300, 75);
                            button1.setOnAction(this);
                            button1.setFont(new Font(15));
                            heroesMenu1.getChildren().add(button1);
                            break;
                        case "Hunter" :
                            Button button2 = new Button(str);
                            button2.setPrefSize(300, 75);
                            button2.setOnAction(this);
                            button2.setFont(new Font(15));
                            heroesMenu1.getChildren().add(button2);
                            break;
                        case "Mage" :
                            Button button3 = new Button(str);
                            button3.setPrefSize(300, 75);
                            button3.setOnAction(this);
                            button3.setFont(new Font(15));
                            heroesMenu1.getChildren().add(button3);
                            break;
                        case "Healer" :
                            Button button4 = new Button(str);
                            button4.setPrefSize(300, 75);
                            button4.setOnAction(this);
                            button4.setFont(new Font(15));
                            heroesMenu1.getChildren().add(button4);
                            break;
                    }
                counter +=1;
                }
            }
            if(eventButton.getParent() == heroesMenu1) {
                heroesMenu1.getChildren().remove(eventButton);
                eventButton = null;
                counter -= 1;
            }
            if(eventButton.getParent() == selectionConfirm && counter == length) {
                ObservableList children = heroesMenu1.getChildren();
                for(int i=0; i<children.size(); i++) {
                    try {
                        Button bButton = ((Button)children.get(i));
                        String str2 = bButton.getText();
                        switch(str2) {
                            case "Warrior" :
                                team.add(new Warrior());
                                break;
                            case "Hunter" :
                                team.add(new Hunter());
                                break;
                            case "Mage" :
                                team.add(new Mage());
                                break;
                            case "Healer" :
                                team.add(new Healer());
                                break;
                        }
                    }catch(java.lang.ClassCastException e) {}
                }
                window.setScene(fight1);
                startFights(fights);
            }
        }
    }

    static EventHandler choiceHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Game.fightStatusLabel.setText("C'est a votre tour ! Choisissez une action à faire.");
            Button evenButton = (Button)event.getSource();
            String str = evenButton.getText();
            switch(str) {
                case "Attack" :
                    hero.doAttack();
                    break;
                case "Deffend" :
                    hero.doDeffend();
                    break;
                case "Use Item" :
                    if(hero.inventory.size() > 0) {
                        Game.fightStatusLabel.setText("Select an Item among :");
                        for(int i=0; i< hero.getInventory().size(); i++) {
                            Consumable item = hero.getInventory().get(i);
                            Button bouton = new Button(item.getName());
                            bouton.setOnAction(this);
                            Game.inventoryBox.getChildren().add(bouton);
                        }
                    }
                    else {
                        System.out.println("Votre inventaire est vide !");
                        hero.choice(); 
                    }
                    break;
            }

        }
    };

    static ArrayList<Hero> team = new ArrayList<Hero>();
    static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    static ArrayList<Combatant> combatants;
    static int length;
    static int fights = 5;

    public static void main(String[] args) {
        launch();
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choissez le nombre de Heros dans votre equipe");
        length = scanner.nextInt();
        setHeroes();
        startFights(fights);
    }
    
    public static void setHeroes() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<length;i++) {
            System.out.println("Choisissez la classe de votre hero :");
            System.out.println("1 : Warrior | 2 : Hunter | 3 : Mage | 4 : Healer");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1 :
                    team.add(new Warrior());
                    break;
                case 2 :
                    team.add(new Hunter());
                    break;
                case 3 :
                    team.add(new Mage());
                    break;
                case 4 :
                    team.add(new Healer());
                    break;
            }
        }
    }

    public static ArrayList<Enemy> setEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for(int i=0;i<length;i++) {
            enemies.add(new Enemy());
        }
        return enemies;
    }

    public static void startFights(int fights) {
        int counter = 0;
        do {
            if(counter<fights-1) {
                enemies = setEnemies();
                fight();
                levelUp(1);
                levelUp(2);
            }
            if(counter == fights-1) {

            }
            counter += 1;
        } while(team.size() > 0 && counter <fights);
    }

    static Combatant currenCombatant;
    public static void fight() {
        combatants = new ArrayList<Combatant>();
        combatants.addAll(team);
        combatants.addAll(enemies);
        Collections.shuffle(combatants);
        int counter = 0;
        do {
            printBoard();
            if(counter < combatants.size()) {
                currenCombatant = combatants.get(counter);
                System.out.println(currenCombatant.getClass());
                try {
                    Hero hero = (Hero)currenCombatant;
                    while(itemButton.isPressed())
                    counter += 1;
                }catch(Error e) {
                    currenCombatant.choice();
                    counter += 1;
                }
            }
            if(counter == combatants.size()) {
                counter = 0;
            }
        } while(team.size() > 0 && enemies.size() > 0);
        if(team.size() > 0) {
            System.out.println("VICTOIRE !!!!!!!");
            for(int i=0; i<team.size(); i++) {
                Hero hero = team.get(i);
                hero.boost();
            }
        }
        if(enemies.size() > 0) {
            System.out.println("Vous avez perdu !!!");
        }
    }

    public static void levelUp(int nb) {
        if(nb ==1) {
            for(int i=0; i<team.size(); i++) {
                Hero hero = team.get(i);
                hero.levelUp();
            }
        }
        if(nb == 2) {
            for(int i=0; i<enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.levelUp();
            }
        }
    }
    
    public static void printBoard() {
        System.out.println();
        System.out.println(printTeam());
        System.out.println();
        System.out.println(printEnemies());
        System.out.println();
    }

    public static String printTeam() {
        String str = "";
        for(int i=0; i<team.size(); i++) {
            Hero hero = team.get(i);
            String hp = String.valueOf(hero.getHp());
            String armor = String.valueOf(hero.getArmor());
            str += "| "+hero.getClass()+" hp : "+hp+" armor : "+armor+" |";
        }
        return str;
    }

    public static String printEnemies() {
        String str = "";
        for(int i=0; i<enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            String hp = String.valueOf(enemy.getHp());
            String armor = String.valueOf(enemy.getArmor());
            str +="| "+enemy.getClass()+" hp : "+hp+" armor : "+armor+" |";
        }
        return str;
    }
    
    public static int randInt(int min, int max) {
        Random random = new Random();
        int randInt = random.nextInt((max-min)+1)+min;
        return randInt;
    }
}
