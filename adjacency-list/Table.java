public class Table {
    public int size;
    public String[] words;
    public int[] wordCount;
    
    public Table(int tableSize) {
        size = tableSize;
        words = new String[size];
        wordCount=new int[size];
    }
    

    public int hash(String s) {
        int hash=0;
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; ++i){
            hash+=(chars[i]/(i+1));
        }
        hash = hash%words.length;
        return hash;
    }
    
    public int add(String s) {
        int hash=hash(s);
        if(words[hash]==null){
            words[hash]=s;
            ++wordCount[hash];
            return hash;
        }
        else if(words[hash].equals(s)){
            ++wordCount[hash];
            return -1;
        }
        else {
            while ((words[hash]!=null)&&(!words[hash].equals(s))) {
                hash=(hash+1)%size;
            }
            words[hash] = s;
            ++wordCount[hash];
            if(wordCount[hash]==1)
                return hash;
            else
                return -1;
        }
        
    }
    
    public int getHash(String s){
        int hash=hash(s);
        while(!words[hash].equals(s)){
            ++hash;
        }
        return hash;
    }
    
}
