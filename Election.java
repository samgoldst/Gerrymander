import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Election {
    private District[] ledger;
    private Town[] towns;
    private String[] parties;

    public Election(int districts, int towns, String[] parties, int maxVoters){
        this.ledger = new District[districts];

        Random rand = new Random();

        for(int i = 0; i < districts; i++){
            ledger[i] = new District(i);
        }

        for(int i = 0; i < towns; i++){
            HashMap<String, Integer> voters = new HashMap<>();
            for(String party : parties){
                voters.put(party, rand.nextInt(maxVoters + 1));
            }
            ledger[rand.nextInt(districts)].add(new Town(voters));
        }
        this.parties = parties.clone();
    }

    public static void main(String[] args){
        Election election = new Election(50, 19495, new String[]{"red", "blue"}, 33804);
        System.out.println(election.popDistribution());
        System.out.println(election.getResult());
        System.out.println(election);
    }

    public District[] getLedger(){
        return this.ledger.clone();
    }

    public Town[] getTowns(){
        return this.towns.clone();
    }

    public double popDistribution(){
        int pops = 0;

        for(District district : this.ledger){
            pops += district.getPopulation();
        }

        double target = pops / (double) this.ledger.length;
        double output = 0;

        for(int i = 0; i < this.ledger.length; i++){
            output += Math.pow(this.ledger[i].getPopulation() - target, 2) / target;
        }
        return output;
    }

    public String toString(){
        String output = "";
        for(District district : this.ledger){
            output += district.toString();
            output += "\n";
        }
        return output;
    }

    public Result getResult(){
        HashMap<String, Integer> wins = new HashMap<>();

        for(District district : this.ledger){
            wins.put(district.getWinner(), wins.getOrDefault(district.getWinner(), 0) + 1);
        }

        int maxWins = 0;
        String winner = null;

        for(Map.Entry<String, Integer> party: wins.entrySet()){
            if(party.getValue() > maxWins){
                maxWins = party.getValue();
                winner = party.getKey();
            }
        }

        return new Result(winner, maxWins / (double)this.ledger.length);
    }

    public record Result(String winner, double percentage){}
}
