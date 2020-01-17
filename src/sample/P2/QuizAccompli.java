package sample.P2;

public class QuizAccompli  extends Quiz{
    private float pourcentage;

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public float getPourcentage() {
        return pourcentage;
    }
    public float calculPourcentage(){
        float score=0;
        for (int i=0;i<this.qcms.size();i++){
            score +=this.qcms.get(i).evaluateQuestion(this.getNb_questions());
        }
        score=score*(100/(float) this.getNb_questions());
        for (int i=0;i<this.qcus.size();i++){
            score +=this.qcus.get(i).evaluateQuestion(this.getNb_questions());
        }
        for (int i=0;i<this.qos.size();i++){
            score +=this.qos.get(i).evaluateQuestion(this.getNb_questions());
        }
        this.setPourcentage(score);
        this.tauxAcc=100;
        this.accomplished=true;
        return score;
    }

}
