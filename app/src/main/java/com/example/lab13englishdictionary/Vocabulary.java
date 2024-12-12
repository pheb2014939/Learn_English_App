package com.example.lab13englishdictionary;

public class Vocabulary {

    String ImageUrl;
    String ex, def, vocab;

    public Vocabulary() {

    }

    public Vocabulary(String imageUrl, String ex, String def, String vocab) {
        ImageUrl = imageUrl;
        this.ex = ex;
        this.def = def;
        this.vocab = vocab;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }
}
