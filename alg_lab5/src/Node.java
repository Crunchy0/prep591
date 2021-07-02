import java.util.HashMap;

public class Node {
    public HashMap<Character, Node> next = new HashMap<>();
    public final Node parent;
    public Node link = null;
    public Node tLink = null;
    public final Character toParent;
    public boolean terminal = false;
    public int ptnNum;

    public Node(Node pr, Character toPr){
        this.toParent = toPr;
        if(pr == null || toPr == 0) {
            this.parent = this;
            this.link = this;
        }
        else{
            this.parent = pr;
        }
    }

    public Node getLink(Character c){
        if(this.next.containsKey(c)){
            return this.next.get(c);
        }
        if(this.link == this){
            return this;
        }
        return this.link.getLink(c);
    }
}
