import java.util.*;

public class District {
    private List<Town> towns = new ArrayList<>();
    private int id;

    public District(int id) {
        this.id = id;
    }

    public int size() {
        return this.towns.size();
    }

    public void add(Town town) {
        this.towns.add(town);
    }

    public void remove(Town town) {
        this.towns.remove(town);
    }

    public Town getRandomTown() {
        Random rand = new Random();
        return this.towns.get(rand.nextInt(this.towns.size()));
    }

    public int getPopulation() {
        int totalPop = 0;
        for (Town town : towns) {
            totalPop += town.getPopulation();
        }
        return totalPop;
    }

    public String getWinner() {
        HashMap<String, Integer> totals = new HashMap<>();
        for (Town town : towns) {
            for (Map.Entry<String, Integer> votes : town.getDemographics().entrySet()) {
                totals.put(votes.getKey(), totals.getOrDefault(votes.getKey(), 0) + votes.getValue());
            }
        }
        int max = 0;
        String maxName = "";
        for (String party : totals.keySet()) {
            if (totals.get(party) > max) {
                maxName = party;
                max = totals.get(party);
            }
        }
        return maxName;
    }

    public HashMap<String, Integer> getDemographics() {
        HashMap<String, Integer> totals = new HashMap<>();
        for (Town town : towns) {
            for (Map.Entry<String, Integer> votes : town.getDemographics().entrySet()) {
                totals.put(votes.getKey(), totals.getOrDefault(votes.getKey(), 0) + votes.getValue());
            }
        }
        return totals;
    }

    @Override
    public String toString() {
        String output = "";
        for(int i : getDemographics().values()){
            output += i + " ";
        }
        output += getPopulation();
        return output;
    }

    public void setTowns(List<Town> towns) {
        this.towns = new ArrayList<>(towns);
    }

    public District copy() {
        District newDistrict = new District(this.id);
        newDistrict.setTowns(this.towns);
        return newDistrict;
    }
}