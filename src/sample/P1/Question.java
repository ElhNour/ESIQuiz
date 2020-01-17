package sample.P1;

public abstract class Question {
    protected String question;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
    public abstract float evaluateQuestion(int nb_question_quiz);
}
