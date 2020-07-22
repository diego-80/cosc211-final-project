public class AdjMatrix{
    public int[][] theGraph; //theGraph[from][to]
    public int size;
    
    public AdjMatrix(int numV){
        size=numV;
        theGraph=new int[size][size];
    }
    
    public void addEdge(int i, int j){
        ++theGraph[i][j];
    }
    
    public int edgeCount(int i, int j){
        return theGraph[i][j];
    }
    
    public int[] mostCommonEdge() {
        int[] edge = new int[3];
        int maxSoFar = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (theGraph[i][j]>maxSoFar) {
                    maxSoFar = theGraph[i][j];
                    edge[0]=i;
                    edge[1]=j;
                    edge[2]=maxSoFar;
                }
            }
        }
        return edge;
    }
    
    public int[][] mostCommon3() {
        int[][] top3=new int[3][3];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (theGraph[i][j]>top3[2][2]) {
                    top3[2][0]=i;
                    top3[2][1]=j;
                    top3[2][2]=theGraph[i][j];
                    if(top3[2][2]>top3[1][2]){
                        int[] holder=new int[3];
                        for(int k=0; k<holder.length; k++){
                            holder[k]=top3[1][k];
                            top3[1][k]=top3[2][k];
                            top3[2][k]=holder[k];
                        }
                        if(top3[1][2]>top3[0][2]){
                            int[] holder2=new int[3];
                            for(int k=0; k<holder2.length; k++){
                                holder2[k]=top3[0][k];
                                top3[0][k]=top3[1][k];
                                top3[1][k]=holder2[k];
                            }
                        }
                    }
                }
            }
        }
        return top3;
    }
    
    public void print(){
        for(int i=0;i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(theGraph[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public int[] sentence(int startFrom, int endOn){
        int[] sentence = new int[10];
        sentence[0]=startFrom;
        int nextSpace=1;
        while(sentence[nextSpace-1]!=endOn){
            if(nextSpace==sentence.length){
                int[] newArr=new int[sentence.length*2];
                for (int i = 0; i < sentence.length; i++) {
                    newArr[i]= sentence[i];
                }
                sentence =newArr;
            }
            sentence[nextSpace]=this.probNext(sentence[nextSpace-1]);
            ++nextSpace;
            //sentenceLength++;
        }
        int[] toRet=new int[nextSpace];//
        for (int i = 0; i < toRet.length; i++) {
            toRet[i]=sentence[i];
        }
        return toRet;
    }
    
    public int probNext(int startFrom){
        int numOut=0;
        for (int i:theGraph[startFrom])
            numOut+=i;
        double key=Math.random();
        double count =0.0;
        for(int i=0; i<theGraph[startFrom].length; i++) {
            count += ((double) theGraph[startFrom][i]) / ((double) numOut);
            if (count > key)
                return i;
        }
        return -1;
    }
    
}
