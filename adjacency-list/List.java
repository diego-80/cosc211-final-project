public class List {
    public Node first;
    public Node last;
    public int numOut;
    
    public List(){};
    
    public void add(int i){
        Node found = this.find(i);
        if(found!=null){
            ++found.count;
        }
        else {
            Node toAdd = new Node(i);
            if (first == null)
                first = toAdd;
            if (last == null)
                last = toAdd;
            else {
                last.next = toAdd;
                last = toAdd;
            }
        }
        numOut++;
    }
    
    public void print(){
        Node active=first;
        while(active!=null) {
            System.out.print(active.val + "/" + active.count +" ");
            active = active.next;
        }
    }
    
    /*
    public boolean has(int i){
        Node active=first;
        while(active!=null) {
            if(active.val==i)
                return true;
            active = active.next;
        }
        return false;
    }
     */
    
    public Node find(int i){
        Node active=first;
        while(active!=null) {
            if(active.val==i)
                return active;
            active = active.next;
        }
        return null;
    }
    
    public Node mostCommonOut(){
        Node hasMaxSoFar=first;
        Node active=first;
        while(active!=null) {
            if(active.count>hasMaxSoFar.count)
                hasMaxSoFar=active;
            active = active.next;
        }
        return hasMaxSoFar;
    }
}
