import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class District {
    private HashSet<Town> towns = new HashSet<>();
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

    public int getPopulation(){
        int totalPop = 0;
        for(Town town : towns) {
            totalPop += town.getPopulation();
        }
        return totalPop;
    }

    public String getWinner() {
        HashMap<String, Integer> totals = new HashMap<>();
        for(Town town : towns) {
            for(Map.Entry<String, Integer> votes : town.getDemographics().entrySet()){
                totals.put(votes.getKey(), totals.getOrDefault(votes.getKey(), 0) + votes.getValue());
            }
        }
        int max = 0;
        String maxName = "";
        for(String party : totals.keySet()){
            if(totals.get(party) > max){
                maxName = party;
                max = totals.get(party);
            }
        }
        return maxName;
    }

    public HashMap<String, Integer> getDemographics(){
        HashMap<String, Integer> totals = new HashMap<>();
        for (Town town : towns) {
            for (Map.Entry<String, Integer> votes : town.getDemographics().entrySet()){
                totals.put(votes.getKey(), totals.getOrDefault(votes.getKey(), 0) + votes.getValue());
            }
        }
        return totals;
    }

    @Override
    public String toString(){
        String output = "";
        output += this.id;
        output += getDemographics().toString();
        return output;
    }
}