package handong.vacation.myapplication.object;

import java.io.Serializable;

public class Memo implements Serializable {
    private int id;
    private String title;
    private String content;

    public Memo(int id){
        this.id = id;
        title = "";
        content = "";
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void reset(){
        title = "";
        content = "";
    }

    public boolean isEmpty(){
        return (title.equals("") && content.equals(""));
    }
}
