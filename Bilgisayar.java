import java.util.*;
import java.util.Random;

public class Bilgisayar extends Play {
    String[][] bilgisayarTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];

    public void gemileriYerlestir() {
        Random rndGemi = new Random();
        for (int gemiBoyutu : Play.gemiBoyutlari) {
            int randomSatir, randomSutun;
            boolean gemiYatayMi;
            do {
                randomSatir = rndGemi.nextInt(Play.tahtaBuyuklugu);
                randomSutun = rndGemi.nextInt(Play.tahtaBuyuklugu);
                gemiYatayMi = rndGemi.nextBoolean();
            } while (!Play.gemininKonumuDogruMu(gemiYatayMi, randomSatir, randomSutun, gemiBoyutu, bilgisayarTahta));

            for (int j = 0; j < gemiBoyutu; j++) {
                if (gemiYatayMi)
                    bilgisayarTahta[randomSatir][randomSutun + j] = Integer.toString(gemiBoyutu) + " ";
                else
                    bilgisayarTahta[randomSatir + j][randomSutun] = Integer.toString(gemiBoyutu) + " ";
            }
        }
    }

    //kullanıcının atışlarını kontrol eden fonksiyon
    public static void AtisKontrolKullanici(String[][] bilgisayarTahta) {
        Scanner input = new Scanner(System.in);
        System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
        int hamleSatir = input.nextInt();
        System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
        int hamleSutun = input.nextInt();
        if (bilgisayarTahta[hamleSatir][hamleSutun] == "0 ") {
            System.out.println("İsabetsiz atış yaptınız!");
        } else if (bilgisayarTahta[hamleSatir][hamleSutun] == "1 ") {
            System.out.println("Tebrikler,Başarılı atış!DENİZALTI BATIRILDI!");
            System.out.println("Hamle sırası tekrar sizin.");
            AtisKontrolKullanici(bilgisayarTahta);
        } else if (bilgisayarTahta[hamleSatir][hamleSutun] == "2 ") {
            System.out.println("Tebrikler,Başarılı atış!MAYIN GEMİSİ VURULDU");
            System.out.println("Hamle sırası tekrar sizin.");
            System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
            int hamleSatirMG = input.nextInt();
            System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
            int hamleSutunMG = input.nextInt();
            if (bilgisayarTahta[hamleSatirMG][hamleSutunMG] == "2 " && ((hamleSatirMG == hamleSatir - 1) || (hamleSatirMG == hamleSatir + 1) || (hamleSutunMG == hamleSutun - 1) || (hamleSutunMG == hamleSutun + 1))) {
                System.out.println("Tebrikler,Başarılı atış!M>AYIN GEMİSİ BATIRILDI!");
                AtisKontrolKullanici(bilgisayarTahta);
            }
        } else if (bilgisayarTahta[hamleSatir][hamleSutun] == "3 ") {
            System.out.println("Tebrikler,Başarılı atış!KRUVAZÖR VURULDU");
            System.out.println("Hamle sırası tekrar sizin.");
            System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
            int hamleSatirK = input.nextInt();
            System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
            int hamleSutunK = input.nextInt();
            if (bilgisayarTahta[hamleSatirK][hamleSutunK] == "3 " && ((hamleSatirK == hamleSatir - 1) || (hamleSatirK == hamleSatir + 1) || (hamleSutunK == hamleSutun - 1) || (hamleSutunK == hamleSutun + 1))) {
                System.out.println("Tebrikler.KRUVAZÖR TEKRAR VURULDU!BATIRMAYA ÇOK YAKINSIN!");
                System.out.println("Hamle sırası tekrar sizin.");
                System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
                int hamleSatirK1 = input.nextInt();
                System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
                int hamleSutunK1 = input.nextInt();
                if (bilgisayarTahta[hamleSatirK1][hamleSutunK1] == "3 " && ((hamleSatirK1 == hamleSatirK - 1) || (hamleSatirK1 == hamleSatirK + 1) || (hamleSutunK1 == hamleSutunK - 1) || (hamleSutunK1 == hamleSutunK + 1))) {
                    System.out.println("Tebrikler.KRUVAZÖR BATIRILDI!");
                    System.out.println("Hmale sırası tekrar sizin.");
                    AtisKontrolKullanici(bilgisayarTahta);
                }
            }
        } else if (bilgisayarTahta[hamleSatir][hamleSutun] == "4 ") {
            System.out.println("Tebrikler,Başarılı atış!AMİRAL VURULDU");
            System.out.println("Hamle sırası tekrar sizin.");
            System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
            int hamleSatirA = input.nextInt();
            System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
            int hamleSutunA = input.nextInt();
            if (bilgisayarTahta[hamleSatirA][hamleSutunA] == "4 " && ((hamleSatirA == hamleSatir - 1) || (hamleSatirA == hamleSatir + 1) || (hamleSutunA == hamleSutun - 1) || (hamleSutunA == hamleSutun + 1))) {
                System.out.println("Tebrikler.AMİRAL TEKRAR VURULDU!BATIRMAYA YAKINSIN!");
                System.out.println("Hamle sırası tekrar sizin.");
                System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
                int hamleSatirA1 = input.nextInt();
                System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
                int hamleSutunA1 = input.nextInt();
                if (bilgisayarTahta[hamleSatirA1][hamleSutunA1] == "4 " && ((hamleSatirA1 == hamleSatirA - 1) || (hamleSatirA1 == hamleSatirA + 1) || (hamleSutunA1 == hamleSutunA - 1) || (hamleSutunA1 == hamleSutunA + 1))) {
                    System.out.println("Tebrikler.AMİRAL TEKRAR VURULDU!BATIRMAYA ÇOK YAKINSIN!");
                    System.out.println("Hamle sırası tekrar sizin.");
                    System.out.println("Atış yapmak istediğiniz birimin satır koordinatlarını giriniz.");
                    int hamleSatirA2 = input.nextInt();
                    System.out.println("Atış yapmak istediğiniz sütun koordinatlarını giriniz.");
                    int hamleSutunA2 = input.nextInt();
                    if (bilgisayarTahta[hamleSatirA2][hamleSutunA2] == "4 " && ((hamleSatirA2 == hamleSatirA1 - 1) || (hamleSatirA2 == hamleSatirA1 + 1) || (hamleSutunA2 == hamleSutunA1 - 1) || (hamleSutunA2 == hamleSutunA1 + 1))) {
                        System.out.println("Tebrikler.AMİRAL BATIRILDI!");
                        System.out.println("Hamle sırası tekrar sizin.");
                        AtisKontrolKullanici(bilgisayarTahta);
                    }
                }
            }
        }
    }

    //bilgisayarın atışlarını kontrol eden fonksiyon
    public void AtisKontrolBilgisayar(String[][] kullanici1Tahta) {
        Random random = new Random();
        int hamleSatir = random.nextInt(0, Play.tahtaBuyuklugu);
        int hamlesutun = random.nextInt(0, Play.tahtaBuyuklugu);
        if (kullanici1Tahta[hamleSatir][hamlesutun] == "0 ") {
            System.out.println("Bilgisayar isabetsiz atış yaptı.");
        } else if (kullanici1Tahta[hamleSatir][hamlesutun] == "1 ") {
            System.out.println("Bilgisayar isabetli atış yaptı.DENİAZLTINIZ BATIRILDI!");
            AtisKontrolBilgisayar(kullanici1Tahta);
        } else if (kullanici1Tahta[hamleSatir][hamlesutun] == "2 ") {
            System.out.println("Bilgisayar isabetli atış yaptı.MAYIN GEMİNİZ VURULDU!");
            BilgisayarHamleAlgoritmasi(hamleSatir, hamlesutun, kullanici1Tahta);
        } else if (kullanici1Tahta[hamleSatir][hamlesutun] == "3 ") {
            System.out.println("Bilgisayar isabetli atış yaptı.KRUVAZÖR VURULDU!");
            BilgisayarHamleAlgoritmasi(hamleSatir, hamlesutun, kullanici1Tahta);
        } else if (kullanici1Tahta[hamleSatir][hamlesutun] == "4 ") {
            System.out.println("Bilgisayar isabetli atış yaptı.AMİRAL VURULDU!");
            BilgisayarHamleAlgoritmasi(hamleSatir, hamlesutun, kullanici1Tahta);
        }
    }

    //başarılı atış sonrası,bilgisayara tekrar yapılacak atış sırasında algoritma kazandıran fonksiyon
    public void BilgisayarHamleAlgoritmasi(int hamleSatir, int hamleSutun, String[][] kullanici1Tahta) {
        Random random = new Random();
        int hamleYonu = random.nextInt(1, 5);
        int vurulanBirim = 0;
        if (hamleYonu == 1) {
            while (kullanici1Tahta[hamleSatir][hamleSutun] != "0 ") {
                hamleSutun++;
                vurulanBirim++;
            }
            BilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim);
        } else if (hamleYonu == 2) {
            while (kullanici1Tahta[hamleSatir][hamleSutun] != "0 ") {
                hamleSatir--;
                vurulanBirim++;
            }
            BilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim);
        } else if (hamleYonu == 3) {
            while (kullanici1Tahta[hamleSatir][hamleSutun] != "0 ") {
                hamleSutun--;
                vurulanBirim++;
            }
            BilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim);
        } else if (hamleYonu == 4) {
            while (kullanici1Tahta[hamleSatir][hamleSutun] != "0 ") {
                hamleSatir++;
                vurulanBirim++;
            }
            BilgisayarHamleSonucu(hamleSatir, hamleSutun, kullanici1Tahta, vurulanBirim);
        }
    }

    //Bilgisayardan random alınan yön sonrası bilgisayarın hamle sonucunu veren fonksiyon
    public void BilgisayarHamleSonucu(int hamleSatir, int hamleSutun, String[][] kullanici1Tahta, int vurulanBirim) {
        if (vurulanBirim == 1) {
            System.out.println("Bilgisayarın 2. atışı başarısızlıkla sonuçlandı.");
        } else if (vurulanBirim == 2 && kullanici1Tahta[hamleSatir][hamleSutun] == "2 ") {
            System.out.println("Bilgisayar isabetli atış yaptı.MAYIN GEMİNİZ BATIRILDI!");
            System.out.println("Hamle sırası tekrar bilgisayarın.");
            AtisKontrolBilgisayar(kullanici1Tahta);
        } else if (vurulanBirim == 2 && kullanici1Tahta[hamleSatir][hamleSutun] == "3 ") {
            System.out.println("Bilgisayar KRUVAZÖR'ÜN 2 birimini vurdu!");
            System.out.println("3. atış başarısızlıkla sonuçlandı.");
        } else if (vurulanBirim == 2 && kullanici1Tahta[hamleSatir][hamleSutun] == "4 ") {
            System.out.println("Bilgisayar AMİRAL'İN 2 birimini vurdu!");
            System.out.println("3. atış başarısızlıkla sonuçlandı.");
        } else if (vurulanBirim == 3 && kullanici1Tahta[hamleSatir][hamleSutun] == "3 ") {
            System.out.println("Bilgisayar 2 isabetli atış yaptı.KRUVAZÖR BATIRILDI!");
            System.out.println("Hamle sırası tekrar bilgisayarın.");
            AtisKontrolBilgisayar(kullanici1Tahta);
        } else if (vurulanBirim == 3 && kullanici1Tahta[hamleSatir][hamleSutun] == "4 ") {
            System.out.println("Bilgisayar AMİRAL'İN 3 birimini vurdu!");
            System.out.println("4. atış başarısızlıkla sonuçlandı.");
            BasarisizBilgisayarHamle(hamleSatir, hamleSutun);
        } else if (vurulanBirim == 4 && kullanici1Tahta[hamleSatir][hamleSutun] == "4 ") {
            System.out.println("Bilgisayar 3 isabetli atış yaptı.AMİRAL BATIRILDI!");
            System.out.println("Hamle sırası tekrar bilgisayarın.");
            AtisKontrolBilgisayar(kullanici1Tahta);
        }
    }


    public void BasarisizBilgisayarHamle(int hamleSatir, int hamleSutun) {
        AtisKontrolKullanici(bilgisayarTahta);

    }

}
