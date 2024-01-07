import java.util.*;

public class Oyuncu extends Play {
    public String oyuncuAdi;
    String[][] tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    boolean kazandiMi = false;
    int sayac = 0;
    int vurus = 0;
    int ilkVurulanSatir;
    int ilkVurulanSutun;
    int sbt;
    int baslangic = 0;//cevreyi isaretlemek için
    int bitis = 0;//cevreyi isaretlemek için
    boolean gemiYatay = false;
    int hamleSayac=20;
    Oyuncu(String ad) {
        this.oyuncuAdi = ad;
    }

    public void gemileriYerlestir() {
        int satir, sutun;
        boolean dongu;
        for (int i = 4; i > 0; i--) {
            int gemiBoyutu = gemiBoyutlari[i - 1];
            switch (i) {
                case 1:
                    System.out.println("DENİZALTI (1 BİRİM,4 ADET) YERLEŞTİRİN:");
                    break;
                case 2:
                    System.out.println("MAYINGEMİLERİ (2 BİRİM,3 ADET) YERLEŞTİRİN:");
                    break;
                case 3:
                    System.out.println("KRUVAZÖRLERİ (3 BİRİM,2 ADET) YERLEŞTİRİN:");
                    break;
                case 4:
                    System.out.println("AMİRAL GEMİSİNİ (4 BİRİM) YERLEŞTİRİN:");
                    break;
            }
            for (int k = 1; k <= (5 - gemiBoyutu); k++) {//gemi sayısı
                System.out.println("\n" + k + ". GEMİ\n");//kaçıncı gemi
                do {
                    try {
                        System.out.println("Başlangıç koordinatlarını Girin:");
                        System.out.println("Satır: ");
                        satir = cvp.nextInt() - 1;
                        System.out.println("Sütun: ");
                        sutun = cvp.nextInt() - 1;
                        char yon;
                        //Girilen değerler tahta boyutu içerisinde mi?+
                        if (satir >= tahtaBuyuklugu || sutun >= tahtaBuyuklugu) {
                            throw new IllegalArgumentException("LÜTFEN GEÇERLİ BİR KOORDİNAT GİR!");
                        } else if (gemiBoyutu == 1) {
                            yon = 'd';
                        } else {
                            System.out.println("Gemi yönünü belirle:\n Yatay için 'Y'\n Dikey için 'D'");
                            yon = cvp.next().toLowerCase().charAt(0);
                        }
                        switch (yon) {
                            case 'd':
                                if (!gemininKonumuDogruMu(false, satir, sutun, gemiBoyutu, tahta)) {
                                    throw new IllegalArgumentException("Yeterli alan yok.Başka bir yön seç!");
                                } else {
                                    yerlestir(false, satir, sutun, tahta, gemiBoyutu);
                                    Play.tahtayiYazdir(tahta);
                                }
                                break;
                            case 'y':
                                if (!gemininKonumuDogruMu(true, satir, sutun, gemiBoyutu, tahta)) {
                                    throw new IllegalArgumentException("Yeterli alan yok.Başka bir nokta seç!");
                                } else {
                                    yerlestir(true, satir, sutun, tahta, gemiBoyutu);
                                    Play.tahtayiYazdir(tahta);
                                }
                                break;
                            default:
                                throw new IllegalArgumentException("GEÇERLİ BİR HARF GİR!");
                        }
                        dongu = false;

                    } catch (IllegalArgumentException e) {
                        dongu = true;
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException a) {
                        System.out.println("Geçerli bir değer gir!");
                        cvp.next();
                        dongu = true;
                    }
                }
                while (dongu);
            }
        }
    }

    public boolean vur(String[][] karsitahta) {
        hamleSayac--;
        Scanner hamle = new Scanner(System.in);
        System.out.println("Vurmak İstediğin Koordinatı Gir!");
        int turn = 1;
        while (turn == 1) {
            try {
                System.out.println("Satır:");
                int satir = hamle.nextInt() - 1;
                System.out.println("Sutun:");
                int sutun = hamle.nextInt() - 1;
                if (satir > tahtaBuyuklugu - 1 || sutun > tahtaBuyuklugu - 1) {
                    throw new IllegalArgumentException("LÜTFEN GEÇERLİ BİR KOORDİNAT GİR!");
                } else if (hamleTahta[satir][sutun].equals("X ") || hamleTahta[satir][sutun].equals(". ")) {
                    throw new IllegalArgumentException("BU NOKTAYI ZATEN VURDUNUZ!");
                }
                switch (vurus) {
                    case 0:
                        if (!karsitahta[satir][sutun].equals("0 ")&& !karsitahta[satir][sutun].equals(". ")) {
                            ilkVurulanSutun = sutun;
                            ilkVurulanSatir = satir;
                            baslangic=ilkVurulanSatir;
                            bitis=ilkVurulanSatir;
                            sbt=sutun;
                            vurus++;
                        }
                        break;
                    case 1:
                        if (satir == ilkVurulanSatir && Math.abs(sutun - ilkVurulanSutun) == 1) {
                            if (!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")) {
                                gemiYatay = true;
                                sbt = satir;
                                baslangic = Math.min(sutun, ilkVurulanSutun);
                                bitis = Math.max(sutun, ilkVurulanSutun);
                                vurus++;
                            }
                        }
                        else if (sutun == ilkVurulanSutun && Math.abs(satir - ilkVurulanSatir) == 1) {
                            if (!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")) {
                                gemiYatay = false;
                                sbt = sutun;
                                baslangic = Math.min(satir, ilkVurulanSatir);
                                bitis = Math.max(satir, ilkVurulanSatir);
                                vurus++;
                            }
                        } else {
                            throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");
                        }
                        break;
                    default://vurus>1
                        if (gemiYatay&&Math.abs((sutun - baslangic)) != 1 && Math.abs((sutun - bitis)) != 1) {
                            throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");
                        }
                        else if (!gemiYatay&&Math.abs((satir - baslangic)) != 1 && Math.abs((satir - bitis)) != 1) {
                            throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");

                        } else if(!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")){
                            vurus++;
                            if(gemiYatay){
                                baslangic = Math.min(sutun, baslangic);
                                bitis = Math.max(sutun, bitis);
                            }else{
                                baslangic = Math.min(satir, baslangic);
                                bitis = Math.max(satir,bitis);
                            }
                        }
                }
                if (!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")) {
                    System.out.println("BAŞARILI ATIŞ!!");
                    System.out.println("Geminin "+vurus+". birimi vuruldu.");
                    sayac++;
                    hamleTahta[satir][sutun] = "X ";
                    if(karsitahta[satir][sutun].equals(vurus +" ")){
                        vurus=0;
                        System.out.println("GEMİ BATIRILDI!!");
                        cevreIsaret(baslangic,bitis,sbt,karsitahta,gemiYatay);
                    }
                    karsitahta[satir][sutun] = "X ";
                    tahtayiYazdir(hamleTahta);
                    if (sayac == 14) {
                        System.out.println("Oyun Bitti ");
                        kazandiMi = true;
                        turn = 0;
                    } else {
                        System.out.println("SIRA TEKRAR SENDE.");
                    }
                } else {
                    System.out.println("BAŞARISIZ ATIŞ!");
                    hamleTahta[satir][sutun] = ". ";
                    tahtayiYazdir(hamleTahta);
                    turn = 0;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return kazandiMi;
    }

    public void cevreIsaret(int bas, int bit, int sbt, String[][] karsiTahta, boolean gemiYatay) {
        if (gemiYatay) {
            for (int cevre = bas; cevre <= bit; cevre++) {
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
                //if bloğu içinde çünkü oyun alanı dışı olup olmadığını bilmiyoruz.
            }
        } else {
            for (int cevre = bas; cevre <= bit; cevre++) {
                if (cevre + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre + 1][sbt].equals(". ")) {
                    hamleTahta[cevre + 1][sbt] = ". ";
                }
                if (cevre > 0 && karsiTahta[cevre - 1][sbt].equals(". ")) {
                    hamleTahta[cevre - 1][sbt] = ". ";
                }
                if (sbt + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre][sbt + 1].equals(". ")) {
                    hamleTahta[cevre][sbt + 1] = ". ";
                }
                if (sbt > 0 && karsiTahta[cevre][sbt - 1].equals(". ")) {
                    hamleTahta[cevre][sbt - 1] = ". ";
                }

            }
        }
    }
}
