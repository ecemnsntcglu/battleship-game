import java.util.Random;
public class Bilgisayar extends Play {
    static Oyuncu oyuncu = new Oyuncu("KULLANICI");
    static int sayac = 0;
    int baslangic, bitis, ilkVurulanSatir, ilkVurulanSutun, sbt;
    boolean gemiYatay;
    int vurulanBirim = 0;
    static boolean kazandiMi = false;
    static String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    static String[][] bilgisayarTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    int hamleYonu = 1;
    public Bilgisayar() {
    }
    public boolean atisKontrolBilgisayar(String[][] karsitahta) {
        Random random = new Random();
        int satir, sutun;
        int hamleBittiMi = 1;
        while (hamleBittiMi == 1 && sayac < 14) {
            try {
                if (vurulanBirim == 0) {
                    satir = random.nextInt(Play.tahtaBuyuklugu);
                    sutun = random.nextInt(Play.tahtaBuyuklugu);
                    if (hamleTahta[satir][sutun].equals("X ") || hamleTahta[satir][sutun].equals(". ")) {
                        throw new IllegalArgumentException(" ");
                    }
                    else if (!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")) {
                        System.out.println("Bilgisayar başarılı atış yaptı.");
                        hamleTahta[satir][sutun]="X ";
                        sayac++;
                        vurulanBirim++;
                        ilkVurulanSatir = satir;
                        ilkVurulanSutun = sutun;
                        if (karsitahta[satir][sutun].equals(String.valueOf(vurulanBirim) + " ")) {
                            System.out.println("Gemi batırıldı.");
                            cevreIsaret(ilkVurulanSatir, ilkVurulanSatir, sutun, karsitahta, gemiYatay);
                            vurulanBirim = 0;
                        } else {
                            hamleBittiMi =bilgisayarHamleAlgoritmasi(ilkVurulanSatir, ilkVurulanSutun, karsitahta, hamleBittiMi);
                        }
                    }
                    else {
                        System.out.println("Bilgisayar başarısız atış yaptı.");
                        hamleBittiMi = 0;
                        hamleTahta[satir][sutun] = ". ";
                        break;
                    }
                }
                else {
                    hamleBittiMi=bilgisayarHamleAlgoritmasi(ilkVurulanSatir, ilkVurulanSutun, karsitahta, hamleBittiMi);
                }
                if (sayac == 14) {
                    System.out.println("Oyun Bitti ");
                    kazandiMi = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                hamleBittiMi = 1;
            }
        }
        return kazandiMi;
    }


    public int bilgisayarHamleAlgoritmasi(int ilksatir, int ilksutun, String[][] karsitahta, int hamleBittiMi) {
        int satir, sutun;
        try {
            switch (hamleYonu) {
                case 1: //sağ
                    sutun = ilksutun + 1;
                    while (sutun < Play.tahtaBuyuklugu && !hamleTahta[ilksatir][sutun].equals(". ")) {
                        if (karsitahta[ilksatir][sutun] != ". " && karsitahta[ilksatir][sutun] != "0 ") {
                            hamleTahta[ilksatir][sutun] = "X ";
                            gemiYatay = true;
                            bitis = sutun;
                            baslangic = ilksutun;
                            sutun++;
                            sayac++;
                            vurulanBirim++;
                            System.out.println("Geminin "+ (vurulanBirim+1) + ". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurulanBirim) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                hamleYonu = 1;
                                hamleBittiMi=1;
                                cevreIsaret(baslangic, bitis, ilksatir, karsitahta, gemiYatay);
                                vurulanBirim= 0;
                                break;
                            }
                        } else {
                            System.out.println("Bilgisayarın " + (vurulanBirim+1) + ". hamlesi başarısız ");
                            hamleTahta[ilksatir][sutun] = ". ";
                            hamleBittiMi= 0;
                        }
                    }
                    hamleYonu++;
                    break;
                case 2: //sol
                    sutun = ilksutun - 1;
                    while (0 <= sutun && !hamleTahta[ilksatir][sutun].equals(". ")) {
                        if (karsitahta[ilksatir][sutun] != ". " && karsitahta[ilksatir][sutun] != "0 ") {
                            hamleTahta[ilksatir][sutun] = "X ";
                            gemiYatay = true;
                            baslangic = sutun;
                            bitis = ilksutun;
                            --sutun;
                            ++sayac;
                            vurulanBirim++;
                            System.out.println("Geminin "+ (vurulanBirim+1) + ". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurulanBirim) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                cevreIsaret(baslangic, bitis, ilksatir, karsitahta, gemiYatay);
                                vurulanBirim= 0;
                                hamleYonu = 1;
                                break;
                            }
                        } else {
                            System.out.println("Bilgisayarın " + (vurulanBirim+1) + ". hamlesi başarısız ");
                            hamleTahta[ilksatir][sutun] = ". ";
                            hamleBittiMi= 0;
                        }
                    }
                    hamleYonu++;
                    break;
                case 3: //aşşağı
                    satir = ilksatir + 1;
                    while (satir < Play.tahtaBuyuklugu && !hamleTahta[satir][ilksutun].equals(". ")) {
                        if (karsitahta[satir][ilksutun] != ". " && karsitahta[satir][ilksutun] != "0 ") {
                            hamleTahta[satir][ilksutun] = "X ";
                            gemiYatay = false;
                            bitis = satir;
                            baslangic = ilksatir;
                            ++satir;
                            ++sayac;
                            vurulanBirim++;
                            System.out.println("Geminin "+ (vurulanBirim+1) + ". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurulanBirim) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                hamleYonu = 1;
                                cevreIsaret(baslangic, bitis, ilksutun, karsitahta, gemiYatay);
                                vurulanBirim = 0;
                                hamleBittiMi= 0;
                            }
                        } else {
                            System.out.println("Bilgisayarın " + (vurulanBirim+1) + ". hamlesi başarısız ");
                            hamleTahta[satir][ilksutun] = ". ";
                            hamleBittiMi= 0;
                        }
                    }
                    hamleYonu++;
                    break;
                case 4: //yukarı
                    satir = ilksatir - 1;
                    while (satir >= 0 && !hamleTahta[satir][ilksutun].equals(". ")) {
                        if (karsitahta[satir][ilksutun] != ". " && karsitahta[satir][ilksutun] != "0 ") {
                            hamleTahta[satir][ilksutun] = "X ";
                            gemiYatay = false;
                            bitis = ilksatir;
                            baslangic = satir;
                            --satir;
                            ++sayac;
                            vurulanBirim++;
                            System.out.println("Geminin "+ (vurulanBirim+1) + ". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurulanBirim) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                hamleBittiMi=1;
                                hamleYonu = 1;
                                cevreIsaret(baslangic, bitis, ilksutun, karsitahta, gemiYatay);
                                vurulanBirim = 0;
                                break;
                            }
                        }
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException var7) {
            System.out.println("Tahta dışına çıkma hatası: " + var7.getMessage());
        }
        return hamleBittiMi;
    }


    public static void cevreIsaret(int bas, int bit, int sbt, String[][] karsiTahta, boolean gemiYatay) {
        int cevre;
        if (gemiYatay) {
            for (cevre = bas; cevre <= bit; ++cevre) {
                if (sbt + 1 < tahtaBuyuklugu && karsiTahta[sbt + 1][cevre].equals(". ")) {
                    hamleTahta[sbt + 1][cevre] = ". ";
                }

                if (sbt > 0 && karsiTahta[sbt - 1][cevre].equals(". ")) {
                    hamleTahta[sbt - 1][cevre] = ". ";
                }

                if (cevre + 1 < tahtaBuyuklugu  && karsiTahta[sbt][cevre + 1].equals(". ")) {
                    hamleTahta[sbt][cevre + 1] = ". ";
                }

                if (cevre > 0 && karsiTahta[sbt][cevre - 1].equals(". ")) {
                    hamleTahta[sbt][cevre - 1] = ". ";
                }
            }
        } else {
            for (cevre = bas; cevre <= bit; ++cevre) {
                if (cevre + 1 < tahtaBuyuklugu && karsiTahta[cevre + 1][sbt] == ". ") {
                    hamleTahta[cevre + 1][sbt] = ". ";
                }

                if (cevre > 0 && karsiTahta[cevre - 1][sbt] == ". ") {
                    hamleTahta[cevre - 1][sbt] = ". ";
                }

                if (sbt + 1 < tahtaBuyuklugu && karsiTahta[cevre][sbt + 1] == ". ") {
                    hamleTahta[cevre][sbt + 1] = ". ";
                }

                if (sbt > 0 && karsiTahta[cevre][sbt - 1].equals(". ")) {
                    hamleTahta[cevre][sbt - 1] = ". ";
                }
            }
        }
    }
}
