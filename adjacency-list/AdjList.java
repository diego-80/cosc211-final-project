public class AdjList {
    public List[] edges;
    
    public AdjList(int numV){
        edges=new List[numV];
        for(int i=0; i<edges.length; i++) {
            edges[i] = new List();
        }
    }
    
    public void addEdge(int i, int j){
        edges[i].add(j);
    }
    
    public int[] mostCommonEdge(){
        int maxSoFar=0;
        int[] edge=new int[3];
        for(int i=0; i<edges.length-1; i++){
            Node listMax=edges[i].mostCommonOut();
            if (listMax.count>maxSoFar) {
                maxSoFar = listMax.count;
                edge[0]=i;
                edge[1]=listMax.val;
                edge[2]=maxSoFar;
            }
        }
        return edge;
    }
    
    public int[][] mostCommon3() {
        int[][] top3=new int[3][3];
        for (int i = 0; i < edges.length-1; i++) {
            Node listMax=edges[i].mostCommonOut();
            if (listMax.count>top3[2][2]) {
                top3[2][0]=i;
                top3[2][1]=listMax.val;
                top3[2][2]=listMax.count;
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
        return top3;
    }
    
    public void print(){
        for (int i=0; i<edges.length; i++){
            System.out.print(i+" ");
            edges[i].print();
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
        int numOut=edges[startFrom].numOut;
        double key=Math.random();
        Node active=edges[startFrom].first;
        double count=0.0;
        while(true){
            count+=((double)active.count)/((double)numOut);
            if(count<=key)
                active=active.next;
            else
                break;
        }
        int toRet = active.val;
        return toRet;
    }
    
}