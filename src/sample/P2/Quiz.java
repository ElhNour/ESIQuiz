package sample.P2;
import sample.P1.Proposition;
import sample.P1.QO;
import sample.P1.Reponse;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    protected String nom;
    protected LocalDate date_ouv;
    protected LocalDate date_exp;
    protected ArrayList<QCM> qcms=new ArrayList<QCM>();
    protected ArrayList<QCU> qcus=new ArrayList<QCU>();
    protected ArrayList<QO> qos=new ArrayList<QO>();
    protected int tauxAcc;
    protected int nb_questions;
    protected Boolean accomplished;
    protected Boolean authorised=true;
    protected   Boolean ouvert=false;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setDate_ouv(LocalDate date_ouv) {
        this.date_ouv = date_ouv;
    }

    public LocalDate getDate_ouv() {
        return date_ouv;
    }

    public void setDate_exp(LocalDate date_exp) {
        this.date_exp = date_exp;
    }

    public LocalDate getDate_exp() {
        return date_exp;
    }

    public void setQcms(ArrayList<QCM> qcms) {
        this.qcms = qcms;
    }

    public ArrayList<QCM> getQcms() {
        return qcms;
    }

    public void setQcus(ArrayList<QCU> qcus) {
        this.qcus = qcus;
    }

    public ArrayList<QCU> getQcus() {
        return qcus;
    }

    public void setQos(ArrayList<QO> qos) {
        this.qos = qos;
    }

    public ArrayList<QO> getQos() {
        return qos;
    }

    public void setTauxAcc(int tauxAcc) {
        this.tauxAcc = tauxAcc;
    }

    public int getTauxAcc() {
        return tauxAcc;
    }

    public void setAccomplished(Boolean accomplished) {
        this.accomplished = accomplished;
    }

    public Boolean getAccomplished() {
        return accomplished;
    }

    public void setAuthorised(Boolean authorised) {
        this.authorised = authorised;
    }

    public Boolean getOuvert() {
        return ouvert;
    }

    public void setOuvert(Boolean ouvert) {
        this.ouvert = ouvert;
    }

    public Boolean getAuthorised() {
        return authorised;
    }
    public void setNb_questions() {
        this.nb_questions = this.qcms.size()+this.qcus.size()+this.qos.size();
    }

    public int getNb_questions() {
        return nb_questions;
    }
    public void visualizeQuiz(){
        System.out.println("         "+this.nom+"\n"+"date d'ouverture : "+this.date_ouv+"\n"+"date d'expiration : "+this.date_exp+"\n");
        if (qcms.size()!=0){
        System.out.print("  QCMs : ");
        int i=0;
        while (i<qcms.size()){
            System.out.println(i+1 + "- "+qcms.get(i).getQuestion()+"\n");
            int j=0;
            while (j<qcms.get(i).getPropositions().size()){
            System.out.println("       * "+qcms.get(i).getPropositions().get(j).getPropsition()+"\n");
            j++;
            }
            i++;
        }}
        if (qcus.size()!=0){
            System.out.print("  QCUs : ");
        int i=0;
        while (i<qcus.size()){
            System.out.println(i+1 + "- "+qcus.get(i).getQuestion()+"\n");
            int j=0;
            while (j<qcus.get(i).getPropositions().size()){
                System.out.println("       * "+qcus.get(i).getPropositions().get(j).getPropsition()+"\n");
                j++;
            }
            i++;
        }}
        if (qos.size()!=0){
            System.out.print("  QOs : ");
        int i=0;
        while (i<qos.size()){
            System.out.println(i+1 + "- "+qos.get(i).getQuestion()+"\n");
            i++;
    }}
    }
    public void visualizeQuizApprenant(){
        System.out.println("         "+this.nom+"\n"+"date d'ouverture : "+this.date_ouv+"\n"+"date d'expiration : "+this.date_exp+"\n");
        if (this.qcms.size()!=0){
        System.out.print("  QCMs : ");
        int i=0;
        int l=i;
        while (i<this.qcms.size()){
            ArrayList<Reponse> reponses=new ArrayList<Reponse>();
            l++;
            System.out.println(l + "- "+this.qcms.get(i).getQuestion()+"\n");
            int j=0;
            while (j<this.qcms.get(i).getPropositions().size()){
                System.out.println("        "+j+" * "+this.qcms.get(i).getPropositions().get(j).getPropsition()+"\n");
                j++;
            }
            if (this.qcms.get(i).getReponses().size()==0) {
                System.out.print("    -> Choisissez la/les bonne(s) r�ponse(s) : ");
                Scanner sc = new Scanner(System.in);
                String integers = sc.nextLine();
                String[] parts = integers.split(" ");
                //conversion en int, extraire la proposition et l mettre en reponse en string
                int[] array = new int[parts.length];
                int k = 0;
                while (k < array.length) {
                    array[k] = Integer.parseInt(parts[k]);
                    Reponse reponse = new Reponse(this.qcms.get(i).getPropositions().get(array[k]).getPropsition());
                    reponses.add(reponse);
                    k++;
                }
                this.qcms.get(i).setReponses(reponses);
            }else {
                int p=0;
                System.out.print(" Les r�ponses introduites : ");
                while (p<this.qcms.get(i).getReponses().size()){
                    System.out.print(p+" - "+this.qcms.get(i).getReponses().get(p).getReponse()+" ");
                    p++;
                }
            }
            i++;
        }}
        if (this.qcus.size()!=0){
        System.out.print("  QCUs : ");
        int i=0;
        int k=i;
        while (i<this.qcus.size()){
            k++;
            System.out.println(k + "- "+this.qcus.get(i).getQuestion()+"\n");
            int j=0;
            while (j<this.qcus.get(i).getPropositions().size()){
                System.out.println("       "+j+" * "+this.qcus.get(i).getPropositions().get(j).getPropsition()+"\n");
                j++;
            }
            if (this.qcus.get(i).getReponse().getReponse()==null){
                System.out.print("    -> Choisissez la bonne r�ponse : ");
                Scanner sc = new Scanner(System.in);
                int choix = sc.nextInt();
                Reponse reponse = new Reponse(this.qcus.get(i).getPropositions().get(choix).getPropsition());
                this.qcus.get(i).setReponse(reponse);
            }else {
                System.out.println("La r�ponse : "+this.qcus.get(i).getReponse().getReponse());
            }
            i++;
        }}
        if (this.qos.size()!=0){
        System.out.print("  QOs : ");
        int i=0;
        int k=i;
        while (i<this.qos.size()){
            k++;
            System.out.println(k + "- "+this.qos.get(i).getQuestion()+"\n");
            if (this.qos.get(i).getReponse().getReponse()==null) {
                System.out.print("    -> La r�ponse : ");
                Scanner sc = new Scanner(System.in);
                String rep=sc.next();
                Reponse reponse=new Reponse(rep);
                this.qos.get(i).setReponse(reponse);
        }else {
            System.out.println("La r�ponse : "+this.qos.get(i).getReponse().getReponse());
        }
            i++;
    }}}
    public Quiz(String var1,LocalDate var2,LocalDate var3,ArrayList<QCM> var4,ArrayList<QCU> var5,ArrayList<QO> var6 ){
        this.nom=var1;
        this.date_ouv=var2;
        this.date_exp=var3;
        this.qcms=var4;
        this.qcus=var5;
        this.qos=var6;
        this.accomplished=false;
        this.authorised=true;
        this.setNb_questions();
    }

    public Quiz(){

    }

    public static void main(String [] args)
    {
        LocalDate date_d=LocalDate.of(2019,01,02);
        LocalDate date_f=LocalDate.of(2019,01,03);
        ArrayList<QCM> qcmArrayList=new ArrayList<QCM>();
        ArrayList<QCU> qcuArrayList=new ArrayList<QCU>();
        ArrayList<QO> qoArrayList=new ArrayList<QO>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir la question :");
        String str = sc.nextLine();
        System.out.println("Veuillez saisir une proposition :");
        String str2 = sc.nextLine();
        Boolean aBoolean = true;
        Proposition proposition=new Proposition(str2,aBoolean);
        ArrayList<Proposition> propositionArrayList=new ArrayList<Proposition>();
        propositionArrayList.add(proposition);
        QCM qcm=new QCM(propositionArrayList);

        System.out.println("Veuillez saisir une reponse :");
        String stro = sc.nextLine();
        Reponse reponse=new Reponse(stro);
        ArrayList<Reponse> reponses=new ArrayList<Reponse>();
        reponses.add(reponse);
        qcm.setReponses(reponses);
        qcm.setQuestion(str);
        qcmArrayList.add(qcm);
        Quiz quiz=new Quiz("test",date_d,date_f,qcmArrayList,qcuArrayList,qoArrayList);
        quiz.visualizeQuiz();

    }
}
