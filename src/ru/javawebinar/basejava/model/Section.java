package ru.javawebinar.basejava.model;

public abstract class Section<C>{
    private C content;
    public Section(C content){
        this.content = content;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }
}
