import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Play play = new Play();
        int hamleSayisi = 20;
        play.oyunaGiris();
        if (Play.kimleOynaniyor() == 1) {
            System.out.println("Bilgisayarla oynayacaksınız.");
            Bilgisayar bilgisayar = new Bilgisayar();
            Play.tahtayiDoldur(Bilgisayar.bilgisayarTahta);
            bilgisayar.gemileriYerlestir();
            Play.tahtayiDoldur(Bilgisayar.oyuncu.tahta);
            Play.tahtayiYazdir(Bilgisayar.oyuncu.tahta);
            Bilgisayar.oyuncu.gemileriYerlestir();

            for(int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n\n");
            }

            System.out.println("----------OYUN BAŞLADI!!----------");
            Play.tahtayiDoldur(Bilgisayar.oyuncu.hamleTahta);
            Play.tahtayiDoldur(Bilgisayar.hamleTahta);

            while(hamleSayisi > 0) {
                System.out.println(Bilgisayar.oyuncu.oyuncuAdi + " sırası:\n");
                System.out.println("HAMLE SAYISI: "+hamleSayisi);
                Play.tahtayiYazdir(Bilgisayar.oyuncu.hamleTahta);
                if (!Bilgisayar.oyuncu.vur(Bilgisayar.bilgisayarTahta)) {
                    System.out.println("Bilgisayarın sırası:\n");
                    if (!Bilgisayar.atisKontrolBilgisayar(Bilgisayar.oyuncu.tahta)) {
                        Play.tahtayiYazdir(Bilgisayar.hamleTahta);
                        System.out.println(Bilgisayar.oyuncu.oyuncuAdi + ": " + Bilgisayar.oyuncu.sayac);
                        System.out.println("Bilgisyar : " + Bilgisayar.sayac);
                        --hamleSayisi;
                    } else {
                        System.out.println("TÜM GEMİLER BATIRILDII!");
                        System.out.println("BİLGİSAYAR KAZANDI!!");
                    }
                } else {
                    System.out.println("TÜM GEMİLER BATIRILDII!");
                    System.out.println(Bilgisayar.oyuncu.oyuncuAdi + " KAZANDI!!");
                }
            }

            System.out.println("HAMLE BİTTİ.");
            if (Bilgisayar.oyuncu.sayac > Bilgisayar.sayac) {
                System.out.println(Bilgisayar.oyuncu.oyuncuAdi + " KAZANDI!!");
            } else if (Bilgisayar.sayac > Bilgisayar.oyuncu.sayac) {
                System.out.println("BİLGİSAYAR KAZANDI!!");
            } else {
                System.out.println("Berabere :)");
            }
        } else {
            System.out.println("Arkadaşınızla oynayacaksınız.");
            System.out.println("1. Oyuncu NICKNAME: ");
            String ad = input.next();
            Oyuncu oyuncu1 = new Oyuncu(ad);
            Play.tahtayiDoldur(oyuncu1.tahta);
            Play.tahtayiYazdir(oyuncu1.tahta);
            oyuncu1.gemileriYerlestir();
            System.out.println("Sıra arkadaşınızda!!");

            for(int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n\n");
            }

            System.out.println("2. Oyuncu NICKNAME: ");
            ad = input.next();
            Oyuncu oyuncu2 = new Oyuncu(ad);
            Play.tahtayiDoldur(oyuncu2.tahta);
            Play.tahtayiYazdir(oyuncu2.tahta);
            oyuncu2.gemileriYerlestir();

            for(int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n\n");
            }

            System.out.println("----------OYUN BAŞLADI!!----------");
            Play.tahtayiDoldur(oyuncu1.hamleTahta);
            Play.tahtayiDoldur(oyuncu2.hamleTahta);

            while(hamleSayisi > 0) {
                System.out.println(oyuncu2.oyuncuAdi + "adlı oyuncu sırası:\n");
                if (!oyuncu1.vur(oyuncu2.tahta)) {
                    System.out.println(oyuncu2.oyuncuAdi + "adlı oyuncu sırası:\n");
                    if (!oyuncu2.vur(oyuncu1.tahta)) {
                        System.out.println(oyuncu1.oyuncuAdi + ": " + oyuncu1.sayac);
                        System.out.println(oyuncu2.oyuncuAdi + ": " + oyuncu2.sayac);
                        --hamleSayisi;
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
