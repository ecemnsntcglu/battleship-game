import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Play play = new Play();
        play.oyunaGiris();
        if (Play.kimleOynaniyor() == 1) {
            Bilgisayar bilgisayar = new Bilgisayar();
            System.out.println("Bilgisayarla oynayacaksınız.");
            Play.tahtayiDoldur(Bilgisayar.oyuncu.tahta);
            Play.tahtayiYazdir(Bilgisayar.oyuncu.tahta);
            System.out.println("Gemileri rasgele yerleştirmek için 'R' yi kendin yerleştirmek için 'k' yi tuşla...");
            char kim = input.next().toLowerCase().charAt(0);
            if (kim == 'r') {
                play.gemileriYerlestirB(Bilgisayar.oyuncu.tahta);
                Play.tahtayiYazdir(Bilgisayar.oyuncu.tahta);
            } else {
                Bilgisayar.oyuncu.gemileriYerlestir();
            }
            Play.tahtayiDoldur(Bilgisayar.bilgisayarTahta);
            bilgisayar.gemileriYerlestirB(Bilgisayar.bilgisayarTahta);
            System.out.println("bilgisayar gemileri yerleştirildi");
            for (int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n");
            }
            Play.tahtayiYazdir(Bilgisayar.oyuncu.tahta);
            System.out.println("----------OYUN BAŞLADI!!----------");
            Play.tahtayiDoldur(Bilgisayar.oyuncu.hamleTahta);
            Play.tahtayiDoldur(Bilgisayar.hamleTahta);

            while (Bilgisayar.oyuncu.hamleSayac> 0) {
                System.out.println(Bilgisayar.oyuncu.oyuncuAdi + " sırası:\n");
                System.out.println("HAMLE SAYISI: " + Bilgisayar.oyuncu.hamleSayac);
                Play.tahtayiYazdir(Bilgisayar.oyuncu.hamleTahta);
                if (!Bilgisayar.oyuncu.vur(Bilgisayar.bilgisayarTahta)) {
                    Thread.sleep(2000);
                    System.out.println("Bilgisayarın sırası:\n");
                    if (!Bilgisayar.atisKontrolBilgisayar(Bilgisayar.oyuncu.tahta)) {
                        Play.tahtayiYazdir(Bilgisayar.hamleTahta);
                        Thread.sleep(2000);
                        System.out.println(Bilgisayar.oyuncu.oyuncuAdi + ": " + Bilgisayar.oyuncu.sayac);
                        System.out.println("Bilgisyar : " + Bilgisayar.sayac);
                    } else {
                        System.out.println("TÜM GEMİLER BATIRILDII!");
                        System.out.println("BİLGİSAYAR KAZANDI!!");
                        break;
                    }
                } else {
                    System.out.println("TÜM GEMİLER BATIRILDII!");
                    System.out.println(Bilgisayar.oyuncu.oyuncuAdi + " KAZANDI!!");
                    break;
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
            System.out.println("Gemileri rasgele yerleşyirmek için 'R' yi kendin yerleştirmek için 'K' yi tuşla...");
            char kim = input.next().toLowerCase().charAt(0);
            if (kim == 'r') {
                play.gemileriYerlestirB(oyuncu1.tahta);
                Play.tahtayiYazdir(oyuncu1.tahta);
            }
            else{
                oyuncu1.gemileriYerlestir();
            }

            System.out.println("\nSıra arkadaşınızda,herhangi bir tuşa bas!!");
            input.next();
            for (int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n");
            }
            System.out.println("2. Oyuncu NICKNAME: ");
            ad = input.next();
            Oyuncu oyuncu2 = new Oyuncu(ad);
            Play.tahtayiDoldur(oyuncu2.tahta);
            Play.tahtayiYazdir(oyuncu2.tahta);
            System.out.println("Gemileri rasgele yerleşyirmek için 'R' yi kendin yerleştirmek için 'K' yi tuşla...");
            kim = input.next().toLowerCase().charAt(0);
            if (kim == 'r') {
                play.gemileriYerlestirB(oyuncu2.tahta);
                Play.tahtayiYazdir(oyuncu2.tahta);
            }else{
                oyuncu2.gemileriYerlestir();
            }
            for (int i = 0; i < 50; ++i) {
                System.out.println("\n\n\n\n");
            }

            System.out.println("----------OYUN BAŞLADI!!----------");
            Play.tahtayiDoldur(oyuncu1.hamleTahta);
            Play.tahtayiDoldur(oyuncu2.hamleTahta);
            while (oyuncu2.hamleSayac> 0) {
                System.out.println(oyuncu1.oyuncuAdi + " adlı oyuncu sırası:\n");
                if (!oyuncu1.vur(oyuncu2.tahta)) {
                    System.out.println(oyuncu2.oyuncuAdi + " adlı oyuncu sırası:\n");
                    if (!oyuncu2.vur(oyuncu1.tahta)) {
                        System.out.println(oyuncu1.oyuncuAdi + ": " + oyuncu1.sayac);
                        System.out.println(oyuncu2.oyuncuAdi + ": " + oyuncu2.sayac);
                    } else {
                        System.out.println(oyuncu2.oyuncuAdi + " KAZANDI!!");
                        break;
                    }
                } else {
                    System.out.println(oyuncu1.oyuncuAdi + " KAZANDI!!");
                    break;
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
