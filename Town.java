import java.util.ArrayList;
import java.util.HashMap;

public class Town {
    private HashMap<String, Integer> demographics;
    private int district;
    private int population;

    public Town(HashMap<String, Integer> demographics, int district){
        this.demographics = demographics;
        this.district = district;
        int sum = 0;
        for(int voters : this.demographics.values()){
            sum += voters;
        }
        this.population = sum;
    }

    public int getPopulation(){
        return this.population;
    }
}