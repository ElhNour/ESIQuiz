package sample.P2;

import sample.P1.Proposition;
import sample.P1.Question;
import sample.P1.Reponse;
import java.util.ArrayList;

public class QCM extends Question {
    private ArrayList<Proposition> propositions;
    private ArrayList<Reponse> reponses=new ArrayList<Reponse>();

   @Override
    public float evaluateQuestion(int nb_question_quiz) {
       float score; float score1=0,score2=0;
    for (int i=0;i<propositions.size();i++){//on v�rifie pour chaque proposition si elle existe parmis les r�ponses de l'apprenant, si oui => v�rifier si
        // elle est juste
        Boolean stop=false;
        int j=0;
        while (j<reponses.size()&& !stop){
            if (propositions.get(i).getPropsition().equals(reponses.get(j).getReponse())){// proposition choisie par l'apprenant donc c une reponse
              // on v�rifie si elle est juste
                if (propositions.get(i).getVal_verite()){//si la proposition est juste donc la r�ponse est correcte
                    score1 +=1/(float) propositions.size();// 1/P telque P est le nombre de propositions contenues dans cette QCM
                }
                else{ //la proposition est fausse et l'apprenant l'a choisie
                    score1 -=1/(float) propositions.size();
               }

                stop=true;
            }else //la proposition !=reponse donc on avance dans l'Arraylist des reponses
            j++;
        }
        if (!stop)//si on sort de la boucle avec stop==false,ie, la proposition "i" n'existe pas parmis les r�ponses de l'apprenant
        {
            if (propositions.get(i).getVal_verite()){// ne pas choisir une r�ponse correcte
                score2 -=1/(float) propositions.size();
            }
            else //ne pas choisir une reponse incorrecte
            {
                score2 +=1/(float) propositions.size();
            }
        }

    }
    score=(score1+score2);
    if (score<0) score=0;
    return score;
    }

    public void setReponses(ArrayList<Reponse> reponses) {
        this.reponses = reponses;
    }

    public ArrayList<Reponse> getReponses() {
        return this.reponses;
    }

    public void setPropositions(ArrayList<Proposition> propositions) {
        this.propositions = propositions;
    }

    public ArrayList<Proposition> getPropositions() {
        return this.propositions;
    }
    public QCM(ArrayList<Proposition> var1){
        this.propositions=var1;
    }
    public QCM(){}
}
