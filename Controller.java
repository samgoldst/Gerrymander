public class Controller {
    public static void main(String[] args){
        Model model = new Model();
        model.createElection(50, 10000, new String[]{"red", "blue"}, 100, "blue", .5);
        model.gerrymander(10000);
        System.out.println(model.getStats());
    }
}
