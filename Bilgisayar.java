import java.util.Random;

public class Bilgisayar extends Play {
    static Oyuncu oyuncu = new Oyuncu("KULLANICI");
    static int sayac = 0;
    int baslangic, bitis, ilkVurulanSatir, ilkVurulanSutun, sbt;
    boolean gemiYatay;
    int vurus = 0;
    static boolean kazandiMi = false;
    static String[][] hamleTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    static String[][] bilgisayarTahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
    int yeniHamleYonu = 1;

    public Bilgisayar() {
    }

    public boolean atisKontrolBilgisayar(String[][] karsitahta) {
        Random random = new Random();
        int satir, sutun;
        int turn = 1;
        while (turn == 1 && sayac < 14) {
            try {
                //vurus>1
                if (vurus == 0) {
                    satir = random.nextInt(Play.tahtaBuyuklugu);
                    sutun = random.nextInt(Play.tahtaBuyuklugu);
                    if (hamleTahta[satir][sutun].equals("X ") || hamleTahta[satir][sutun].equals(". ")) {
                        throw new IllegalArgumentException(" ");
                    } else if (!karsitahta[satir][sutun].equals("0 ") && !karsitahta[satir][sutun].equals(". ")) {
                        System.out.println("Bilgisayar başarılı atış yaptı.");
                        hamleTahta[satir][sutun]="X ";
                        sayac++;
                        vurus++;
                        ilkVurulanSatir = satir;
                        ilkVurulanSutun = sutun;
                        int i=vurus;
                        if (karsitahta[satir][sutun].equals(String.valueOf(i) + " ")) {
                            System.out.println("Gemi batırıldı.");
                            cevreIsaret(ilkVurulanSatir, ilkVurulanSatir, sutun, karsitahta, gemiYatay);
                            vurus = 0;
                        } else {
                            turn=bilgisayarHamleAlgoritmasi(ilkVurulanSatir, ilkVurulanSutun, karsitahta, turn);
                        }
                    } else {
                        System.out.println("Bilgisayar başarısız atış yaptı.");
                        turn = 0;
                        hamleTahta[satir][sutun] = ". ";
                        break;
                    }
                }
                else {
                    turn=bilgisayarHamleAlgoritmasi(ilkVurulanSatir, ilkVurulanSutun, karsitahta, turn);
                }

                if (sayac == 14) {
                    System.out.println("Oyun Bitti ");
                    kazandiMi = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                turn = 1;
            }
        }
        return kazandiMi;
    }


    public int bilgisayarHamleAlgoritmasi(int ilksatir, int ilksutun, String[][] karsitahta, int turn) {
        int satir, sutun;
        try {
            switch (yeniHamleYonu) {
                case 1:
                    sutun = ilksutun + 1;
                    while (sutun < Play.tahtaBuyuklugu && !hamleTahta[ilksatir][sutun].equals(". ")) {
                        if (karsitahta[ilksatir][sutun] != ". " && karsitahta[ilksatir][sutun] != "0 ") {
                            hamleTahta[ilksatir][sutun] = "X ";
                            gemiYatay = true;
                            bitis = sutun;
                            baslangic = ilksutun;
                            sutun++;
                            sayac++;
                            vurus++;
                            System.out.println("Geminin"+vurus+". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurus) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                yeniHamleYonu = 1;
                                turn=1;
                                cevreIsaret(baslangic, bitis, ilksatir, karsitahta, gemiYatay);
                                vurus = 0;
                                break;
                            }
                        } else {
                            System.out.println("Bilgisayarın"+vurus+". hamlesi başarısız ");
                            hamleTahta[ilksatir][sutun] = ". ";
                            turn = 0;


                        }
                    }
                    yeniHamleYonu++;
                    break;
                case 2:
                    sutun = ilksutun - 1;
                    while (0 <= sutun && !hamleTahta[ilksatir][sutun].equals(". ")) {
                        if (karsitahta[ilksatir][sutun] != ". " && karsitahta[ilksatir][sutun] != "0 ") {
                            hamleTahta[ilksatir][sutun] = "X ";
                            gemiYatay = true;
                            baslangic = sutun;
                            bitis = ilksutun;
                            --sutun;
                            ++sayac;
                            vurus++;
                            System.out.println("Geminin"+vurus+". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurus) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                cevreIsaret(baslangic, bitis, ilksatir, karsitahta, gemiYatay);
                                vurus = 1;
                                yeniHamleYonu = 1;
                                break;
                            }
                        } else {
                            System.out.println("Bilgisayarın"+vurus+". hamlesi başarısız ");
                            hamleTahta[ilksatir][sutun] = ". ";

                            turn = 0;

                        }
                    }
                    yeniHamleYonu++;
                    break;
                case 3:
                    satir = ilksatir + 1;
                    while (satir < Play.tahtaBuyuklugu && !hamleTahta[satir][ilksutun].equals(". ")) {
                        if (karsitahta[satir][ilksutun] != ". " && karsitahta[satir][ilksutun] != "0 ") {
                            hamleTahta[satir][ilksutun] = "X ";
                            gemiYatay = false;
                            bitis = satir;
                            baslangic = ilksatir;
                            ++satir;
                            ++sayac;
                            vurus++;
                            System.out.println("Geminin"+vurus+". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurus) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                yeniHamleYonu = 1;
                                cevreIsaret(baslangic, bitis, ilksutun, karsitahta, gemiYatay);
                                vurus = 0;
                                turn = 1;
                            }
                        } else {
                            System.out.println("Bilgisayarın"+vurus+". hamlesi başarısız ");
                            hamleTahta[satir][ilksutun] = ". ";

                            turn = 0;
                        }
                    }
                    yeniHamleYonu++;
                    break;
                case 4:
                    satir = ilksatir - 1;
                    while (satir >= 0 && !hamleTahta[satir][ilksutun].equals(". ")) {
                        if (karsitahta[satir][ilksutun] != ". " && karsitahta[satir][ilksutun] != "0 ") {
                            hamleTahta[satir][ilksutun] = "X ";
                            gemiYatay = false;
                            bitis = ilksatir;
                            baslangic = satir;
                            --satir;
                            ++sayac;
                            vurus++;
                            System.out.println("Geminin"+vurus+". birimi vuruldu.");
                            if (karsitahta[ilksatir][ilksutun].equals(String.valueOf(vurus) + " ")) {
                                System.out.println("Gemi batırıldı.");
                                turn=1;
                                yeniHamleYonu = 1;
                                cevreIsaret(baslangic, bitis, ilksutun, karsitahta, gemiYatay);
                                vurus = 0;
                                break;
                            }
                        }
                    }
                    break;
            }

        } catch (ArrayIndexOutOfBoundsException var7) {
            System.out.println("Tahta dışına çıkma hatası: " + var7.getMessage());
        }
return turn;
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
