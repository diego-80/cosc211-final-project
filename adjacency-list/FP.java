import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FP {
    public static void main(String[] args){
        //access text
        //read unique words into hash table
        //create array of unique words
        //create graph
            //adj list with list nodes holding next word and count of edge occurence
        //analysis
        String filename;
        if(args.length==0)
            filename="src/text1.txt";
        else
            filename=args[0];
        try {
            FileReader fr = new FileReader(filename);
            Scanner sc = new Scanner(fr);
    
            //Read every word into an array, doubling size when necessary
            int nextSpace=0;
            String[] allWords = new String[200];
            while(sc.hasNext()){
                if(nextSpace+5>=allWords.length){
                    String[] newArray = new String[allWords.length*2];
                    for (int i=0; i<allWords.length; i++)
                        newArray[i]=allWords[i];
                    allWords=newArray;
                }
                String toAdd=sc.next().toLowerCase();
                if(toAdd.endsWith(".")){
                    toAdd=toAdd.replaceAll("[^a-zA-Z]", "");
                    allWords[nextSpace] = toAdd;
                    allWords[nextSpace+1]=".";
                    nextSpace+=2;
                }
                else if(toAdd.endsWith(",")){
                    toAdd=toAdd.replaceAll("[^a-zA-Z]", "");
                    allWords[nextSpace] = toAdd;
                    allWords[nextSpace+1]=",";
                    nextSpace+=2;
                }
                else {
                    toAdd=toAdd.replaceAll("[^a-zA-Z]", "");
                    allWords[nextSpace] = toAdd;
                    ++nextSpace;
                }
            }
            
            //Hash into hash table so there is one copy of each unique word with a count of how many times it appears
            //Keep track of which values were hashed to (i.e. hold a word)
            nextSpace=0;
            int[] hashVals = new int[allWords.length/3];
            for(int i:hashVals)
                i=-1;
            int uniqueWords=0;
            Table hashTable=new Table(allWords.length);
            for (int i = 0; i < allWords.length; i++) {
                if(allWords[i]!=null){
                    if(nextSpace==hashVals.length){
                        int[] newArray = new int[hashVals.length*2];
                        for (int j = 0; j< hashVals.length; j++)
                            newArray[j]= hashVals[j];
                        hashVals=newArray;
                    }
                    int hashedTo=hashTable.add(allWords[i]);
                    if(hashedTo>=0) {
                        hashVals[nextSpace] = hashedTo;
                        ++uniqueWords;
                        ++nextSpace;
                    }
                }
            }
            int[] trimmed = new int[uniqueWords];
            for(int i=0; i<uniqueWords;i++){
                trimmed[i]=hashVals[i];
            }
            hashVals=trimmed;
            
            AdjList graph = new AdjList(hashVals.length);
            int fromHash=-1;
            int toHash=-1;
            for(int i=0; i<allWords.length-1; i++){
                if(allWords[i]!=null&&allWords[i+1]!=null) {
                    for(int j=0; j<hashVals.length; j++){
                        if (hashVals[j] == hashTable.getHash(allWords[i]))
                            fromHash = j;
                        if (hashVals[j] == hashTable.getHash(allWords[i + 1]))
                            toHash = j;
                    }
                    graph.addEdge(fromHash, toHash);
                }
            }
    
            System.out.println("3 most common two-word phrases:");
            int[][] top3 =graph.mostCommon3();
            for(int i=0; i<top3[0].length; i++){
                String word1=hashTable.words[hashVals[top3[i][0]]];
                String word2=hashTable.words[hashVals[top3[i][1]]];
                System.out.println("'"+word1+" "+word2+"' appears "+top3[i][2]+" times.");
            }
    
            System.out.println();
            System.out.println("Computer-generated sentence:");
            int period=0;
            for(int i=0; i<hashVals.length; ++i){
                if(hashTable.words[hashVals[i]].equals(".")){
                    period=i;
                    break;
                }
            }
            int start=graph.probNext(period);
            int[] sentence = graph.sentence(start, period);
            for(int i=0; i<sentence.length; i++){
                System.out.print(hashTable.words[hashVals[sentence[i]]]+" ");
            }
        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found. Exited program.");
            System.exit(0);
        }
    }
}
