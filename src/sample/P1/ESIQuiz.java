package sample.P1;
import sample.P2.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

interface EditQuiz{
    static void editTitle(Quiz quiz,String title){
        quiz.setNom(title);
    }
    static void editDate_ouv(Quiz quiz,LocalDate date) {
        quiz.setDate_ouv(date);
    }
    static void editDate_exp(Quiz quiz,LocalDate date) {
        quiz.setDate_exp(date);
    }
    static void deleteQuestion(Quiz quiz,int type_question,int id_question){
        switch (type_question){
            case 1:
                quiz.getQcms().remove(id_question-1);
                break;
            case 2:
                quiz.getQcus().remove(id_question-1);
                break;
            case 3:
                quiz.getQos().remove(id_question-1);
                break;
        }
    }
    static void addQuestion(Quiz quiz, Notion notion){
        Boolean ajoute=false;
        while(!ajoute) {
            Random random = new Random();
            int num_question;
            int type_question = random.nextInt(3) + 1;
            if (type_question == 1) {
                if (notion.getQcms().size() != 0) {
                    num_question = random.nextInt(notion.getQcms().size());
                    if (quiz.getQcms().size()!=0){
                        if (!quiz.getQcms().contains(notion.getQcms().get(num_question))) {
                        quiz.getQcms().add(notion.getQcms().get(num_question));
                        ajoute=true;
                    } else type_question = 2;}else {
                        quiz.getQcms().add(notion.getQcms().get(num_question));
                        ajoute=true;
                    }
                } else type_question = 2;
            }

            if (type_question == 2) {
                if (notion.getQcus().size() != 0) {
                    num_question = random.nextInt(notion.getQcus().size());
                    if (quiz.getQcus().size()!=0){
                    if (!quiz.getQcus().contains(notion.getQcus().get(num_question))) {
                        quiz.getQcus().add(notion.getQcus().get(num_question));
                        ajoute=true;
                    } else type_question = 3;}else {
                    quiz.getQcus().add(notion.getQcus().get(num_question));
                    ajoute=true;
                }
                } else type_question = 3;
            }
            if (type_question == 3) {
                if (notion.getQos().size() != 0) {
                    num_question = random.nextInt(notion.getQos().size());
                    if (quiz.getQos().size()!=0){
                    if (!quiz.getQos().contains(notion.getQos().get(num_question))) {
                        quiz.getQos().add(notion.getQos().get(num_question));
                        ajoute=true;
                    }}else {
                        quiz.getQos().add(notion.getQos().get(num_question));
                        ajoute=true;
                    }
                    }
                }
            }
        }

    static void editQuestion(Formation formation,Quiz quiz,int type_question,int id_question){
        int i;Boolean stop;
        switch (type_question){
            case 1:
                i=0;
                 stop=false;
                while (i<formation.getNotion().size() && !stop){
                    int j=0;
                    while (j<formation.getNotion().get(i).getQcms().size() && !stop){
                    if (formation.getNotion().get(i).getQcms().get(j).getQuestion().equals(quiz.getQcms().get(id_question-1).getQuestion())){
                        //si on a trouvé la question bah il faut la changer avec une autre de cette mm notion
                        int nouvelle_ques;
                        Boolean same=true;
                        while (same)//tant que random genère la mm question, on regenère un nouveau num
                        {
                            Random random=new Random();
                            nouvelle_ques=random.nextInt(formation.getNotion().get(i).getQcms().size());
                            if (!quiz.getQcms().contains(formation.getNotion().get(i).getQcms().get(nouvelle_ques))){ same=false;
                                quiz.getQcms().set(id_question-1,formation.getNotion().get(i).getQcms().get(nouvelle_ques));
                            }
                        }
                       stop=true;
                    }else j++;
                    }
                    i++;
                }
                break;
            case 2:
                i=0;
                stop=false;
                while (i<formation.getNotion().size() && !stop){
                    int j=0;
                    while (j<formation.getNotion().get(i).getQcus().size() && !stop){
                        if (formation.getNotion().get(i).getQcus().get(j).getQuestion().equals(quiz.getQcus().get(id_question-1).getQuestion())){
                            //si on a trouvé la question bah il faut la changer avec une autre de cette mm notion
                            int nouvelle_ques;
                            Boolean same=true;
                            while (same)//tant que random genère la mm question, on regenère un nouveau num
                            {
                                Random random=new Random();
                                nouvelle_ques=random.nextInt(formation.getNotion().get(i).getQcus().size());
                                if (nouvelle_ques!=id_question-1 && !quiz.getQcus().contains(formation.getNotion().get(i).getQcus().get(nouvelle_ques))){ same=false;
                                    quiz.getQcus().set(id_question-1,formation.getNotion().get(i).getQcus().get(nouvelle_ques));
                                }
                            }
                            stop=true;
                        }else j++;
                    }
                    i++;
                }
                break;
            case 3:
                i=0;
                stop=false;
                while (i<formation.getNotion().size() && !stop){
                    int j=0;
                    while (j<formation.getNotion().get(i).getQos().size() && !stop){
                        if (formation.getNotion().get(i).getQos().get(j).getQuestion().equals(quiz.getQos().get(id_question-1).getQuestion())){
                            //si on a trouvé la question bah il faut la changer avec une autre de cette mm notion
                            int nouvelle_ques;
                            Boolean same=true;
                            while (same)//tant que random genère la mm question, on regenère un nouveau num
                            {
                                Random random=new Random();
                                nouvelle_ques=random.nextInt(formation.getNotion().get(i).getQos().size());
                                if (nouvelle_ques!=id_question-1 && !quiz.getQos().contains(formation.getNotion().get(i).getQos().get(nouvelle_ques))){ same=false;
                                    quiz.getQos().set(id_question-1,formation.getNotion().get(i).getQos().get(nouvelle_ques));
                                }
                            }
                            stop=true;
                        }else j++;
                    }
                    i++;
                }
                break;
        }
    }
}

public class ESIQuiz {
    public static void main(String[] args){
        LocalDate date = LocalDate.of(1999, 12, 17);
        CompteFormateur nour = new CompteFormateur("EL HASSANE", "Nour", date, "el harrach Algiers");
        System.out.print(" Création d'une formation : \n - Nom : ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        System.out.print(" - Description : ");
        String description = sc.nextLine();
        System.out.print(" - Date début : ");
        String dat = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate date1 = LocalDate.parse(dat, formatter);
        System.out.print(" - Date fin : ");
        String datf = sc.nextLine();
        LocalDate date2 = LocalDate.parse(datf, formatter);
        nour.createFormation(nom, description, date1, date2);

        int choix = 1;
        while (choix != 0) {
            System.out.println("\n1- Ajouter un apprenant");
            System.out.println("0- Quitter");
            choix = sc.nextInt();
            if (choix == 1) {
                System.out.print("Nom et ");
                String noun = sc.nextLine();
                System.out.print("Prenom : ");
                String prenoun = sc.nextLine();
                System.out.print("Date de naissance (jj/mm/aaaa) : ");
                String datt = sc.nextLine();
                LocalDate birthday = LocalDate.parse(datt, formatter);
                System.out.print("L'adresse : ");
                String adress = sc.nextLine();
                CompteApprenant compteApprenant = new CompteApprenant(noun, prenoun, adress, birthday);
                nour.getFormation().getApprenants().add(compteApprenant);
            }
        }
        int i = 0;
        System.out.println("\n   Liste des apprenants ");
        while (i < nour.getFormation().getApprenants().size()) {
            System.out.println(i + 1 + "- " + nour.getFormation().getApprenants().get(i).getNom() + " " + nour.getFormation().getApprenants().get(i).getPrenom());
            i++;
        }
        while (choix != 3) {
            System.out.println("\n1- Ajouter un apprenant");
            System.out.println("2- Supprimer un apprenant");
            System.out.println("3- Quitter");
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    System.out.print("Nom et ");
                    String noun = sc.nextLine();
                    System.out.print("Prenom : ");
                    String prenoun = sc.nextLine();
                    System.out.print("Date de naissance (jj/mm/aaaa) : ");
                    dat = sc.nextLine();
                    LocalDate birthday = LocalDate.parse(dat, formatter);
                    System.out.print("L'adresse : ");
                    String adress = sc.nextLine();
                    CompteApprenant compteApprenant = new CompteApprenant(noun, prenoun, adress, birthday);
                    nour.getFormation().getApprenants().add(compteApprenant);
                    break;
                case 2:
                    System.out.print("\n -> Choisir le numero de l'apprenant :");
                    choix = sc.nextInt();
                    nour.getFormation().getApprenants().remove(choix - 1);
                    break;
            }
            i = 0;
            while (i < nour.getFormation().getApprenants().size()) {
                System.out.println(i + 1 + "- " + nour.getFormation().getApprenants().get(i).getNom() + " " + nour.getFormation().getApprenants().get(i).getPrenom());
                i++;
            }
        }
        choix = 1;
        while (choix != 2) {
            System.out.println("\n1- Ajouter une notion");
            System.out.println("2- Quitter");
            choix = sc.nextInt();
            if (choix == 1) {
                System.out.print(" -> Veuillez saisir la notion : ");
                String note = sc.next()+sc.nextLine();
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
                            String qes = sc.next()+sc.nextLine();
                            ArrayList<Proposition> propo_qcm=new ArrayList<Proposition>();
                            System.out.println(" --> Les propositions : ");
                            int choice = 1;
                            while (choice != 0) {
                                System.out.println("1- Ajouter une proposition");
                                System.out.println("0- Quitter");
                                choice = sc.nextInt();
                                if (choice == 1) {
                                    System.out.println(" --> La proposition : ");
                                    String prop =sc.next()+sc.nextLine();
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
                            ArrayList<Proposition> propo_qcu=new ArrayList<Proposition>();
                            qes=sc.next()+sc.nextLine();
                            System.out.println(" --> Les propositions : ");
                            int choice1 = 1;
                            while (choice1 != 0) {
                                System.out.println("1- Ajouter une proposition");
                                System.out.println("0- Quitter");
                                choice1 = sc.nextInt();
                                if (choice1 == 1) {
                                    System.out.println(" --> La proposition : ");
                                    String propo = sc.next()+sc.nextLine();
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
                            qes=sc.next()+sc.nextLine();
                            System.out.println(" --> Le mot clé : ");
                            String mot = sc.next()+sc.nextLine();
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
        i = 0;
        while (i < nour.getFormation().getNotion().size()) {
            System.out.print(i + "- " + nour.getFormation().getNotion().get(i).getNotion()+"\n");
            i++;
        }

        System.out.println(" * Nom du quiz : ");
        String n=sc.next()+sc.nextLine();
        System.out.print(" * Date d'ouverture : ");
        datf = sc.nextLine();
        date1 = LocalDate.parse(datf, formatter);
        System.out.print(" * Date d'expiration : ");
        datf = sc.nextLine();
        date2 = LocalDate.parse(datf, formatter);
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
        Quiz quiz=nour.generateQuiz(n,date1,date2,integers,numeros);
        nour.getFormation().getList_quiz().add(quiz);
        quiz.visualizeQuiz();
        choix=1;
        while (choix!=0){
        System.out.println("1- Ajouter une question");
        System.out.println("2- Remplacer une question");
        System.out.println("3- Supprimer une question");
        System.out.println("0-Quitter");
        choix=sc.nextInt();
        switch (choix){
            case 1:
                System.out.println(" Liste des notions de cette formation : ");
                i = 0;
                while (i < nour.getFormation().getNotion().size()) {
                    System.out.print(i + "- " + nour.getFormation().getNotion().get(i).getNotion()+"\n");
                    i++;
                }
                System.out.println("choisir le numero de la notion : ");
                i=sc.nextInt();
                EditQuiz.addQuestion(quiz,nour.getFormation().getNotion().get(i));
                quiz.visualizeQuiz();
                break;
            case 2:
                System.out.println("choisir le type de question (1:QCM 2:QCU 3:QO)");
                int type=sc.nextInt();
                System.out.println("choisir le numero de la question : ");
                int id=sc.nextInt();
                EditQuiz.editQuestion(nour.getFormation(),quiz,type,id);
                quiz.visualizeQuiz();
                break;
            case 3:
                System.out.println("choisir le type de question (1:QCM 2:QCU 3:QO)");
                 type=sc.nextInt();
                System.out.println("choisir le numero de la question : ");
                 id=sc.nextInt();
                EditQuiz.deleteQuestion(quiz,type,id);
                quiz.visualizeQuiz();
                break;
        }}
        System.out.println("Liste des quiz pour l'apprenant :"+nour.getFormation().getApprenants().get(0).getNom()+" "+nour.getFormation().getApprenants().get(0).getPrenom());
        nour.getFormation().getApprenants().get(0).visualizeListQuiz();
        System.out.print("choisir le numero du quiz : ");
        choix=sc.nextInt();
        nour.getFormation().getApprenants().get(0).evaluateQuiz((QuizNonAccompli) nour.getFormation().getApprenants().get(0).selectQuiz(choix));
        nour.getFormation().getApprenants().get(0).visualizeListQuiz();
        System.out.println("\n Liste des quiz pour l'apprenant :"+nour.getFormation().getApprenants().get(1).getNom()+" "+nour.getFormation().getApprenants().get(1).getPrenom());
        nour.getFormation().getApprenants().get(1).visualizeListQuiz();
        System.out.print("choisir le numero du quiz : ");
        choix=sc.nextInt();
        nour.getFormation().getApprenants().get(1).evaluateQuiz((QuizNonAccompli) nour.getFormation().getApprenants().get(1).selectQuiz(choix));
        nour.getFormation().getApprenants().get(1).visualizeListQuiz();
        System.out.println("Consultation de l'activité de l'apprenant :"+nour.getFormation().getApprenants().get(0).getNom()+" "+nour.getFormation().getApprenants().get(0).getPrenom() );
        nour.consultActivitiy(0);
        System.out.println("Classement des apprenants selon le taux de réussite moyen : ");
        nour.showRanking();



    }
}
