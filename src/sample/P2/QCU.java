package sample.P2;

import sample.P1.Proposition;
import sample.P1.Question;
import sample.P1.Reponse;

import java.math.BigDecimal;
import java.util.ArrayList;

public class QCU extends Question {
    private ArrayList<Proposition> propositions=new ArrayList<Proposition>();
    private Reponse reponse=new Reponse();

    @Override
    public float evaluateQuestion(int nb_question_quiz) {
        float score=0;
        int i = 0;
        Boolean stop = false;
        while ((i < propositions.size()) && (!stop)) {
            if (propositions.get(i).getVal_verite()) {
                if (propositions.get(i).getPropsition().equals(this.reponse.getReponse())) {

                    score = 100/(float) nb_question_quiz;
                }
                stop = true;
            } else i++;
        }
        return score;
    }
    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public Reponse getReponse() {
        return this.reponse;
    }

    public void setPropositions(ArrayList<Proposition> propositions) {
        this.propositions = propositions;
    }

    public ArrayList<Proposition> getPropositions() {
        return propositions;
    }
    public QCU(ArrayList<Proposition> propositions){this.propositions=propositions;}
}
