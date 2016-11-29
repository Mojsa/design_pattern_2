/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.util.Arrays;

/**
 *
 * @author Mario
 */
public class RankingNorm {

    private static String[] agencije = {
        "SSI", "NAUI", "BSAC", "CMAS"
    };

    private static final int[] ssiRekreacijski = {1, 1, 1, 1, 1, 1};
    private static final int[] nauiRekreacijski = {0, 1, 1, 1, 1, 0};
    private static final int[] bsacRekreacijski = {0, 1, 1, 1, 1, 0};
    private static final int[] cmasRekreacijski = {0, 1, 1, 1, 1, 0};

    private static final int[] ssiProfi = {1, 1, 1, 1, 1, 1, 1, 1};
    private static final int[] nauiProfi = {1, 0, 1, 1, 0, 0, 0, 1};
    private static final int[] bsacProfi = {1, 0, 1, 1, 1, 0, 0, 0};
    private static final int[] cmasProfi = {1, 0, 1, 1, 0, 0, 0, 0};

    private static final String[] razineRekreacijske = {
        "R0", "R1", "R2", "R3", "R4", "R5"
    };

    private static final String[] razineProfi = {
        "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8"
    };

    public static String normalizirajRazinu(String agencija, String razina) {
        String r = "-";
        if (provjeriPodatke(agencija, razina)) {
            boolean rekreacijski = false;
            int zadnjaRazina = 0;
            int brojRazine;
            if (razina.substring(0, 1).equals("R")) {
                rekreacijski = true;
                brojRazine = Integer.parseInt(razina.substring(1, 2));
            } else {
                brojRazine = Integer.parseInt(razina.substring(1, 2)) - 1;
            }
            if (agencija.equals(agencije[0])) {
                return razina;
            } else if (agencija.equals(agencije[1])) {
                if (rekreacijski && brojRazine != 0) {
                    //rekreacijski 
                    if (ssiRekreacijski[brojRazine] == nauiRekreacijski[brojRazine]) {
                        return razina;
                    }
                    for (int j = 0; j < ssiRekreacijski.length; j++) {
                        if (ssiRekreacijski[j] == nauiRekreacijski[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineRekreacijske[zadnjaRazina];

                } else if (!rekreacijski) {

                    if (ssiProfi[brojRazine] == nauiProfi[brojRazine]) {
                        return razineProfi[brojRazine];
                    }
                    for (int j = 0; j < ssiProfi.length; j++) {
                        if (ssiProfi[j] == nauiProfi[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineProfi[zadnjaRazina];
                    //profesionalni
                }

            } else if (agencija.equals(agencije[2])) {
                if (rekreacijski && brojRazine != 0) {
                    //rekreacijski 
                    if (ssiRekreacijski[brojRazine] == bsacRekreacijski[brojRazine]) {
                        return razina;
                    }
                    for (int j = 0; j < ssiRekreacijski.length; j++) {
                        if (ssiRekreacijski[j] == bsacRekreacijski[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineRekreacijske[zadnjaRazina];

                } else if (!rekreacijski) {
                    if (ssiProfi[brojRazine] == bsacProfi[brojRazine]) {
                        return razineProfi[brojRazine];
                    }
                    for (int j = 0; j < ssiProfi.length; j++) {
                        if (ssiProfi[j] == bsacProfi[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineProfi[zadnjaRazina];
                    //profesionalni
                }

            } else if (agencija.equals(agencije[3])) {
                if (rekreacijski && brojRazine != 0) {
                    //rekreacijski 
                    if (ssiRekreacijski[brojRazine] == cmasRekreacijski[brojRazine]) {
                        return razina;
                    }
                    for (int j = 0; j < ssiRekreacijski.length; j++) {
                        if (ssiRekreacijski[j] == cmasRekreacijski[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineRekreacijske[zadnjaRazina];

                } else if (!rekreacijski) {
                    if (ssiProfi[brojRazine] == cmasProfi[brojRazine]) {
                        return razineProfi[brojRazine];
                    }
                    for (int j = 0; j < ssiProfi.length; j++) {
                        if (ssiProfi[j] == cmasProfi[j] && j <= brojRazine) {
                            zadnjaRazina = j;
                        }
                    }
                    return razineProfi[zadnjaRazina];
                    //profesionalni
                }
            }
        }

        return r;
    }

    private static boolean provjeriPodatke(String agencija, String razina) {
        return Arrays.asList(agencije).contains(agencija) && (Arrays.asList(razineRekreacijske).contains(razina)
                || Arrays.asList(razineProfi).contains(razina));
    }

    public static String[] getAgencije() {
        return agencije;
    }

    public static String[] getRazineRekreacijske() {
        return razineRekreacijske;
    }

    public static String[] getRazineProfi() {
        return razineProfi;
    }

}
