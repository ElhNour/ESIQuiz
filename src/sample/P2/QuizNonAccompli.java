package sample.P2;

import sample.P1.QO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class QuizNonAccompli extends Quiz {
    public QuizNonAccompli(String var1, LocalDate var2, LocalDate var3, ArrayList<QCM> var4, ArrayList<QCU> var5, ArrayList<QO> var6 ){
        this.nom=var1;
        this.date_ouv=var2;
        this.date_exp=var3;
        this.qcms=var4;
        this.qcus=var5;
        this.qos=var6;
        this.accomplished=false;
        this.authorised=true;
    }
    //le taux d'accomplissement change pour un quiz non accompli, un quiz accompli a un taux d'acc de 100%
    public float calculTauxAcc() // c'est le nombre de questions déjà faites / le nombre de questions total du quiz
    {
        int nb_ques_faites=0;
        Iterator<QCM> qcmIterator=this.qcms.iterator();
        while (qcmIterator.hasNext()){
            if (qcmIterator.next().getReponses().get(0)!=null)//ie, si l'utilisateur a déjà introduit au moins une réponse pour cette question
                nb_ques_faites++;
        }
        Iterator<QCU> qcuIterator=this.qcus.iterator();
        while (qcuIterator.hasNext()){
            if (qcuIterator.next().getReponse()!=null)//ie, si l'utilisateur a déjà introduit une réponse pour cette question
                nb_ques_faites++;
        }
        Iterator<QO> qoIterator=this.qos.iterator();
        while (qoIterator.hasNext()){
            if (qoIterator.next().getReponse()!=null)//ie, si l'utilisateur a déjà introduit une réponse pour cette question
                nb_ques_faites++;
        }
        return nb_ques_faites/(float) this.getNb_questions();
    }

}
