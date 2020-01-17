package sample.P1;
public class QO extends Question {
    private String mot_cle;
    private Reponse reponse=new Reponse();

    public void setMot_cle(String mot_cle) {
        this.mot_cle = mot_cle;
    }

    public String getMot_cle() {
        return mot_cle;
    }
    public QO(String question,String mot_cle){
        this.question=question;
        this.mot_cle=mot_cle;
    }

    @Override
    public float evaluateQuestion(int nb_question_quiz) {
    float score=0;
    String[] res=this.reponse.getReponse().split(" ");
        int i=0;Boolean stop=false;
        while (i<res.length && !stop){
            if (mot_cle.equals(res[i])){
                stop=true;

                score = 100/ (float) nb_question_quiz;
            }  else i++;
        }
        return score;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public Reponse getReponse() {
        return reponse;
    }
    public QO(String mot_cle){this.mot_cle=mot_cle;}
    public static void main(String [] args){
        QO qo=new QO("hello","mot");
        Reponse reponse=new Reponse("c'est un mot cle");
        qo.setReponse(reponse);
        System.out.println(qo.evaluateQuestion(10));
    }

}
