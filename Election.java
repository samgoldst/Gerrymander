import java.util.HashMap;
import java.util.Random;

public class Election {
    District[] ledger;
    Town[] towns;

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
    }

    public static void main(String[] args){
        Election election = new Election(5, 10, new String[]{"red", "blue"}, 100);
        System.out.println(election.ledger[0].toString());
    }
}
