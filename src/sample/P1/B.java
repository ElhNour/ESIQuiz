package sample.P1;

import sample.P2.Quiz;
import sample.P2.QuizAccompli;

public class B extends QuizAccompli {
    public B(){

    }
    public static void main(String[] args){
        Quiz P4=new Quiz();
       QuizAccompli p6=new QuizAccompli();
        C p=new C();
P4=p;
p6=(C) P4;
        System.out.println(p6.getClass());

    }
}
