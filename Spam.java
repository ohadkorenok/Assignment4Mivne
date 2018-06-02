public class Spam {
    private String word;
    private int percent;

    public Spam(String word,int percent){
        if(percent<0 | percent> 100 | word==null)
            throw new RuntimeException();
        this.word=word;
        this.percent=percent;
    }
    public String getWord(){
        return this.word;
    }
    public int getPercent(){
        return percent;
    }
}
