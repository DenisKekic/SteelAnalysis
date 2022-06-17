import grade.Interval;
import grade.SteelAnalysis;
import grade.SteelGrade;
import org.junit.Before;
import org.junit.*;

import static org.junit.Assert.*;

public class TestSteel {
    /* Erstellen Sie Unit Tests zu der obigen Methode contains(SteelAnalysis sa) und testen
    Sie diese mit mindestens 2 Legierungselementen und 3 verschiedenen Bereichen (siehe
    Teilaufgabe 1.2).
    */

    SteelGrade steelGrade1 = new SteelGrade("steelGrade1");
    SteelGrade steelGrade2 = new SteelGrade("steelGrade2");
    SteelGrade steelGrade3 = new SteelGrade("steelGrade3");
    SteelGrade steelGrade4 = new SteelGrade("steelGrade4");
    SteelGrade steelGrade5 = new SteelGrade("steelGrade4");

    Interval interval1;
    Interval interval2;
    Interval interval3;
    Interval interval4;
    Interval interval5;
    Interval interval6;
    Interval interval7;
    Interval interval8;


    @Before
    public void first(){
        /*Stahlgruppe: C, 0.0, 0.04; Si,0.0, 0.15 hat 4 Restriktionen. Stahlgruppe: C, 0.0, 0.04;
        Si,0.0, Infinity hat 3 Restriktionen Stahlgruppe: C, 0.0, 0.04; Si,-Infinity, Infinity hat 2
        Restriktionen
         */

        interval1 = new Interval(0.0, 0.4);
        interval2 = new Interval(0.0, 0.15);
        interval3 = new Interval(0.0, Double.POSITIVE_INFINITY);
        interval4 = new Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
        interval5 = new Interval(0, 1);
        interval6 = new Interval(0.01, 0.2);
        interval7 = new Interval(Double.NEGATIVE_INFINITY, 0.2);
        interval8 = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        steelGrade1.add("C", interval1);
        steelGrade1.add("Si", interval2);

        steelGrade2.add("C", interval1);
        steelGrade2.add("Si", interval3);

        steelGrade3.add("C", interval1);
        steelGrade3.add("Si", interval4);

        steelGrade4.add("C", interval5);
        steelGrade4.add("Si", interval6);
        steelGrade4.add("Mn", interval1);
        steelGrade4.add("P", interval8);
        steelGrade4.add("S", interval7);

        steelGrade5.add("C", interval1);
        steelGrade5.add("Si", interval8);
    }

    @Test
    public void testContains(){

        assertEquals(true, steelGrade1.contains(SteelAnalysis.create("<C001> C, 0.01; Si, 0.1; Mn, 0.4; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015")));
        assertEquals(true, steelGrade2.contains(SteelAnalysis.create("<C001> C, 0.01; Si, 0.1; Mn, 0.4; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015")));
        assertEquals(false, steelGrade3.contains(SteelAnalysis.create("<C001> C, 0.01; Si, 0.1; Mn, 0.4; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015")));
        assertEquals(true, steelGrade4.contains(SteelAnalysis.create("<C001> C, 0.01; Si, 0.1; Mn, 0.4; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015")));
        assertEquals(false, steelGrade4.contains(SteelAnalysis.create("<C001> C, 0.01; Si, 0.3; Mn, 0.5; P, 0.0005; S, 0.002; Cr, 0.05; Ni, 0.015")));

    }

    @Test
    public void testBestSteel(){

        assertEquals(4, steelGrade1.getNumberOfRestrictions());
        assertEquals(3, steelGrade2.getNumberOfRestrictions());
        assertEquals(2, steelGrade5.getNumberOfRestrictions());
        assertEquals(7, steelGrade4.getNumberOfRestrictions());

    }
}
