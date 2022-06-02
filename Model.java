public class Model {
    private Election election;

    public void createElection(int numDistricts, int numTowns, String[] parties, int maxSupporters, String wantedWinner, double wantedMargin){
        Election election = new Election(numDistricts, numTowns, parties, maxSupporters, wantedWinner, wantedMargin);
        this.election = election;
    }

    public void gerrymander(int iterations){
        for(int i = 0; i < 100; i ++) {
            this.election.updateMap(iterations / 100, i);
            System.out.print("\r" + (i + 1) + "% complete");
        }
    }

    public String getStats(){
        return this.election.toString();
    }
}