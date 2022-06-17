package grade;

import java.util.Map;
import java.util.TreeMap;

public class SteelGrade {

    private String name;
    Map<String, Interval> intervalMap = new TreeMap<String, Interval>();

    public SteelGrade(String name) {
        this.name = name;
    }

    public void add(String element, Interval range){
        intervalMap.put(element,range);
    }

    public Interval getValue(String element){
        return intervalMap.get(element);
    }

    /*Stahlanalyse: C, 0.01; Si, 0.1
    Stahlgruppe: C, 0.0, 0.04; Si,0.0, 0.15 Stahlgruppe: C, 0.09, 0.18; Si,0.0, 0.15
    Bei UltraLowCarbon liefert contains(...) true , da Kohlenstoff und Silizium
    innerhalb des Intervall liegen, bei MediumCarbon jedoch false , da der
    Kohlenstoffwert zu gering ist*/

    public boolean contains(SteelAnalysis steelAnalysis){
        for (String s : intervalMap.keySet()) {
            if(!(steelAnalysis.getValue(s) >= intervalMap.get(s).getUntergrenze() && steelAnalysis.getValue(s) <= intervalMap.get(s).getObergrenze())){
                return false;
            }
        }
        return true;
    }

    /*
    Ni, [-Infinity, Infinity] ... Nickel hat weder nach oben noch nach unten eine Grenze. Cr, [-
    Infinity, 0.04] ... Chrom hat keine untere Grenze aber eine obere Grenze von 0.04 C,
    [0.01, 0.02] ... Kohlenstoff muss zwischen 0.01 und 0.02 liegen V, [0.001, Infinity] ...
    Vanadium muss mindestens 0.001 sein, aber nach oben unbeschränkt.
    Eine Methode zur Berechnung der Einschränkungen getNumberOfRestrictions() welche
    die Anzahl von Restriktionen zurück liefert. (Die Anzahl der Restriktionen entspricht der
    Anzahl der Intervallgrenzen die nicht +/- Infinity sind.)*/

    public int getNumberOfRestrictions(){
        int numberOfRestrictions = 0;

        for (String s : intervalMap.keySet()) {
            if(intervalMap.get(s).getUntergrenze() != Double.NEGATIVE_INFINITY){
                numberOfRestrictions ++;
            }
            if(intervalMap.get(s).getObergrenze() != Double.POSITIVE_INFINITY){
                numberOfRestrictions ++;
            }
        }
        return numberOfRestrictions;
    }
}
