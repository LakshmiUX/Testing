

public class Fact {
private String factText;
private String author;
private String factType;

   
    public String getFactText() {
        return factText;
    }

    public void setFactText(String factText) {
        this.factText = factText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

     public String getFactType() {
        return factType;
    }

    public void setFactType(String factType) {
        this.factType = factType;
    }

    @Override
    public String toString() {
        return "fact [fact-text=" + factText + ", author=" + author + ", fact-type=" +
            factType + "]";
    }
}