package com.example.lab13englishdictionary;
public class Modeclass {
    String question;
    String ans;
    String oA;
    String oB;
    String oC;
    String oD;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getoA() {
        return oA;
    }

    public void setoA(String oA) {
        this.oA = oA;
    }

    public String getoB() {
        return oB;
    }

    public void setoB(String oB) {
        this.oB = oB;
    }

    public String getoC() {
        return oC;
    }

    public void setoC(String oC) {
        this.oC = oC;
    }

    public String getoD() {
        return oD;
    }

    public void setoD(String oD) {
        this.oD = oD;
    }

    public Modeclass(String question, String ans, String oA, String oB, String oC, String oD) {
        this.question = question;
        this.ans = ans;
        this.oA = oA;
        this.oB = oB;
        this.oC = oC;
        this.oD = oD;
    }

    public Modeclass(){

    }
}
