package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.P1.CompteFormateur;
import sample.P1.Proposition;
import sample.P1.QO;
import sample.P2.Notion;
import sample.P2.QCM;
import sample.P2.QCU;
import sample.P2.Quiz;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller implements Initializable {
    @FXML
    private Label invalidlabel;
    @FXML
    private Label name;
    @FXML
    private TextField textfieldusername;
    @FXML
    private TextField textfieldpwd;
    @FXML
    private TextFlow quiz;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField date;
    @FXML
    private TextField adress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleinscriptionBtn(ActionEvent event) throws IOException {
        Parent inscription_page = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        Scene ins_scene = new Scene(inscription_page, 1000, 1000);
        ins_scene.getStylesheets().add(ESIQuiz_GUI.class.getResource("style.css").toExternalForm());

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(ins_scene);
        app_stage.show();
        LocalDate dateTime = LocalDate.of(1999, 12, 17);

        CompteFormateur formateur = new CompteFormateur(nom.getText(), prenom.getText(), dateTime, adress.getText());
        Stage app_stageQuiz = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stageQuiz.setScene(ins_scene);
        app_stageQuiz.show();
    }

    @FXML
    public void handleConnexionBtn(ActionEvent event) throws IOException {
        Parent cnx_page = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene cnx_scene = new Scene(cnx_page);
        cnx_scene.getStylesheets().add(ESIQuiz_GUI.class.getResource("style.css").toExternalForm());

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (isValid()) {
            app_stage.hide();
            app_stage.setScene(cnx_scene);
            app_stage.show();
        } else {
            textfieldusername.clear();
            textfieldpwd.clear();
            invalidlabel.setText("Pseudo ou mot de passe incorrect");
        }
    }

    private boolean isValid() {
        return (textfieldusername.getText().equals("nour")) && (textfieldpwd.getText().equals("EL HASSANE17-12-1999"));
    }

    @FXML
    public void handleGenerateQuizBtn(ActionEvent event) {
        Parent rootQuiz = new Parent() {
            @Override
            protected ObservableList<Node> getChildren() {
                return super.getChildren();
            }
        };
        Scene sceneQuiz = new Scene(rootQuiz, 1000, 1000);
        rootQuiz.setId("rootQuiz");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
        LocalDate date = LocalDate.of(1999, 12, 17);
        CompteFormateur nour = new CompteFormateur("EL HASSANE", "Nour", date, "el harrach Algiers");

        Scanner sc = new Scanner(System.in);
        int choix = 1;
        while (choix != 2) {
            System.out.println("\n1- Ajouter une notion");
            System.out.println("2- Quitter");
            choix = sc.nextInt();
            if (choix == 1) {
                System.out.print(" -> Veuillez saisir la notion : ");
                String note = sc.next() + sc.nextLine();
                ArrayList<QCM> qcms = new ArrayList<QCM>();
                ArrayList<QCU> qcus = new ArrayList<QCU>();
                ArrayList<QO> qos = new ArrayList<QO>();

                while (choix != 0) {
                    System.out.println(" -> Veuillez introduire les questions avec leurs propositions :\n    Choisir le type de question (1:QCM-2:QCU-3:QO)");
                    System.out.println("0- Quitter");
                    choix = sc.nextInt();
                    switch (choix) {
                        case 0:
                            break;
                        case 1:
                            System.out.print(" --> La question : ");
                            String qes = sc.next() + sc.nextLine();
                            ArrayList<Proposition> propo_qcm = new ArrayList<Proposition>();
                            System.out.println(" --> Les propositions : ");
                            int choice = 1;
                            while (choice != 0) {
                                System.out.println("1- Ajouter une proposition");
                                System.out.println("0- Quitter");
                                choice = sc.nextInt();
                                if (choice == 1) {
                                    System.out.println(" --> La proposition : ");
                                    String prop = sc.next() + sc.nextLine();
                                    System.out.println(" La proposition en dessus est true ou false ?");
                                    Boolean val_ver = sc.nextBoolean();
                                    Proposition proposition = new Proposition(prop, val_ver);
                                    propo_qcm.add(proposition);
                                }
                            }
                            QCM qcm = new QCM(propo_qcm);
                            qcm.setQuestion(qes);
                            qcms.add(qcm);
                            break;
                        case 2:
                            System.out.print(" --> La question : ");
                            ArrayList<Proposition> propo_qcu = new ArrayList<Proposition>();
                            qes = sc.next() + sc.nextLine();
                            System.out.println(" --> Les propositions : ");
                            int choice1 = 1;
                            while (choice1 != 0) {
                                System.out.println("1- Ajouter une proposition");
                                System.out.println("0- Quitter");
                                choice1 = sc.nextInt();
                                if (choice1 == 1) {
                                    System.out.println(" --> La proposition : ");
                                    String propo = sc.next() + sc.nextLine();
                                    System.out.println(" La proposition en dessus est true ou false ?");
                                    Boolean val_veri = sc.nextBoolean();
                                    Proposition proposition = new Proposition(propo, val_veri);
                                    propo_qcu.add(proposition);
                                }

                            }
                            QCU qcu = new QCU(propo_qcu);
                            qcu.setQuestion(qes);
                            qcus.add(qcu);
                            break;
                        case 3:
                            System.out.println(" --> La question : ");
                            qes = sc.next() + sc.nextLine();
                            System.out.println(" --> Le mot clé : ");
                            String mot = sc.next() + sc.nextLine();
                            QO qo = new QO(mot);
                            qo.setQuestion(qes);
                            qos.add(qo);
                            break;
                    }
                }
                Notion notion = new Notion(note, qcms, qcus, qos);
                nour.getFormation().getNotion().add(notion);
            }
        }
        System.out.println(" Liste des notions de cette formation : ");
        int i = 0;
        while (i < nour.getFormation().getNotion().size()) {
            System.out.print(i + "- " + nour.getFormation().getNotion().get(i).getNotion() + "\n");
            i++;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        System.out.println(" * Nom du quiz : ");
        String n = sc.next() + sc.nextLine();
        System.out.print(" * Date d'ouverture : ");
        String datf = sc.nextLine();
        LocalDate date1 = LocalDate.parse(datf, formatter);
        System.out.print(" * Date d'expiration : ");
        datf = sc.nextLine();
        LocalDate date2 = LocalDate.parse(datf, formatter);
        System.out.println(" -> Veuillez choisir les notions que vous voulez inclure dans le quiz : ");
        String numbers = sc.nextLine();
        String[] parts = numbers.split(" ");
        ArrayList<Integer> integers = new ArrayList<Integer>();
        i = 0;
        while (i < parts.length) {
            integers.add(Integer.parseInt(parts[i]));
            i++;
        }
        System.out.println(" -> Saisir le nombre de questions relatives à chaque notion : ");
        String questions = sc.nextLine();
        String[] part = questions.split(" ");
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        i = 0;
        while (i < part.length) {
            numeros.add(Integer.parseInt(part[i]));
            i++;
        }
        Quiz quiz = nour.generateQuiz(n, date1, date2, integers, numeros);
        nour.getFormation().getList_quiz().add(quiz);
        try {

            sceneQuiz.setRoot((Parent) fxmlLoader.load());
        } catch (IOException e) {

        }
        VBox vb = new VBox();
        vb.setId("Nom");
        Label label=new Label();
        label.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        vb.getChildren().add(label);
        Label name = new Label();
        name.setId("name");
        name.setText("                " + quiz.getNom() + "\n" + "             date d'ouverture : " + quiz.getDate_ouv() + "\n" + "             date d'expiration : " + quiz.getDate_exp() + "\n");
        vb.getChildren().add(name);
        if (quiz.getQcms().size() != 0) {
            label = new Label();
            label.setText(" QCMs : ");
            label.setId("qcm");
            vb.getChildren().add(label);
            i = 0;
            while (i < quiz.getQcms().size()) {
                label = new Label();
                int k = i + 1;
                label.setText(k + "- " + quiz.getQcms().get(i).getQuestion());
                label.setId("questions");
                vb.getChildren().add(label);
                int j = 0;
                while (j < quiz.getQcms().get(i).getPropositions().size()) {
                    label = new Label();
                    label.setText("       * " + quiz.getQcms().get(i).getPropositions().get(j).getPropsition());
                    label.setId("prop");
                    vb.getChildren().add(label);
                    j++;
                }
                i++;
            }
        }
        if (quiz.getQcus().size() != 0) {
            label = new Label();
            label.setText("  QCUs : ");
            vb.getChildren().add(label);
            label.setId("qcm");
            i = 0;
            while (i < quiz.getQcus().size()) {
                label = new Label();
                int k = i + 1;
                label.setText(k + "- " + quiz.getQcus().get(i).getQuestion() + "\n");
                label.setId("questions");
                vb.getChildren().add(label);
                int j = 0;
                while (j < quiz.getQcus().get(i).getPropositions().size()) {
                    label = new Label();
                    label.setText("       * " + quiz.getQcus().get(i).getPropositions().get(j).getPropsition() + "\n");
                    label.setId("prop");
                    vb.getChildren().add(label);
                    j++;
                }
                i++;
            }
        }
        if (quiz.getQos().size() != 0) {
            label = new Label();
            label.setText("  QOs : ");
            vb.getChildren().add(label);
            label.setId("qcm");

            i = 0;
            while (i < quiz.getQos().size()) {
                label = new Label();
                int k = i + 1;
                label.setText(k + "- " + quiz.getQos().get(i).getQuestion() + "\n");
                label.setId("questions");
                vb.getChildren().add(label);
                i++;
            }
        }
        sceneQuiz.getStylesheets().add(ESIQuiz_GUI.class.getResource("style.css").toExternalForm());
        sceneQuiz.setRoot(vb);
        Stage app_stageQuiz = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stageQuiz.setScene(sceneQuiz);
        app_stageQuiz.show();
    }

    public void handleDisconnectBtn(ActionEvent event) {
        Parent root = new Parent() {
            @Override
            protected ObservableList<Node> getChildren() {
                return super.getChildren();
            }
        };
        Scene scene = new Scene(root, 580, 600);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {

            scene.setRoot((Parent) fxmlLoader.load());
        } catch (IOException e) {
        }
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}
