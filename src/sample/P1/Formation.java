package sample.P1;
import sample.P2.CompteApprenant;
import sample.P2.Notion;
import sample.P2.Quiz;
import java.time.LocalDate;
import java.util.ArrayList;

public class Formation {
    private String nom;
    private String description;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private ArrayList<Notion> notion=new ArrayList<Notion>();
    private ArrayList<Quiz> list_quiz=new ArrayList<Quiz>();
    private ArrayList<CompteApprenant> apprenants=new ArrayList <CompteApprenant>();

    public ArrayList<Notion> getNotion() {
        return notion;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Quiz> getList_quiz() {
        return list_quiz;
    }

    public void setList_quiz(ArrayList<Quiz> list_quiz) {
        this.list_quiz = list_quiz;
    }

    public ArrayList<CompteApprenant> getApprenants() {
        return apprenants;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApprenants(ArrayList<CompteApprenant> apprenants) {
        this.apprenants = apprenants;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNotion(ArrayList<Notion> notion) {
        this.notion = notion;
    }
    public Formation(String var1,String var2,LocalDate var3,LocalDate var4){
        this.nom=var1;
        this.description=var2;
        this.date_debut=var3;
        this.date_fin=var4;
    }
    public Formation(){}

}
