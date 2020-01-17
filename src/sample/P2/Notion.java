package sample.P2;
import org.w3c.dom.Node;
import sample.P1.Proposition;
import sample.P1.QO;

import java.util.*;

public class Notion {
    private String notion;
    private ArrayList<QCM> qcms;
    private ArrayList<QCU> qcus;
    private ArrayList<QO> qos;

    public void setNotion(String notion) {
        this.notion = notion;
    }

    public String getNotion() {
        return notion;
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
    public Notion(String var1,ArrayList<QCM> var2,ArrayList<QCU> var3,ArrayList<QO> var4){
        notion=var1;
        qcms=var2;
        qcus=var3;
        qos=var4;
    }
    public static void affichC(Collection<Animal> animals){
        Iterator<Animal> it=animals.iterator();
        while (it.hasNext()){
            Animal a=it.next();
            if (a.nom.charAt(0)=='C') System.out.println(a.nom+" ");
        }
    }

    public static void main (String[] args){
        HashSet<Animal> animals=new HashSet<Animal>();
        Animal a1=new Animal("Cbagra");Animal a2=new Animal("bagra");Animal a3=new Animal("Czarafa");Animal a4=new Animal("asad");Animal a5=new Animal("bagra");
        animals.add(a1);animals.add(a2);animals.add(a3);animals.add(a4);animals.add(a5);
        String s="hello world";
        StringTokenizer tok=new StringTokenizer(s," ");
        System.out.println(tok.countTokens());
        Deque<String> deque=new ArrayDeque<String>();

        Iterator<Animal> it=animals.iterator();
        while (it.hasNext()){
            System.out.println(it.next().nom+" ");
        }
        TreeSet<Animal> animalTreeSet=new TreeSet<>(animals);
        Iterator<Animal> i=animalTreeSet.iterator();
        System.out.println("\n");
        while (i.hasNext()){
            System.out.println(i.next().nom+" ");
        }
        ArrayList<Animal> animalArrayList=new ArrayList<Animal>(animalTreeSet);
        animalArrayList.add(a1);
        animalArrayList.add(a2);
        animalArrayList.add(a3);
        Collections.sort(animalArrayList);
        System.out.println("\n");
        Iterator<Animal> ite=animalArrayList.iterator();
        while (ite.hasNext()){
            System.out.println(ite.next().nom+" ");
        }
        System.out.println("\n");
        Notion.affichC(animals);
        System.out.println("\n");
        Notion.affichC(animalArrayList);


    }

}
class Animal implements Comparable<Animal>{
    String nom;
    public  Animal (String n){
        nom=n;
    }
    public boolean equals(Animal a){
        return this.nom.equals(a.nom);
    }

    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }
    public int compareTo(Animal a){
        return a.nom.compareTo(this.nom);
    }
//HashMap<String,int> hashMap=new HashMap<String, int>();

}
