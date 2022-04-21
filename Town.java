import java.util.ArrayList;
import java.util.HashMap;

public class Town {
    private HashMap<String, Integer> demographics;
    private int population;

    public Town(HashMap<String, Integer> demographics){
        this.demographics = new HashMap<>(demographics);
        int sum = 0;
        for(int voters : this.demographics.values()){
            sum += voters;
        }
        this.population = sum;
    }

    public int getPopulation(){
        return this.population;
    }

    public HashMap<String, Integer> getDemographics(){
        return new HashMap<>(this.demographics);
    }

    @Override
    public String toString(){
        return this.demographics.toString();
    }
}