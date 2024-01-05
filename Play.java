

import java.util.Scanner;

public class Play {
    static int tahtaBuyuklugu;
    int ekle = 0;
    static int[] gemiBoyutlari = {1, 2, 3, 4};
    static Scanner cvp = new Scanner(System.in);
    static boolean kuralDisiMi;

    public void oyunaGiris() {
        System.out.println("Amiral Battı Oyunu'na Hoş Geldiniz! :)");
        System.out.println("Oynamak istediğiniz zorluk seviyesini giriniz: ");
        System.out.println("Kolay - Orta - Zor");
        do {//doğru string girilene kadar döngüde
            kuralDisiMi = false;

            try {
                String cevapSeviye = cvp.next();
                if (cevapSeviye.equalsIgnoreCase("kolay")) {
                    tahtaBuyuklugu = 8; // 8*8lik tahta
                    kuralDisiMi = false;
                } else if (cevapSeviye.equalsIgnoreCase("orta")) {
                    tahtaBuyuklugu = 10; // 10*10lik tahta
                    kuralDisiMi = false;
                } else if (cevapSeviye.equalsIgnoreCase("zor")) {
                    tahtaBuyuklugu = 12; // 12*12 luk tahta
                    kuralDisiMi = false;
                } else {
                    throw new IllegalArgumentException("Lütfen geçerli bir zorluk seviyesi giriniz: ");
                }
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
                kuralDisiMi = true;
            }
        } while (kuralDisiMi);

    }

    public static int kimleOynaniyor() {
        int cevapKimle = 0;

        do {
            kuralDisiMi = false;
            System.out.print("Bilgisayarla oynamak için 1, Arkadaşınızla oynamak için 2'yi giriniz: ");

            try {
                cevapKimle = cvp.nextInt();
                if (cevapKimle == 1) {
                    kuralDisiMi = false;
                } else if (cevapKimle == 2) {
                    kuralDisiMi = false;
                } else {
                    throw new IllegalArgumentException("Geçerli bir sayı girin: ");
                }

            } catch (Exception e) {
                System.out.print("Geçerli bir sayı girin: ");
                cvp.next();
                kuralDisiMi = true;
            }
        } while (kuralDisiMi);
        return cevapKimle;
    }


    public static void tahtayiDoldur(String[][] tahta) {
        for (int i = 0; i < tahtaBuyuklugu; i++) {
            for (int j = 0; j < tahtaBuyuklugu; j++) {
                tahta[i][j] = "0 ";
            }
        }

    }



    /*private boolean kullaniciGemininKonumuDogruMu(boolean gemiYatayMi, int satir, int sutun, int gemiBoyutu, String[][] tahta) {
        if (gemiYatayMi == false) {
            if (satir + gemiBoyutu > tahtaBuyuklugu) {
                return false;
            }

            for (int i = 0; i < gemiBoyutu; i++) {
                if (!"0 ".equals(tahta[satir + i][sutun])) {
                    return false;
                }
            }
        } else {
            if (sutun + gemiBoyutu > tahtaBuyuklugu) {
                return false;
            }
            for (int i = 0; i < gemiBoyutu; i++) {
                if (!"0 ".equals(tahta[satir][sutun + i])) {
                    return false;
                }
            }
        }
        return true;
    }*/

    public static boolean gemininKonumuDogruMu(boolean gemiYatayMi, int satir, int sutun, int gemiBoyutu, String[][] tahta) {
        if (gemiYatayMi == false) {
            if (satir + gemiBoyutu > tahtaBuyuklugu) {
                return false;
            }

            for (int i = 0; i < gemiBoyutu; i++) {
                if (!"0 ".equals(tahta[satir + i][sutun])) {
                    return false;
                }
            }
        } else {
            if (sutun + gemiBoyutu > tahtaBuyuklugu) {
                return false;
            }
            for (int i = 0; i < gemiBoyutu; i++) {
                if (!"0 ".equals(tahta[satir][sutun + i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void tahtayiYazdir(String[][] tahta) {//ortak tahta
        System.out.print("  ");
        for (int ust = 1; ust <= tahtaBuyuklugu; ust++) {
            if(ust>10){
                System.out.print(" " + ust + " ");
            }
            else if(ust==10){
                System.out.print("  " + ust + " ");
            }
            else{
                System.out.print("  " + ust + " ");
            }
        }
        System.out.println();
        for (int satir = 0; satir < tahtaBuyuklugu; satir++) {
            if(satir>8){
                System.out.print((satir+1) + "  ");
            }
            else{
                System.out.print(" " +(satir+1) + "  ");
            }
            for (int sutun = 0; sutun < tahtaBuyuklugu; sutun++) {
                System.out.print(tahta[satir][sutun] + "  ");
            }
            System.out.println();
        }
    }
}

