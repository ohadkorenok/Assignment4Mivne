public class Word {
    private String word;
    private int key;
    public Word(String word){
        if(word==null)
            throw new RuntimeException();
        this.word=word;
        generateKey();
    }
    public void generateKey(){
        int key=0;
        for(int i=0;i<word.length();i++)
            key+=word.charAt(i);
        this.key=key;
    }
    public int getKey(){return key;}
    public String getWord(){return word;}
}
