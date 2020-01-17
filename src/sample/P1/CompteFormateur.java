package sample.P1;

import sample.P2.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class CompteFormateur implements EditQuiz {
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String adresse;
    private String login;
    private String mdp;
    private Formation formation = new Formation();

    public CompteFormateur(String var1, String var2, LocalDate var3, String var4) {
        this.nom = var1;
        this.prenom = var2;
        this.date_naissance = var3;
        this.adresse = var4;
        this.setLogin();
    }
    public CompteFormateur(){}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.date_naissance.format(myFormatObj);
        this.mdp = this.nom + formattedDate;
    }

    public void setLogin() {
        this.login = this.prenom.substring(0, 1) + this.nom;
    }

    public String getLogin() {
        return this.login;
    }

    public void toConnect() {

    }

    public void toDisconnect() {

    }

    public void addNotionQuestion(Notion notion) {
        formation.getNotion().add(notion);
    }

    public void editNotion(int id_notion, String notion) {
        formation.getNotion().get(id_notion - 1).setNotion(notion);
    }

    public void editQuestion(int id_notion, int id_question, int type_question, String question) {
        switch (type_question) {
            case 1:
                formation.getNotion().get(id_notion - 1).getQcms().get(id_question - 1).setQuestion(question);
                break;
            case 2:
                formation.getNotion().get(id_notion - 1).getQcus().get(id_question - 1).setQuestion(question);
                break;
            case 3:
                formation.getNotion().get(id_notion - 1).getQos().get(id_question - 1).setQuestion(question);
                break;

        }
    }

    public Quiz generateQuiz(String nom, LocalDate date_ouv, LocalDate date_exp, ArrayList<Integer> num_notion, ArrayList<Integer> nb_questions_relatives) {
        ArrayList<QCM> qcmArrayList = new ArrayList<QCM>();
        ArrayList<QCU> qcuArrayList = new ArrayList<QCU>();
        ArrayList<QO> qoArrayList = new ArrayList<QO>();
        int i = 0;
        Random random = new Random();
        while (i < num_notion.size()) {
            int nb_questions = 1;
            while (nb_questions <= nb_questions_relatives.get(i)) {//num notion �a commence de 0
                if (formation.getNotion().get(num_notion.get(i)).getQcms().size() != 0) {
                    int num_question = random.nextInt(formation.getNotion().get(num_notion.get(i)).getQcms().size()); //generer un num entre 0 et la taille de l'Arraylist
                    //on v�rifie si cette question existe d�j� pour ne pas avoir une redondance
                    if (!qcmArrayList.contains(formation.getNotion().get(num_notion.get(i)).getQcms().get(num_question))) {
                        qcmArrayList.add(formation.getNotion().get(num_notion.get(i)).getQcms().get(num_question));
                        nb_questions++;
                    }
                }

                if (formation.getNotion().get(num_notion.get(i)).getQcus().size() != 0) {
                    if (nb_questions > nb_questions_relatives.get(i)) break;
                    int num_question = random.nextInt(formation.getNotion().get(num_notion.get(i)).getQcus().size());
                    if (!qcuArrayList.contains(formation.getNotion().get(num_notion.get(i)).getQcus().get(num_question))) {
                        qcuArrayList.add(formation.getNotion().get(num_notion.get(i)).getQcus().get(num_question));
                        nb_questions++;
                    }
                }

                if (formation.getNotion().get(num_notion.get(i)).getQos().size() != 0) {
                    if (nb_questions > nb_questions_relatives.get(i)) break;
                    int num_question = random.nextInt(formation.getNotion().get(num_notion.get(i)).getQos().size());
                    if (!qoArrayList.contains(formation.getNotion().get(num_notion.get(i)).getQos().get(num_question))) {
                        qoArrayList.add(formation.getNotion().get(num_notion.get(i)).getQos().get(num_question));
                        nb_questions++;
                    }
                }
            }
            i++;
        }
        Quiz quiz = new Quiz(nom, date_ouv, date_exp, qcmArrayList, qcuArrayList, qoArrayList);
        formation.getList_quiz().add(quiz);
        i = 0;
        while (i < formation.getApprenants().size()) {//on ajoute le quiz g�n�r� dans la liste des quiz non accomplis pour l'apprenant
            //on doit cr�er pour chaque apprenant des arrayList propres � lui m�me mais qui sont identiques.
            ArrayList<QCM> qcmArrayList1 = new ArrayList<QCM>();
            int k = 0;
            while (k < qcmArrayList.size()) {
                QCM qcm = new QCM(qcmArrayList.get(k).getPropositions());
                qcm.setQuestion(qcmArrayList.get(k).getQuestion());
                qcmArrayList1.add(qcm);
                k++;
            }
            ArrayList<QCU> qcuArrayList1 = new ArrayList<QCU>();
            k = 0;
            while (k < qcuArrayList.size()) {
                QCU qcu = new QCU(qcuArrayList.get(k).getPropositions());
                qcu.setQuestion(qcuArrayList.get(k).getQuestion());
                qcuArrayList1.add(qcu);
                k++;
            }
            ArrayList<QO> qoArrayList1 = new ArrayList<QO>();
            k = 0;
            while (k < qoArrayList.size()) {
                QO qo = new QO(qoArrayList.get(k).getMot_cle());
                qo.setQuestion(qoArrayList.get(k).getQuestion());
                qoArrayList1.add(qo);
                k++;
            }
            QuizNonAccompli quizNA = new QuizNonAccompli(nom, date_ouv, date_exp, qcmArrayList1, qcuArrayList1, qoArrayList1);
            formation.getApprenants().get(i).getQuiz_na().add(quizNA);
            i++;
        }
        quiz.setNb_questions();
        return quiz;
    }

    public void visualizeQuiz(int id_Quiz) {
        formation.getList_quiz().get(id_Quiz - 1).visualizeQuiz();
    }

    public void createFormation(String nom, String description, LocalDate date_d, LocalDate date_f) {
        Formation form = new Formation(nom, description, date_d, date_f);
        this.formation = form;
    }

    public void createCompteApprenant(String nom, String prenom, String adresse, LocalDate birthday) {
        CompteApprenant compteApprenant = new CompteApprenant(nom, prenom, adresse, birthday);
        this.formation.getApprenants().add(compteApprenant);
    }

    public void deleteCompteApprenant(int id_apprenant) {
        this.formation.getApprenants().remove(id_apprenant - 1);
    }

    public void editCompteApprenant() {
    }

    public void consultActivitiy(int id_Apprenant) {// id choisi de la listApprenants et commence de 0
        int nb_quiz_accompli = formation.getApprenants().get(id_Apprenant).getQuiz_a().size();
        int i = 0;
        int k = i;
        while (i < nb_quiz_accompli) {
            k++;
            System.out.println("          Quiz " + k + " : " + formation.getApprenants().get(id_Apprenant).getQuiz_a().get(i).getNom());
            System.out.println(" - Date d'ouverture : " + formation.getApprenants().get(id_Apprenant).getQuiz_a().get(i).getDate_ouv());
            System.out.println(" - Date d'expiration : " + formation.getApprenants().get(id_Apprenant).getQuiz_a().get(i).getDate_exp());
            System.out.println(" - Taux d'accomplissement : " + formation.getApprenants().get(id_Apprenant).getQuiz_a().get(i).getTauxAcc() + "%");
            System.out.println(" - Pourcentage de r�ussite : " + formation.getApprenants().get(id_Apprenant).getQuiz_a().get(i).calculPourcentage() + "%");
            i++;
        }
        int nb_quiz_non_accompli = formation.getApprenants().get(id_Apprenant).getQuiz_na().size();
        i = 0;
        while (i < nb_quiz_non_accompli) {
            k++;
            System.out.println("          Quiz " + k + " : " + formation.getApprenants().get(id_Apprenant).getQuiz_na().get(i).getNom());
            System.out.println(" - Date d'ouverture : " + formation.getApprenants().get(id_Apprenant).getQuiz_na().get(i).getDate_ouv());
            System.out.println(" - Date d'expiration : " + formation.getApprenants().get(id_Apprenant).getQuiz_na().get(i).getDate_exp());
            System.out.println(" - Taux d'accomplissement : " + formation.getApprenants().get(id_Apprenant).getQuiz_na().get(i).getTauxAcc() + "%");
            Date a = Date.valueOf(formation.getApprenants().get(id_Apprenant).getQuiz_na().get(i).getDate_exp());
            Date b = Date.valueOf(LocalDate.now());
            if (a.before(b)) System.out.println(" - Pourcentage de r�ussite : 0%");
            else System.out.println(" - Pourcentage de r�ussite : - ");
            i++;
        }
    }

    public void showRanking() {
        Boolean sWitch = true;
        int i;
        while (sWitch) {
            sWitch = false;
            i = 0;
            while (i < this.formation.getApprenants().size() - 1) {
                if (this.formation.getApprenants().get(i).compareTo(this.formation.getApprenants().get(i + 1)) < 0) {
                    CompteApprenant tmp = this.formation.getApprenants().get(i);
                    this.formation.getApprenants().set(i, this.formation.getApprenants().get(i + 1));
                    this.formation.getApprenants().set(i + 1, tmp);
                    sWitch = true;
                }
                i++;
            }
        }//on sort de la boucle avec switch==false, pas de switch => les apprenants sont class�s selon leurs taux de r�ussite moyens
        System.out.println("    Classement des apprenants selon le taux de r�ussite moyen : ");
        i = 0;
        while (i < this.formation.getApprenants().size()) {
            System.out.println(i + 1 + "- " + this.formation.getApprenants().get(i).getNom() + " " + this.formation.getApprenants().get(i).getPrenom() + " : " + this.formation.getApprenants().get(i).getTauxDeReussite());
            i++;
        }
    }

    public static void main(String[] args) {

    }

}

