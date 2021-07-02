import java.util.*;

public class Main {
    public static Node createBohr(ArrayList<String> patterns){
        Node bohr = new Node(null, Character.MIN_VALUE);
        for(String pt: patterns){
            Node cur = bohr;
            for(Character c: pt.toCharArray()){
                if(!cur.next.containsKey(c)){
                    cur.next.put(c, new Node(cur, c));
                }
                cur = cur.next.get(c);
            }
            cur.ptnNum = patterns.indexOf(pt);
            cur.terminal = true;
        }
        return bohr;
    }

    public static void writeLinks(Node bohr){
        Queue<Node> front = new LinkedList<Node>();
        front.add(bohr);

        while(!front.isEmpty()){
            Node cur = front.peek();
            front.poll();
            Node curLink = cur.parent.link;
            final Character key = cur.toParent;
            boolean foundLink = true;

            while(!curLink.next.containsKey(key)){
                if(curLink == bohr){
                    cur.link = bohr;
                    foundLink = false;
                    break;
                }
                curLink = curLink.link;
            }
            if(foundLink){
                curLink = curLink.next.get(key);
                if(cur.parent == bohr){
                    cur.link = bohr;
                }
                else{
                    cur.link = curLink;
                    Node curTLink = curLink;
                    while(curTLink != bohr){
                        if(curTLink.terminal){
                            cur.tLink = curTLink;
                            break;
                        }
                        curTLink = curTLink.link;
                    }
                }
            }
            if(!cur.next.isEmpty()){
                front.addAll(cur.next.values());
            }
        }
    }

    public static void ahoCor(String t, ArrayList<String> patterns, ArrayList<Pair<Integer, Integer>> res){
        Node bohr = createBohr(patterns);
        writeLinks(bohr);
        Node cur = bohr;
        res.clear();

        for(int i = 0; i < t.length(); i++){
            cur = cur.getLink(t.charAt(i));
            Node tLink = cur.tLink;
            while(tLink != null){
                res.add(new Pair<Integer, Integer>(i - patterns.get(tLink.ptnNum).length() + 2, tLink.ptnNum + 1));
                tLink = tLink.tLink;
            }
            if(cur.terminal){
                res.add(new Pair<Integer, Integer>(i - patterns.get(cur.ptnNum).length() + 2, cur.ptnNum + 1));
            }
        }
    }

    public static void main(String args[]){
        String t, p;
        int num = 0;
        ArrayList<String> pts = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        t = in.next();
        num = in.nextInt();
        for(int i = 0; i < num; i++){
            String s = in.next();
            pts.add(s);
        }

        ahoCor(t, pts, res);
        Collections.sort(res);

        for(Pair<Integer, Integer> r : res){
            System.out.print(r.first());
            System.out.print(" ");
            System.out.println(r.second());
        }
    }
}
