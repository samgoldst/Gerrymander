public class Model {
    private Election election;
    private IModelToView mtv;

    public Model(IModelToView mtv){
        this.mtv = mtv;
    }

    public void createElection(int numDistricts, int numTowns, String[] parties, int maxSupporters, String wantedWinner, double wantedMargin){
        Election election = new Election(numDistricts, numTowns, parties, maxSupporters, wantedWinner, wantedMargin);
        this.election = election;
    }

    public void gerrymander(int iterations, int craziness){
        for(int i = 0; i < 100; i ++) {
            this.election.updateMap(iterations / 100, i, craziness);
            System.out.print("\r" + (i + 1) + "% complete");
            mtv.sendPercentage(i);
        }
        mtv.sendStats(this.election.toString());
    }

    public String getStats(){
        return this.election.toString();
    }
}