import java.util.HashMap;
import java.util.Random;

public class Election {
    private District[] ledger;
    private Town[] towns;
    private String[] parties;
    private double goalPercentage;
    private String goalWinner;
    private Random rand = new Random();

    public Election(int districts, int towns, String[] parties, int maxVoters, String goalWinner, Double goalPercentage){
        this.ledger = new District[districts];
        this.goalPercentage = goalPercentage;
        this.goalWinner = goalWinner;

        for(int i = 0; i < districts; i++){
            ledger[i] = new District(i);
        }

        for(int i = 0; i < towns; i++){
            HashMap<String, Integer> voters = new HashMap<>();
            for(String party : parties){
                voters.put(party, this.rand.nextInt(maxVoters + 1));
            }
            ledger[this.rand.nextInt(districts)].add(new Town(voters));
        }
        this.parties = parties.clone();
    }

    public void updateMap(int iterations){
        for (int i = 0; i < iterations; i++){
            Election newElection = new Election(this);
            newElection.townMove();
            double newScore = newElection.popDistribution();
            double oldScore = this.popDistribution();
            if(newScore < oldScore) {
                this.ledger = newElection.getLedger();
                System.out.println(newScore + oldScore + "done");
            }
        }
    }
    public void townMove(){
        int districtNum = this.rand.nextInt(this.ledger.length);
        while(this.ledger[districtNum].size() == 0) {
            districtNum = this.rand.nextInt(this.ledger.length);
        }
        Town town = this.ledger[districtNum].getRandomTown();
        this.ledger[districtNum].remove(town);
        this.ledger[this.rand.nextInt(this.ledger.length)].add(town);
    }

    public double getGoalPercentage() {
        return goalPercentage;
    }

    public String getGoalWinner() {
        return goalWinner;
    }

    public String[] getParties() {
        return parties.clone();
    }

    public Election(Election election){
        this.ledger = election.getLedger();
        this.goalPercentage = election.getGoalPercentage();
        this.goalWinner = election.getGoalWinner();
        this.parties = election.getParties();
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

    public double comparePercent(){
        return Math.abs(this.getResult(this.goalWinner).percentage - goalPercentage);
    }

    public double eval(){
        return (this.comparePercent()) * (this.popDistribution());
    }

    public String toString(){
        String output = "";
        for(District district : this.ledger){
            output += district.toString();
            output += "\n";
        }
        return output;
    }

    public Result getResult(String party){
        HashMap<String, Integer> wins = new HashMap<>();

        for(District district : this.ledger){
            wins.put(district.getWinner(), wins.getOrDefault(district.getWinner(), 0) + 1);
        }
        return new Result(party, wins.getOrDefault(party, 0) / (double)this.ledger.length);
    }

    public record Result(String party, double percentage){}

    public static void main(String[] args){
        Election election = new Election(10, 1000, new String[]{"red", "blue"}, 100, "blue", .7);
        for(int i = 0; i < 1000; i ++){
            election.updateMap(1);
            if(election.eval() == 0.0){
                System.out.println(election.toString());
            }
        }
    }
}
