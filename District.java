import java.util.HashSet;

public class District {
    private HashSet towns = new HashSet();
    private int id;

    public District(int id){
        this.id = id;
    }

    public void add(Town town){
        this.towns.add(town);
    }

    public void remove(Town town){
        this.towns.remove(town);
    }
}