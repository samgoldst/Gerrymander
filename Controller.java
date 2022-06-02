public class Controller {
    public static void main(String[] args){
        Model model = new Model();
        model.createElection(50, 10000, new String[]{"red", "blue"}, 100, "blue", .8);
        model.gerrymander(1000000);
        System.out.println("\n" + model.getStats());
    }
}
