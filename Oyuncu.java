import java.util.*;

public class Oyuncu extends Play {
    public String oyuncuAdi;
    String[][] tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    boolean kazandiMi = false;
    int sayac = 0;
    int vurus = 0;

    Oyuncu(String ad) {
        this.oyuncuAdi = ad;
    }

    public void gemileriYerlestir() {
        int satir, sutun;
        boolean dongu = false;
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
                        }
                        else {
                            System.out.println("Gemi yönünü belirle:\n Yukarı için 'U'\n Aşağı için 'D'\n Sağ için 'R'\n Sol için 'L'");
                            yon = cvp.next().toLowerCase().charAt(0);
                            switch (yon) {
                                case 'u':
                                    if (satir - (gemiBoyutu - 1) < 0) {
                                        throw new IllegalArgumentException("Yeterli alan yok.Başka bir yön seç!");

                                    }
                                    for (int kontrol = satir; kontrol > (satir - gemiBoyutu); kontrol--) {

                                        if (tahta[kontrol][sutun] != "0 ") {
                                            throw new IllegalArgumentException("Alan boş değil,BAŞKA BİR KOORDİNAT GİR!");

                                        }
                                        else {
                                            dongu=false;
                                        }
                                    }
                                    for (int yerlestir = satir; yerlestir > (satir - gemiBoyutu); yerlestir--) {
                                        tahta[yerlestir][sutun] = Integer.toString(gemiBoyutu) + " ";

                                        if (yerlestir + 1 < tahtaBuyuklugu && tahta[yerlestir + 1][sutun] == "0 ") {
                                            tahta[yerlestir + 1][sutun] = ". ";
                                        }
                                        if (sutun + 1 < tahtaBuyuklugu && tahta[yerlestir][sutun + 1] == "0 ") {
                                            tahta[yerlestir][sutun + 1] = ". ";
                                        }
                                        if (yerlestir > 0 && tahta[yerlestir - 1][sutun] == "0 ") {
                                            tahta[yerlestir - 1][sutun] = ". ";
                                        }
                                        if (sutun > 0 && tahta[satir][yerlestir - 1] == "0 ") {
                                            tahta[yerlestir][sutun - 1] = ". ";
                                        }
                                    }
                                    Play.tahtayiYazdir(tahta);
                                    break;
                                case 'd':
                                    if (satir + (gemiBoyutu - 1) > tahtaBuyuklugu - 1) {
                                        throw new IllegalArgumentException("Yeterli alan yok.Başka bir yön seç!");

                                    }
                                    for (int kontrol = satir; kontrol < (satir + gemiBoyutu); kontrol++) {
                                        if (tahta[kontrol][sutun] != "0 ") {
                                            throw new IllegalArgumentException("Alan boş değil,BAŞKA BİR KOORDİNAT GİR!");
                                        }
                                        else{
                                            dongu=false;
                                        }
                                    }
                                    for (int yerlestir = satir; yerlestir < (satir + gemiBoyutu); yerlestir++) {

                                        tahta[yerlestir][sutun] = Integer.toString(gemiBoyutu) + " ";
                                        if (yerlestir + 1 < tahtaBuyuklugu && tahta[yerlestir + 1][sutun] == "0 ") {
                                            tahta[yerlestir + 1][sutun] = ". ";
                                        }
                                        if (sutun + 1 < tahtaBuyuklugu && tahta[yerlestir][sutun + 1] == "0 ") {
                                            tahta[yerlestir][sutun + 1] = ". ";
                                        }
                                        if (yerlestir > 0 && tahta[yerlestir - 1][sutun] == "0 ") {
                                            tahta[yerlestir - 1][sutun] = ". ";
                                        }
                                        if (sutun > 0 && tahta[yerlestir][sutun - 1] == "0 ") {
                                            tahta[yerlestir][sutun - 1] = ". ";
                                        }
                                    }
                                    Play.tahtayiYazdir(tahta);
                                    break;
                                case 'r':
                                    if (sutun + (gemiBoyutu - 1) > tahtaBuyuklugu - 1) {
                                        throw new IllegalArgumentException("Yeterli alan yok.Başka bir yön seç!");

                                    }
                                    for (int kontrol = sutun; kontrol < (sutun + gemiBoyutu); kontrol++) {

                                        if (tahta[satir][kontrol] != "0 ") {
                                            throw new IllegalArgumentException("Alan boş değil,BAŞKA BİR KOORDİNAT GİR!");

                                        }
                                        else {
                                            dongu=false;
                                        }
                                    }
                                    for (int yerlestir = sutun; yerlestir < (sutun + gemiBoyutu); yerlestir++) {

                                        tahta[satir][yerlestir] = Integer.toString(gemiBoyutu) + " ";

                                        if (satir + 1 < tahtaBuyuklugu && tahta[satir + 1][yerlestir] == "0 ") {
                                            tahta[satir + 1][yerlestir] = ". ";
                                        }
                                        if (satir > 0 && tahta[satir - 1][yerlestir] == "0 ") {
                                            tahta[satir - 1][yerlestir] = ". ";
                                        }
                                        if (yerlestir + 1 < tahtaBuyuklugu && tahta[satir][yerlestir + 1] == "0 ") {
                                            tahta[satir][yerlestir + 1] = ". ";
                                        }
                                        if (yerlestir > 0 && tahta[satir][yerlestir - 1] == "0 ") {
                                            tahta[satir][yerlestir - 1] = ". ";
                                        }
                                    }
                                    Play.tahtayiYazdir(tahta);
                                    break;
                                case 'l':
                                    if (sutun - (gemiBoyutu - 1) < 0) {
                                        throw new IllegalArgumentException("Yeterli alan yok.Başka bir yön seç!");
                                    }
                                    for (int kontrol = sutun; kontrol > (sutun - gemiBoyutu); kontrol--) {

                                        if (tahta[satir][kontrol] != "0 ") {
                                            throw new IllegalArgumentException("Alan boş değil,BAŞKA BİR KOORDİNAT GİR!");
                                        }
                                        else {
                                            dongu=false;
                                        }
                                    }
                                    for (int yerlestir = sutun; yerlestir > (sutun - gemiBoyutu); yerlestir--) {
                                        tahta[satir][yerlestir] = Integer.toString(gemiBoyutu) + " ";

                                        if (satir + 1 < tahtaBuyuklugu && tahta[satir + 1][yerlestir] == "0 ") {
                                            tahta[satir + 1][yerlestir] = ". ";
                                        }
                                        if (satir > 0 && tahta[satir - 1][yerlestir] == "0 ") {
                                            tahta[satir - 1][yerlestir] = ". ";
                                        }
                                        if (yerlestir + 1 < tahtaBuyuklugu && tahta[satir][yerlestir + 1] == "0 ") {
                                            tahta[satir][yerlestir + 1] = ". ";
                                        }
                                        if (yerlestir > 0 && tahta[satir][yerlestir - 1] == "0 ") {
                                            tahta[satir][yerlestir - 1] = ". ";
                                        }
                                    }
                                    Play.tahtayiYazdir(tahta);
                                    break;
                                default:
                                    System.out.println("GEÇERLİ BİR HARF GİR!");
                                    dongu = true;

                            }
                            dongu = false;
                        }
                    } catch (Exception e) {
                        dongu = true;
                        System.out.println(e.getMessage());
                        cvp.next();
                    }
                }
                while (dongu);
            }
        }
    }

    public boolean vur(String[][] karsitahta) {
        int ilkVurulanSatir = 0;
        int sonVurulanSatir = 0;
        int ilkVurulanSutun = 0;
        int sonVurulanSutun = 0;
        int baslangic = 0;
        int bitis = 0, sbt = 0;
        boolean gemiYatay = false;
        Scanner hamle = new Scanner(System.in);
        System.out.println("Vurmak İstediğin Koordinatı Gir!");
        int turn = 1;
        while (turn == 1) {
            try {
                System.out.println("Satır:");
                int satir = hamle.nextInt()-1;
                System.out.println("Sutun:");
                int sutun = hamle.nextInt()-1;
                if (satir > tahtaBuyuklugu-1 || sutun > tahtaBuyuklugu-1) {
                    throw new IllegalArgumentException("LÜTFEN GEÇERLİ BİR KOORDİNAT GİR!");
                } else if (hamleTahta[satir][sutun] == "X "||hamleTahta[satir][sutun] == ". ") {
                    throw new IllegalArgumentException("BU NOKTAYI ZATEN VURDUNUZ!");

                } else if (vurus == 1) {
                    if (satir == ilkVurulanSatir && Math.abs(sutun - ilkVurulanSutun) == 1) {
                        if (karsitahta[satir][sutun] != "0 " || karsitahta[satir][sutun] != ". ") {
                            gemiYatay = true;
                        }
                    } else if (sutun == ilkVurulanSutun && Math.abs(satir - ilkVurulanSatir) == 1) {
                        if (karsitahta[satir][sutun] != "0 " || karsitahta[satir][sutun] != ". ") {
                            gemiYatay = false;
                        }
                    } else {
                        throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");

                    }
                } else if (vurus > 1) {
                    if (gemiYatay) {
                        if (Math.abs((sutun - ilkVurulanSutun)) != 1 && Math.abs((sutun - sonVurulanSutun)) != 1) {
                            throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");
                        }
                    } else {
                        if (Math.abs((satir - ilkVurulanSatir)) != 1 && Math.abs((satir - sonVurulanSatir)) != 1) {
                            throw new IllegalArgumentException("ÖNCE VURULAN GEMİYİ BATIRMALISIN!\nİPUCU:Vurulan noktanın çevresine atış yap!");
                        }
                    }

                }
                if (karsitahta[satir][sutun] != "0 " && karsitahta[satir][sutun] != ". ") {
                    System.out.println("BAŞARILI ATIŞ!!");
                    sayac++;

                    switch (karsitahta[satir][sutun]) {
                        case "1 ":
                            System.out.println("TEBRİKLER,BİR DENİZALTI İMHA ETTİN!!");
                            hamleTahta[satir + 1][sutun] = ". ";
                            hamleTahta[satir - 1][sutun] = ". ";
                            hamleTahta[satir][sutun + 1] = ". ";
                            hamleTahta[satir][sutun - 1] = ". ";
                            System.out.println("Hamle sırası tekrar sizin.");
                            break;
                        case "2 ":
                            if (vurus == 0) {
                                ilkVurulanSatir = satir;
                                ilkVurulanSutun = sutun;
                                vurus++;
                                break;
                            } else {
                                System.out.println("Vurulan gemi için 2. koordinat.");
                                System.out.println("TEBRİKLER,BİR MAYINGEMİSİ İMHA ETTİN!!");
                                sonVurulanSatir=satir;
                                sonVurulanSutun=sutun;
                                if (gemiYatay) {
                                    cevreIsaret(Math.min(sutun, sonVurulanSutun), Math.max(sutun, sonVurulanSutun), satir, karsitahta, gemiYatay);
                                } else {
                                    cevreIsaret(Math.min(satir, sonVurulanSatir), Math.max(satir, sonVurulanSatir), sutun, karsitahta, gemiYatay);
                                }

                                this.vurus = 0;
                            }
                            break;
                        case "3 ":
                            switch (vurus) {
                                case 0:
                                    System.out.println("Geminin ilk birimi vuruldu.");
                                    ilkVurulanSatir = satir;
                                    ilkVurulanSutun = sutun;
                                    vurus++;
                                    break;

                                case 1:
                                    System.out.println("Geminin ikinci birimi vuruldu.");
                                    sonVurulanSatir = satir;
                                    sonVurulanSutun = sutun;
                                    if (gemiYatay) {
                                        baslangic = Math.min(ilkVurulanSutun, sutun);
                                        bitis = Math.max(ilkVurulanSutun, sutun);
                                    } else {
                                        baslangic = Math.min(ilkVurulanSatir, satir);
                                        bitis = Math.max(ilkVurulanSatir, satir);
                                    }
                                    vurus++;
                                    break;
                                case 2:
                                    System.out.println("Geminin üçüncü birimi vuruldu.");
                                    System.out.println("KRUVAZÖR BATIRILDI!");
                                    if (gemiYatay) {
                                        baslangic = Math.min(baslangic, sutun);
                                        bitis = Math.max(bitis, sutun);
                                        sbt = satir;
                                    } else {
                                        baslangic = Math.min(baslangic, satir);
                                        bitis = Math.max(bitis, satir);
                                        sbt = sutun;
                                    }
                                    vurus = 0;
                                    cevreIsaret(baslangic, bitis, sbt, karsitahta, gemiYatay);
                                    break;
                            }
                            break;
                        case "4 ":
                            switch (vurus) {
                                case 0:
                                    System.out.println("Geminin ilk birimi vuruldu.");
                                    ilkVurulanSatir = satir;
                                    ilkVurulanSutun = sutun;
                                    vurus++;
                                    break;
                                case 1:
                                    System.out.println("Geminin ikinci birimi vuruldu.");
                                    sonVurulanSatir = satir;
                                    sonVurulanSutun = sutun;
                                    if (gemiYatay) {
                                        baslangic = Math.min(ilkVurulanSutun, sutun);
                                        bitis = Math.max(ilkVurulanSutun, sutun);
                                        sbt = satir;
                                    } else {
                                        baslangic = Math.min(ilkVurulanSatir, satir);
                                        bitis = Math.max(ilkVurulanSatir, satir);
                                        sbt = sutun;
                                    }
                                    vurus++;
                                    break;
                                case 2:
                                    System.out.println("Geminin üçüncü birimi vuruldu.");
                                    sonVurulanSatir = satir;
                                    sonVurulanSutun = sutun;
                                    if (gemiYatay) {
                                        baslangic = Math.min(baslangic, sutun);
                                        bitis = Math.max(bitis, sutun);
                                    } else {
                                        baslangic = Math.min(baslangic, satir);
                                        bitis = Math.max(bitis, satir);
                                    }
                                    vurus++;
                                    break;

                                case 3:
                                    System.out.println("TEBRİKLER,AMİRALİ İMHA ETTİN!!");
                                    sonVurulanSatir = 0;
                                    sonVurulanSutun = 0;
                                    if (gemiYatay) {
                                        baslangic = Math.min(baslangic, sutun);
                                        bitis = Math.max(bitis, sutun);
                                    } else {
                                        baslangic = Math.min(baslangic, satir);
                                        bitis = Math.max(bitis, satir);
                                    }
                                    cevreIsaret(baslangic, bitis, sbt, karsitahta, gemiYatay);
                                    vurus = 0;

                            }
                            break;

                    }
                    karsitahta[satir][sutun] = "X ";
                    hamleTahta[satir][sutun] = "X ";
                    tahtayiYazdir(hamleTahta);
                    if (sayac == 14) {
                        System.out.println("Oyun Bitti ");
                        kazandiMi = true;
                        turn=0;
                    } else {
                        System.out.println("SIRA TEKRAR SENDE.");
                    }
                }
                else {
                    System.out.println("BAŞARISIZ ATIŞ!");
                    hamleTahta[satir][sutun] = ". ";
                    tahtayiYazdir(hamleTahta);
                    turn = 0;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                turn=1;
            }
        }
        return kazandiMi;
    }

    public void cevreIsaret(int bas, int bit, int sbt, String[][] karsiTahta, boolean gemiYatay) {
        if (gemiYatay) {
            for (int cevre = bas; cevre <= bit; cevre++) {
                if (sbt + 1 < tahtaBuyuklugu - 1 && karsiTahta[sbt + 1][cevre] == ". ") {
                    hamleTahta[sbt + 1][cevre] = ". ";
                }
                if (sbt > 0 && karsiTahta[sbt - 1][cevre] == ". ") {
                    hamleTahta[sbt - 1][cevre] = ". ";
                }
                if (cevre + 1 < tahtaBuyuklugu - 1 && karsiTahta[sbt][cevre + 1] == ". ") {
                    hamleTahta[sbt][cevre + 1] = ". ";
                }
                if (cevre > 0 && karsiTahta[sbt][cevre - 1] == ". ") {
                    hamleTahta[sbt][cevre - 1] = ". ";
                }
                //if bloğu içinde çünkü oyun alanı dışı olup olmadığını bilmiyoruz.
            }
        } else {
            for (int cevre = bas; cevre <= bit; cevre++) {
                if (cevre + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre + 1][sbt] == ". ") {
                    hamleTahta[cevre + 1][sbt] = ". ";
                }
                if (cevre > 0 && karsiTahta[cevre - 1][sbt] == ". ") {
                    hamleTahta[cevre - 1][sbt] = ". ";
                }
                if (sbt + 1 < tahtaBuyuklugu - 1 && karsiTahta[cevre][sbt + 1] == ". ") {
                    hamleTahta[cevre][sbt + 1] = ". ";
                }
                if (sbt > 0 && karsiTahta[cevre][sbt - 1] == ". ") {
                    hamleTahta[cevre][sbt - 1] = ". ";
                }

            }
        }
    }


}
