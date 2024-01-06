import java.util.Random;

public class Bilgisayar extends Play {
    static Oyuncu oyuncu = new Oyuncu("KULLANICI");
    static int sayac = 0;
    static boolean kazandiMi = false;
    static String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    static String[][] bilgisayarTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];

    public Bilgisayar() {
    }

    public void gemileriYerlestir() {
        Random rndGemi = new Random();
        int[] gemiBoyutlari1 = Play.gemiBoyutlari;
        int uzunluk = gemiBoyutlari1.length;

        for (int gemiBoyutu : gemiBoyutlari1) {
            for (int k = 1; k <= (5 - gemiBoyutu); k++) {

                int randomSatir;
                int randomSutun;
                boolean gemiYatayMi;
                do {
                    randomSatir = rndGemi.nextInt(Play.tahtaBuyuklugu);
                    randomSutun = rndGemi.nextInt(Play.tahtaBuyuklugu);
                    gemiYatayMi = rndGemi.nextBoolean();
                } while (!Play.gemininKonumuDogruMu(gemiYatayMi, randomSatir, randomSutun, gemiBoyutu, bilgisayarTahta));

                yerlestir(gemiYatayMi, randomSatir, randomSutun, bilgisayarTahta, gemiBoyutu);
            }

        }

    }

    public static boolean atisKontrolBilgisayar(String[][] kullanici1Tahta) {
        Random random = new Random();
        int hamleSatir = random.nextInt(0, Play.tahtaBuyuklugu);
        int hamleSutun = random.nextInt(0, Play.tahtaBuyuklugu);
        if (!hamleTahta[hamleSatir][hamleSutun].equals("X ") && !hamleTahta[hamleSatir][hamleSutun].equals(". ")) {
            switch (kullanici1Tahta[hamleSatir][hamleSutun]) {
                case "0 ":
                case ". ":
                    System.out.println("Bilgisayar isabetsiz atış yaptı.");
                    hamleTahta[hamleSatir][hamleSutun] = ". ";
                    break;
                case "1 ":
                    System.out.println("Bilgisayar isabetli atış yaptı.DENİZALTINIZ BATIRILDI!");
                    cevreIsaret(hamleSutun,hamleSutun,hamleSatir,kullanici1Tahta,true);
                    ++sayac;
                    hamleTahta[hamleSatir][hamleSutun] = "X ";
                    atisKontrolBilgisayar(kullanici1Tahta);
                    break;
                case "2 ":
                    System.out.println("Bilgisayar isabetli atış yaptı.MAYIN GEMİNİZ VURULDU!");
                    bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, kullanici1Tahta, 0, 0);
                    break;
                case "3 ":
                    System.out.println("Bilgisayar isabetli atış yaptı.KRUVAZÖR VURULDU!");
                    bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, kullanici1Tahta, 0, 0);
                    break;
                case "4 ":
                    System.out.println("Bilgisayar isabetli atış yaptı.AMİRAL VURULDU!");
                    bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, kullanici1Tahta, 0, 0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + kullanici1Tahta[hamleSatir][hamleSutun]);
            }
        } else {
            atisKontrolBilgisayar(kullanici1Tahta);
        }

        return kazandiMi;
    }

    public static void bilgisayarHamleAlgoritmasi(int hamleSatir, int hamleSutun, String[][] kullanici1Tahta, int hamleYonu, int vurulanBirim) {
        int yeniHamleYonu;
        if (hamleYonu == 0) {
            Random random = new Random();
            yeniHamleYonu = random.nextInt(1, 5);
        } else {
            yeniHamleYonu = hamleYonu;
        }

        try {
            switch (yeniHamleYonu) {
                case 1:
                    while (hamleSutun < Play.tahtaBuyuklugu && !kullanici1Tahta[hamleSatir][hamleSutun].equals("0 ")) {
                        hamleTahta[hamleSatir][hamleSutun] = "X ";
                        ++hamleSutun;
                        ++vurulanBirim;
                        ++sayac;
                    }

                    bilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim, yeniHamleYonu);
                    break;
                case 2:
                    while (hamleSatir < Play.tahtaBuyuklugu && !kullanici1Tahta[hamleSatir][hamleSutun].equals("0 ")) {
                        hamleTahta[hamleSatir][hamleSutun] = "X ";
                        --hamleSatir;
                        ++vurulanBirim;
                        ++sayac;
                    }

                    bilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim, yeniHamleYonu);
                    break;
                case 3:
                    while (hamleSutun < Play.tahtaBuyuklugu && !kullanici1Tahta[hamleSatir][hamleSutun].equals("0 ")) {
                        hamleTahta[hamleSatir][hamleSutun] = "X ";
                        --hamleSutun;
                        ++vurulanBirim;
                        ++sayac;
                    }

                    bilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim, yeniHamleYonu);
                    break;
                case 4:
                    while (hamleSatir < Play.tahtaBuyuklugu && !kullanici1Tahta[hamleSatir][hamleSutun].equals("0 ")) {
                        hamleTahta[hamleSatir][hamleSutun] = "X ";
                        ++hamleSatir;
                        ++vurulanBirim;
                        ++sayac;
                    }

                    bilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim, yeniHamleYonu);
            }

            hamleSatir = Math.max(0, Math.min(hamleSatir, kullanici1Tahta.length - 1));
            hamleSutun = Math.max(0, Math.min(hamleSutun, kullanici1Tahta[0].length - 1));
        } catch (ArrayIndexOutOfBoundsException var7) {
            System.out.println("Tahta dışına çıkma hatası: " + var7.getMessage());
        }

    }

    public static void bilgisayarHamleSonucu(int hamleSatir, int hamleSutun, String[][] kullanici1Tahta, int vurulanBirim, int hamleYonu) {
        if (vurulanBirim == 1) {
            System.out.println("Bilgisayarın 2. atışı başarısızlıkla sonuçlandı.");
            gemiKalanKismiVur(hamleSatir, hamleSutun, kullanici1Tahta, hamleYonu, 1);
        } else if (vurulanBirim == 2) {
            switch (kullanici1Tahta[hamleSatir][hamleSutun]) {
                case "2 ":
                    System.out.println("Bilgisayar isabetli atış yaptı.MAYIN GEMİNİZ BATIRILDI!");
                    System.out.println("Hamle sırası tekrar bilgisayarın.");
                    atisKontrolBilgisayar(kullanici1Tahta);
                    break;
                case "3 ":
                    System.out.println("Bilgisayar KRUVAZÖR'ÜN 2 birimini vurdu!");
                    System.out.println("3. atış başarısızlıkla sonuçlandı.");
                    gemiKalanKismiVur(hamleSatir, hamleSutun, kullanici1Tahta, hamleYonu, 2);
                    break;
                case "4 ":
                    System.out.println("Bilgisayar AMİRAL'İN 2 birimini vurdu!");
                    System.out.println("3. atış başarısızlıkla sonuçlandı.");
                    gemiKalanKismiVur(hamleSatir, hamleSutun, kullanici1Tahta, hamleYonu, 2);
            }
        } else if (vurulanBirim == 3) {
            switch (kullanici1Tahta[hamleSatir][hamleSutun]) {
                case "3 ":
                    System.out.println("Bilgisayar 2 isabetli atış yaptı.KRUVAZÖR BATIRILDI!");
                    System.out.println("Hamle sırası tekrar bilgisayarın.");
                    atisKontrolBilgisayar(kullanici1Tahta);
                    break;
                case "4 ":
                    System.out.println("Bilgisayar AMİRAL'İN 3 birimini vurdu!");
                    System.out.println("4. atış başarısızlıkla sonuçlandı.");
                    gemiKalanKismiVur(hamleSatir, hamleSutun, kullanici1Tahta, hamleYonu, 3);
            }
        } else if (vurulanBirim == 4 && kullanici1Tahta[hamleSatir][hamleSutun].equals("4 ")) {
            System.out.println("Bilgisayar 3 isabetli atış yaptı.AMİRAL BATIRILDI!");
            System.out.println("Hamle sırası tekrar bilgisayarın.");
            atisKontrolBilgisayar(kullanici1Tahta);
        }

    }

    public static void gemiKalanKismiVur(int hamleSatir, int hamleSutun, String[][] karsiTahta, int hamleYonu, int vurulanBirim) {
        oyuncu.vur(bilgisayarTahta);
        --vurulanBirim;
        switch (hamleYonu) {
            case 1:
                bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, karsiTahta, 3, vurulanBirim);
                break;
            case 2:
                bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, karsiTahta, 4, vurulanBirim);
                break;
            case 3:
                bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, karsiTahta, 1, vurulanBirim);
                break;
            case 4:
                bilgisayarHamleAlgoritmasi(hamleSatir, hamleSutun, karsiTahta, 2, vurulanBirim);
        }

    }

    public static void cevreIsaret(int bas, int bit, int sbt, String[][] karsiTahta, boolean gemiYatay) {
        int cevre;
        if (gemiYatay) {
            for (cevre = bas; cevre <= bit; ++cevre) {
                if (sbt + 1 < tahtaBuyuklugu - 1 && karsiTahta[sbt + 1][cevre].equals(". ")) {
                    hamleTahta[sbt + 1][cevre] = ". ";
                }

                if (sbt > 0 && karsiTahta[sbt - 1][cevre].equals(". ")) {
                    hamleTahta[sbt - 1][cevre] = ". ";
                }

                if (cevre + 1 < tahtaBuyuklugu - 1 && karsiTahta[sbt][cevre + 1].equals(". ")) {
                    hamleTahta[sbt][cevre + 1] = ". ";
                }

                if (cevre > 0 && karsiTahta[sbt][cevre - 1].equals(". ")) {
                    hamleTahta[sbt][cevre - 1] = ". ";
                }
            }
        } else {
            for (cevre = bas; cevre <= bit; ++cevre) {
                if (cevre + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre + 1][sbt] == ". ") {
                    hamleTahta[cevre + 1][sbt] = ". ";
                }

                if (cevre > 0 && karsiTahta[cevre - 1][sbt] == ". ") {
                    hamleTahta[cevre - 1][sbt] = ". ";
                }

                if (sbt + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre][sbt + 1] == ". ") {
                    hamleTahta[cevre][sbt + 1] = ". ";
                }

                if (sbt > 0 && karsiTahta[cevre][sbt - 1].equals(". ")) {
                    hamleTahta[cevre][sbt - 1] = ". ";
                }
            }
        }

    }

}
