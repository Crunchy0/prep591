public class Pair<F extends Comparable, S extends Comparable> implements Comparable<Pair<F, S>> {
    private final F f;
    private final S s;

    public Pair(F f, S s){
        this.f = f;
        this.s = s;
    }

    public F first(){
        return this.f;
    }
    public S second(){
        return this.s;
    }

    public int compareTo(Pair<F, S> other){
        if(this.first().compareTo(other.first()) > 0){
            return 1;
        }
        if (this.first().compareTo(other.first()) < 0){
            return -1;
        }
        if(this.second().compareTo(other.second()) > 0){
            return 1;
        }
        if(this.second().compareTo(other.second()) < 0){
            return -1;
        }
        return 0;
    }
}
