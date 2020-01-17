package sample.P2;
import sample.P1.Formation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CompteApprenant {
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String adresse;
    private String login;
    private String mdp;
    private float tauxDeReussite;
    private ArrayList<QuizNonAccompli> quiz_na=new ArrayList<QuizNonAccompli>();
    private ArrayList<QuizAccompli> quiz_a=new ArrayList<QuizAccompli>();

    public CompteApprenant(String var1, String var2,  String var3,LocalDate var4) {
        this.nom=var1;
        this.prenom=var2;
        this.date_naissance=var4;
        this.adresse=var3;
        this.setLogin();
        this.setMdp();
    }
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

    public void setTauxDeReussite(float tauxDeReussite) {
        this.tauxDeReussite = tauxDeReussite;
    }

    public float getTauxDeReussite() {
        return tauxDeReussite;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.date_naissance.format(myFormatObj);
        this.mdp = this.nom+formattedDate;
    }
    public void editMdp(String mdp){
        this.mdp=mdp;
    }
    public void setLogin(){
        this.login=this.prenom.substring(0,1)+this.nom;
    }
    public String getLogin(){
        return this.login;
    }

    public ArrayList<QuizNonAccompli> getQuiz_na() {
        return quiz_na;
    }

    public void setQuiz_na(ArrayList<QuizNonAccompli> quiz_na) {
        this.quiz_na = quiz_na;
    }

    public void setQuiz_a(ArrayList<QuizAccompli> quiz_a) {
        this.quiz_a = quiz_a;
    }

    public ArrayList<QuizAccompli> getQuiz_a() {
        return quiz_a;
    }

    public void toConnect() {

    }
    public void toDisconnect() {

    }
    public float calculTaux_Reussite_Moyen(){
        int i=0; float somme=0;
        while (i<this.getQuiz_a().size()){
            somme +=this.quiz_a.get(i).calculPourcentage();
            i++;
        }
        somme=somme/this.quiz_a.size();
        this.setTauxDeReussite(somme);
        return somme;
    }
    public float compareTo(CompteApprenant o) {
        return this.calculTaux_Reussite_Moyen()-o.calculTaux_Reussite_Moyen() ;
    }
    public void visualizeListQuiz(){
        int nb_quiz_accompli=quiz_a.size();
        int i=0;
        int k=i;
        while (i<nb_quiz_accompli) {
            k++;
            System.out.println("          Quiz "+k+" : "+quiz_a.get(i).getNom());
            System.out.println(" - Date d'ouverture : "+quiz_a.get(i).getDate_ouv());
            System.out.println(" - Date d'expiration : "+quiz_a.get(i).getDate_exp());
            System.out.println(" - Taux d'accomplissement : "+quiz_a.get(i).getTauxAcc()+"%");
            System.out.println(" - Pourcentage de r�ussite : "+quiz_a.get(i).calculPourcentage()+"%");
            i++;
        }
        int nb_quiz_non_accompli=quiz_na.size();
        i=0;
        while (i<nb_quiz_non_accompli) {
            k++;
            System.out.println("          Quiz "+k+" : "+quiz_na.get(i).getNom());
            System.out.println(" - Date d'ouverture : "+quiz_na.get(i).getDate_ouv());
            System.out.println(" - Date d'expiration : "+quiz_na.get(i).getDate_exp());
            System.out.println(" - Taux d'accomplissement : "+quiz_na.get(i).getTauxAcc()+"%");
            Date a=Date.valueOf(quiz_na.get(i).getDate_exp());
            Date b=Date.valueOf(LocalDate.now());
            if (a.before(b)) System.out.println(" - Pourcentage de r�ussite : 0%");
            else  System.out.println(" - Pourcentage de r�ussite : - ");
            i++;
        }
    }
    public Quiz selectQuiz(int id_quiz){
        if (id_quiz<=this.quiz_a.size()){//le quiz choisi est accompli
            System.out.println("Vous avez accompli ce quiz et vous n'avez plus la possibilit� d'y r�pondre");
            System.out.println(" Le pourcentage de r�ussite est : "+this.quiz_a.get(id_quiz-1).calculPourcentage());
            System.out.println(" La correction :");
            int i=0;
            while (i<this.quiz_a.get(id_quiz-1).qcms.size()){
                int j=0;
                System.out.println(" "+i+1+" : "+this.quiz_a.get(id_quiz-1).qcms.get(i).getQuestion());
                while (j<this.quiz_a.get(id_quiz-1).qcms.get(i).getPropositions().size()){
                    if (this.quiz_a.get(id_quiz-1).qcms.get(i).getPropositions().get(j).getVal_verite()){
                        System.out.println(" - "+this.quiz_a.get(id_quiz-1).qcms.get(i).getPropositions().get(j).getPropsition());}
                    j++;
                }
                i++;
            }
            int k=i;
            i=0;
            while (i<this.quiz_a.get(id_quiz-1).qcus.size()){
                int j=0;
                System.out.println(" "+k+1+" : "+this.quiz_a.get(id_quiz-1).qcus.get(i).getQuestion());
                while (j<this.quiz_a.get(id_quiz-1).qcus.get(i).getPropositions().size()){
                    if (this.quiz_a.get(id_quiz-1).qcus.get(i).getPropositions().get(j).getVal_verite()){
                        System.out.println(" - "+this.quiz_a.get(id_quiz-1).qcus.get(i).getPropositions().get(j).getPropsition());}
                    j++;
                }
                i++;
                k++;
            }
            i=0;
            while (i<this.quiz_a.get(id_quiz-1).qos.size()){
                System.out.println(" "+k+1+" : "+this.quiz_a.get(id_quiz-1).qos.get(i).getQuestion());
                System.out.println(" Le mot cl� : "+this.quiz_a.get(id_quiz-1).qos.get(i).getMot_cle());
                i++;
                k++;
            }
            return this.quiz_a.get(id_quiz-1);
        }
        else {
            this.quiz_na.get(id_quiz-1).visualizeQuiz();
            this.quiz_na.get(id_quiz-1).setOuvert(true);
            System.out.println("1- R�pondre au quiz ");
            System.out.println("2- Retour");
            Scanner sc=new Scanner(System.in);
            int choix=sc.nextInt();
            Date a=Date.valueOf(this.quiz_na.get(id_quiz-1).getDate_exp());
            Date b=Date.valueOf(LocalDate.now());
            if (choix==1 && a.after(b) ){//le quiz n'est pas encore expir�
            int index=id_quiz-this.quiz_na.size()-1;
            if (index<0) index=0;
            this.quiz_na.get(index).visualizeQuizApprenant();

            }
            int index=id_quiz-this.quiz_na.size()-1;
            if (index<0) index=0;
            return this.quiz_na.get(index);
        }

    }
    public void editAnswerQCM(Quiz quiz,int id_question,int id_reponse,String answer){
            quiz.qcms.get(id_question-1).getReponses().get(id_reponse-1).setReponse(answer);
    }

    public void editAnswerQCU_QO(Quiz quiz,int type_question,int id_question,String answer){
        switch (type_question){
            case 2:
                quiz.qcus.get(id_question-1).getReponse().setReponse(answer);
                break;
            case 3:
                quiz.qos.get(id_question-1).getReponse().setReponse(answer);
                break;
    }}
    public void evaluateQuiz(QuizNonAccompli quiz){
        if (quiz.authorised){
        //1 : g�n�rer une copie virtuelle des r�ponses de l'apprenant
        System.out.println("     Vos r�ponses : ");
        int i=0;
        int k=i;
            while (i<quiz.qcms.size()){
            int j=0;
            k++;
            System.out.print(" "+k+" :");
            while (j<quiz.qcms.get(i).getReponses().size()){
            System.out.println(" - "+quiz.qcms.get(i).getReponses().get(j).getReponse());
            j++;
            }
            i++;
        }
        i=0;
        while (i<quiz.qcus.size()){
            k++;
                System.out.println(" "+k+" :"+" - "+quiz.qcus.get(i).getReponse().getReponse());
            i++;
        }
            i=0;
        while (i<quiz.qos.size()){
            k++;
            System.out.println(" "+k+" :"+" - "+quiz.qos.get(i).getReponse().getReponse());
            i++;
        }

        //2 : soumettre � �valuation, ou bien retourner au quiz
        System.out.println(" 1- Soumettre les r�ponses � �valuation");
        System.out.println(" 2- Revenir au quiz");
        Scanner scanner=new Scanner(System.in);
        int choix=scanner.nextInt();
        switch (choix){
            case 1:
                //cr�er un quizAccompli
                QuizAccompli quizAccompli=new QuizAccompli();
                quizAccompli.setNom(quiz.getNom());
                quizAccompli.setDate_ouv(quiz.getDate_ouv());
                quizAccompli.setDate_exp(quiz.getDate_exp());
                quizAccompli.setTauxAcc(100);
                quizAccompli.setQcms(quiz.getQcms());
                quizAccompli.setQcus(quiz.getQcus());
                quizAccompli.setQos(quiz.getQos());
                quizAccompli.setNb_questions();
                quizAccompli.setAuthorised(false);
                quiz.setAuthorised(false);
                quizAccompli.setAccomplished(true);
                quizAccompli.setOuvert(true);

                this.quiz_a.add(quizAccompli);
                System.out.println(" Le pourcentage de r�ussite est : "+quizAccompli.calculPourcentage()+" %");
                System.out.println(" La correction :");
                i=0;
                int l=i;
                while (i<quiz.qcms.size()){
                    int j=0;
                    l++;
                    System.out.println(" "+l+" : "+quiz.qcms.get(i).getQuestion());
                    while (j<quiz.qcms.get(i).getPropositions().size()){
                        if (quiz.qcms.get(i).getPropositions().get(j).getVal_verite()){
                        System.out.println(" - "+quiz.qcms.get(i).getPropositions().get(j).getPropsition());}
                        j++;
                    }
                    i++;
                }
                i=0;
                while (i<quiz.qcus.size()){
                    int j=0;
                    l++;
                    System.out.println(" "+l+" : "+quiz.qcus.get(i).getQuestion());
                    while (j<quiz.qcus.get(i).getPropositions().size()){
                        if (quiz.qcus.get(i).getPropositions().get(j).getVal_verite()){
                            System.out.println(" - "+quiz.qcus.get(i).getPropositions().get(j).getPropsition());}
                        j++;
                    }
                    i++;
                }
                i=0;
                while (i<quiz.qos.size()){
                    l++;
                    System.out.println(" "+l+" : "+quiz.qos.get(i).getQuestion());
                    System.out.println(" Le mot cl� : "+quiz.qos.get(i).getMot_cle());
                    i++;
                }
                this.quiz_na.remove(quiz);
                break;
            case 2:
                quiz.visualizeQuizApprenant();
                System.out.println("Voulez-vous modifier vos r�ponses ? (R�pondre par oui/non)");
                String s=scanner.next();
                String oui="oui";
                String non="non";
                if (s.equals(oui)) {
                    System.out.println("Choisir le type de question (1:QCM 2:QCU 3:QO)");
                    int choice=scanner.nextInt();
                    switch (choice){
                        case 1:

                            System.out.println("Veuillez choisir le numero de la question ainsi que le numero de la r�ponse :");
                            int num_q=scanner.nextInt();
                            int num_r=scanner.nextInt();
                            System.out.println(" -> Saisir la nouvelle r�ponse : ");
                            String nv=scanner.nextLine();
                            this.editAnswerQCM(quiz,num_q,num_r,nv);
                            break;
                        case 2:
                            System.out.println("Veuillez choisir le numero de la question ainsi que le numero de la r�ponse :");
                             num_r=scanner.nextInt();
                            System.out.println(" -> Saisir la nouvelle r�ponse : ");
                            nv=scanner.nextLine();
                            this.editAnswerQCU_QO(quiz,2,num_r,nv);
                            quiz.visualizeQuizApprenant();
                            break;
                        case 3:
                            System.out.println("Veuillez choisir le numero de la question ainsi que le numero de la r�ponse :");
                            num_r=scanner.nextInt();
                            System.out.println(" -> Saisir la nouvelle r�ponse : ");
                            nv=scanner.nextLine();
                            this.editAnswerQCU_QO(quiz,3,num_r,nv);
                            quiz.visualizeQuizApprenant();
                            break;
                    }

                }
                break;
        }

    }}

    public static void main(String [] args){

    }
}
