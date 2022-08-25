import javax.swing.*;

public class Controller {
    View view;

    public Controller(){
        Model model;

         model = new Model(new IModelToView() {
             @Override
             public void sendPercentage(int num) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        view.changeLabel((num + 1) + "% complete");
                    }
                });
             }

             @Override
             public void sendStats(String stats) {
                 SwingUtilities.invokeLater(new Runnable() {
                     @Override
                     public void run() {
                         view.changeLabel(stats);
                     }
                 });
             }

             @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

         view = new View(new IViewToModel() {
             @Override
             public void startGerryMander(int parties, int iterations, int towns, int districts, int craziness, int maxSupporters, double wantedMargin) {
                 new Thread(new Runnable(){
                     @Override
                     public void run() {
                         String[] partyList = new String[parties];
                         for (int i = 0; i < parties; i ++) {
                             partyList[i] = Integer.toString(i);
                         }
                         model.createElection(districts, towns, partyList, maxSupporters, "0", wantedMargin);
                         model.gerrymander(iterations, craziness);
                         System.out.println("\n" + model.getStats());

                     }
                 }).start();
             }

            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        view.start();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller();
            }
        });
    }
}