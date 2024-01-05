import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Play play = new Play();
        int hamleSayisi = 20;
        play.oyunaGiris();
        if (Play.kimleOynaniyor() == 1) {
            System.out.println("Bilgisayarla oynayacaksınız.");
            System.out.println("OYUNCU NICKNAME: ");
            String ad = input.next();
            Oyuncu oyuncu = new Oyuncu(ad);
            Bilgisayar bilgisayar = new Bilgisayar();
            Play.tahtayiDoldur(bilgisayar.bilgisayarTahta);
            bilgisayar.gemileriYerlestir();
            Play.tahtayiDoldur(oyuncu.tahta);
            oyuncu.gemileriYerlestir();
            Play.tahtayiYazdir(oyuncu.tahta);
        } else {
            System.out.println("Arkadaşınızla oynayacaksınız.");
            System.out.println("1. Oyuncu NICKNAME: ");
            String ad = input.next();
            Oyuncu oyuncu1 = new Oyuncu(ad);
            Play.tahtayiDoldur(oyuncu1.tahta);
            Play.tahtayiYazdir(oyuncu1.tahta);
            oyuncu1.gemileriYerlestir();
            System.out.println("Sıra arkadaşınızda!!");
            for (int i = 0; i < 50; i++) {
                System.out.println("\n\n\n\n");
            }
            System.out.println("2. Oyuncu NICKNAME: ");
            ad = input.next();
            Oyuncu oyuncu2 = new Oyuncu(ad);
            Play.tahtayiDoldur(oyuncu2.tahta);
            Play.tahtayiYazdir(oyuncu2.tahta);
            oyuncu2.gemileriYerlestir();
            for (int i = 0; i < 50; i++) {
                System.out.println("\n\n\n\n");
            }
            System.out.println("----------OYUN BAŞLADI!!----------");
            Play.tahtayiDoldur(oyuncu1.hamleTahta);
            Play.tahtayiDoldur(oyuncu2.hamleTahta);
            while (hamleSayisi > 0) {
                System.out.println(oyuncu2.oyuncuAdi + "adlı oyuncu sırası:\n");
                if (!oyuncu1.vur(oyuncu2.tahta)) {
                    System.out.println(oyuncu2.oyuncuAdi + "adlı oyuncu sırası:\n");
                    if (!oyuncu2.vur(oyuncu1.tahta)) {
                        System.out.println(oyuncu1.oyuncuAdi + ": " + oyuncu1.sayac);
                        System.out.println(oyuncu2.oyuncuAdi + ": " + oyuncu2.sayac);
                        hamleSayisi--;
                    } else {
                        System.out.println(oyuncu2.oyuncuAdi + " KAZANDI!!");
                    }
                } else {
                    System.out.println(oyuncu1.oyuncuAdi + " KAZANDI!!");
                }

            }
            System.out.println("OYUN BİTTİ.");
            if (oyuncu1.sayac > oyuncu2.sayac) {
                System.out.println(oyuncu1.oyuncuAdi + " KAZANDI!!");
            } else if (oyuncu2.sayac > oyuncu1.sayac) {
                System.out.println(oyuncu2.oyuncuAdi + " KAZANDI!!");
            } else {
                System.out.println("Berabere :)");
            }

        }


    }
}