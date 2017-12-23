package ru.javawebinar.basejava.model;

public abstract class Section<C>{
    protected String title;
    private C content;
    public Section(String title, C content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }
}
