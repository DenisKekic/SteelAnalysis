package grade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteelAnalysis {
    private String name;
    private Map<String,Double> legierungselementeMesswerteMap = new HashMap<>();

    public SteelAnalysis() {

    }

    public void add(String element, double value){
        legierungselementeMesswerteMap.put(element, value);
    }

    public double getValue(String element){
        return legierungselementeMesswerteMap.get(element);
    }

    public static SteelAnalysis create(String s){
        SteelAnalysis steelAnalysis = new SteelAnalysis();

        String[] input = s.split(" ");
        String[] element = new String[(input.length-1)/2];
        String[] value = new String[(input.length-1)/2];

        int x = 0;
        int y = 0;
        for(int i = 0; i <= input.length-1; i++){

            if(i == 0){
                steelAnalysis.name = input[i];
            }
            else if(input[i].contains(",")){
                input[i] = input[i].replace(",", "");
                element[x] = input[i];
                x++;
            }
            else if((input[i].contains(";") || (input[i] == input[input.length-1]))){
                input[i] = input[i].replace(";", "");
                value[y] = input[i];
                y++;
            }
        }

        for (int j = 0; j < element.length-1; j++){
            steelAnalysis.legierungselementeMesswerteMap.put(element[j], Double.valueOf(value[j]));
        }

        return steelAnalysis;
    }

    // <C001> C, 0.01; Si, 0.1; Mn, 0.4; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015
    @Override
    public String toString() {
        String line = name;
        for (String s : legierungselementeMesswerteMap.keySet()) {
            line = line.concat(" " + s + ", " + getValue(s) + "; ");
        }
        return line;
    }

    public SteelGrade findBestMatchingSteelGrade(List<SteelGrade> stahlgruppen){
        SteelGrade steelGrade = null;
        int numberOfRestrictions = 0;

        for (SteelGrade grade : stahlgruppen) {
            if(grade.contains(SteelAnalysis.this)){
                if(grade.getNumberOfRestrictions() > numberOfRestrictions){
                    numberOfRestrictions = grade.getNumberOfRestrictions();
                    steelGrade = grade;
                }
            }
        }
        return steelGrade;
    }
}
