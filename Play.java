import java.util.Random;
import java.util.Scanner;

public class Play {

    int tahtaBuyuklugu;
    int[] gemiBoyutlari = {1, 2, 3, 4};
    String[][] kullanici1Tahta;
    String[][] kullanici2Tahta;
    String[][] bilgisayarTahta;
    Scanner cvp = new Scanner(System.in);
    boolean kuralDisiMi;

    public void oyunaGiris() {
        System.out.println("Amiral Battı Oyunu'na Hoş Geldiniz! :)");
        System.out.println("Oynamak istediğiniz zorluk seviyesini giriniz: ");
        System.out.println("Kolay - Orta - Zor");
        do {//doğru string girilene kadar döngüde
            kuralDisiMi = false;

            try {
                String cevapSeviye = cvp.next();
                if (cevapSeviye.equalsIgnoreCase("kolay")) {
                    tahtaBuyuklugu = 6; // 5*5lik tahta
                    kuralDisiMi = false;
                } else if (cevapSeviye.equalsIgnoreCase("orta")) {
                    tahtaBuyuklugu = 8; // 7*7lik tahta
                    kuralDisiMi = false;
                } else if (cevapSeviye.equalsIgnoreCase("zor")) {
                    tahtaBuyuklugu = 11; // 10*10luk tahta
                    kuralDisiMi = false;
                } else {
                    throw new IllegalArgumentException("Lütfen geçerli bir zorluk seviyesi giriniz: ");
                }
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
                kuralDisiMi = true;
            }
        } while (kuralDisiMi);

        kimleOynaniyor();
    }

    public void kimleOynaniyor() {
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

        if (cevapKimle == 1) {
            System.out.println("Bilgisayarla oynayacaksınız.");
            kullanici1Tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
            bilgisayarTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
            tahtayiDoldur(kullanici1Tahta);
            tahtayiDoldur(bilgisayarTahta);
            gemileriYerlestir(bilgisayarTahta);
            tahtayiYazdir(kullanici1Tahta);
            kullaniciGemileriniYerlestir(kullanici1Tahta);
        } else {
            System.out.println("Arkadaşınızla oynayacaksınız.");
            kullanici1Tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
            kullanici2Tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
            tahtayiDoldur(kullanici1Tahta);
            kullaniciGemileriniYerlestir(kullanici1Tahta);
            tahtayiYazdir(kullanici1Tahta);
            System.out.println("Sıra arkadaşınızda!!");
            tahtayiDoldur(kullanici2Tahta);
            kullaniciGemileriniYerlestir(kullanici2Tahta);
            tahtayiYazdir(kullanici2Tahta);
            
        }
    }
    public void tahtayiDoldur(String[][] tahta) {
        for (int i = 1; i < tahtaBuyuklugu; i++) {
            for (int j = 1; j < tahtaBuyuklugu; j++) {
                tahta[i][j] = "0 ";
            }
        }
    }

    public void kullaniciGemileriniYerlestir(String[][] kullaniciTahta) {
        for (int gemiBoyutu : gemiBoyutlari) {
            boolean gecerliGiris = false;

            while (!gecerliGiris) {
                try {
                    System.out.println("Yerleştirmek istediğiniz " + gemiBoyutu + " birimlik geminin konumunu girin.");
                    System.out.print("Satır (1-" + (tahtaBuyuklugu - 1) + "): ");
                    int satir = cvp.nextInt();

                    System.out.print("Sütun (1-" + (tahtaBuyuklugu - 1) + "): ");
                    int sutun = cvp.nextInt();

                    System.out.print("Yatay mı (1) Dikey mi (2): ");
                    int yatayDikeySecim = cvp.nextInt();

                    boolean yatayMi = (yatayDikeySecim == 1);

                    gecerliGiris = kullaniciGemininKonumuDogruMu(yatayMi, satir, sutun, gemiBoyutu, kullaniciTahta);

                    if (gecerliGiris) {
                        for (int j = 0; j < gemiBoyutu; j++) {
                            if (yatayMi)
                                kullaniciTahta[satir][sutun + j] = Integer.toString(gemiBoyutu) + " ";
                            else
                                kullaniciTahta[satir + j][sutun] = Integer.toString(gemiBoyutu) + " ";
                        }
                        tahtayiYazdir(kullaniciTahta);
                    } else {
                        System.out.println("Geçersiz konum, lütfen tekrar deneyin.");
                    }
                } catch (Exception e) {
                    System.out.println("Hata oluştu, lütfen tekrar deneyin.");
                    cvp.next(); // Hatalı girişi temizliyor
                }
            }
        }
    }

    public void gemileriYerlestir(String[][] tahta) {
        Random rndGemi = new Random();
        for (int gemiBoyutu : gemiBoyutlari) {
            int randomSatir, randomSutun;
            boolean gemiYatayMi;
            do {
                randomSatir = rndGemi.nextInt(tahtaBuyuklugu);
                randomSutun = rndGemi.nextInt(tahtaBuyuklugu);
                gemiYatayMi = rndGemi.nextBoolean();
            } while (!gemininKonumuDogruMu(gemiYatayMi, randomSatir, randomSutun, gemiBoyutu, tahta));

            for (int j = 0; j < gemiBoyutu; j++) {
                if (gemiYatayMi)
                    tahta[randomSatir][randomSutun + j] = Integer.toString(gemiBoyutu) + " ";
                else
                    tahta[randomSatir + j][randomSutun] = Integer.toString(gemiBoyutu) + " ";
            }
        }
    }

    private boolean kullaniciGemininKonumuDogruMu(boolean gemiYatayMi, int satir, int sutun, int gemiBoyutu, String[][] tahta) {
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

    private boolean gemininKonumuDogruMu(boolean gemiYatayMi, int satir, int sutun, int gemiBoyutu, String[][] tahta) {
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

    public void tahtayiYazdir(String[][] tahta) {//ortak tahta
        for (int ust = 1; ust < tahtaBuyuklugu; ust++) {
            System.out.print(" " + ust + " ");
        }
        System.out.println();
        for (int satir = 1; satir < tahtaBuyuklugu; satir++) {
            System.out.print(satir + " ");
            for (int sutun = 1; sutun < tahtaBuyuklugu; sutun++) {
                System.out.print(tahta[satir][sutun] + " ");
            }
            System.out.println();
        }
    }
}
