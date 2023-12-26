import java.util.Random;
import java.util.Scanner;

public class Play {

     int tahtaBuyuklugu;
     int[]gemiBoyutlari = {1,2,3,4};
     String cevapSeviye;
     String[][] tahta;
     Scanner cvp = new Scanner(System.in);
     boolean kuralDisiMi;

     public void oyunaGiris()
     {
       System.out.println("Amiral Battı Oyunu'na Hoş Geldiniz! :)");
       System.out.println("Oynamak istediğiniz zorluk seviyesini giriniz: ");
       System.out.println("Kolay - Orta - Zor");
       do{ //doğru string girilene kadar döngüde
       kuralDisiMi = false;
       
       try {
       cevapSeviye= cvp.next();
       if(cevapSeviye.equalsIgnoreCase("kolay"))
       {
         tahtaBuyuklugu = 6; //5*5lik tahta
         kuralDisiMi = false;
       }

       else if(cevapSeviye.equalsIgnoreCase("orta"))
       {
        tahtaBuyuklugu = 8; //7*7lik tahta
        kuralDisiMi = false;
       }
       else if(cevapSeviye.equalsIgnoreCase("zor"))
       {
        tahtaBuyuklugu = 11; //10*10luk tahta
        kuralDisiMi = false;
       }
       else
       throw new IllegalArgumentException("Lütfen geçerli bir zorluk seviyesi giriniz: ");
     } 
     catch (IllegalArgumentException e) 
     {
        System.out.print(e.getMessage());
        kuralDisiMi = true;
     }
    }while(kuralDisiMi);

        kimleOynaniyor(); 
        
    }

    public void kimleOynaniyor()
    {
        int cevapKimle;
        System.out.print("Bilgisayarla oynamak için 1, Arkadaşınızla oynamak için 2'yi giriniz: ");
        
        //InputMismatchException hatası
        do{
            kuralDisiMi = false;
        try {
            cevapKimle = cvp.nextInt();
            if (cevapKimle == 1) {
                System.out.println("Bilgisayarla oynayacaksınız.");
                kuralDisiMi = false;
            } else if (cevapKimle == 2) {
                System.out.println("Arkadaşınızla oynayacaksınız.");
                kuralDisiMi = false;
            } else 
                throw new IllegalArgumentException("Geçerli bir sayı girin: ");
            
        } catch (Exception e) {
            System.out.print("Geçerli bir sayı girin: ");
            cvp.next();
            kuralDisiMi = true;
        }
            }while(kuralDisiMi);

        tahtayiDoldur();
        gemileriYerlestir();
        tahtayiYazdir();


    }

     public void tahtayiDoldur()//ortak tahta
    {
        tahta = new String[tahtaBuyuklugu][tahtaBuyuklugu];
        for(int i=1; i<tahtaBuyuklugu;i++)
        {
            for(int j=1; j<tahtaBuyuklugu;j++)
            {
                tahta[i][j]= "0 ";
            }
        }
    }

    public void gemileriYerlestir()//Bilgisayarın gemileri
    {
        
        Random rndGemi = new Random();
        for (int i = 0; i < gemiBoyutlari.length; i++) {//gemiyi rastgele yerleştir.
            int gemiBoyutu = gemiBoyutlari[i]; 
            int randomRow, randomCol;
            boolean gemiYatayMi;
            do {
                randomRow = rndGemi.nextInt(tahtaBuyuklugu);
                randomCol = rndGemi.nextInt(tahtaBuyuklugu);
                gemiYatayMi = rndGemi.nextBoolean();//geminin yatay / dikey random karar verilmesi.
            } while (!gemininKonumuDogruMu(gemiYatayMi,randomRow, randomCol, gemiBoyutu));

            for (int j = 0; j < gemiBoyutu; j++) {//geminin dikey yerleştirilmesi
                if(gemiYatayMi==false)//dikey
                {
                if(gemiBoyutu==1)
                tahta[randomRow + j][randomCol] = "1 ";

                else if(gemiBoyutu==2)
                tahta[randomRow + j][randomCol] = "2 ";

                else if(gemiBoyutu==3)
                tahta[randomRow + j][randomCol] = "3 ";

                else
                tahta[randomRow + j][randomCol] = "4 ";
                }

                else 
                {
                if(gemiBoyutu==1)
                tahta[randomRow][randomCol+j] = "1 ";

                else if(gemiBoyutu==2)
                tahta[randomRow][randomCol+j] = "2 ";

                else if(gemiBoyutu==3)
                tahta[randomRow][randomCol+j] = "3 ";

                else
                tahta[randomRow][randomCol+j] = "4 ";
                }

            }
            
        }
    }

    private boolean gemininKonumuDogruMu(boolean gemiYatayMi, int row, int col, int gemiBoyutu) {
        if(gemiYatayMi==false)
        {
        if (row + gemiBoyutu > tahtaBuyuklugu) {
            return false;
        }

        for (int i = 0; i < gemiBoyutu; i++) {//her birimi tek tek kontrol.
            if (tahta[row + i][col] != "0 ") {//dalga yoksa false dönecek.
                return false;
            }
            
        }
        }

        else
         {
        if (col + gemiBoyutu > tahtaBuyuklugu) {
            return false;
        }
        for (int i = 0; i < gemiBoyutu; i++) {//her birimi tek tek kontrol.
            if (tahta[row][col+i] != "0 ") {//dalga yoksa false dönecek.
                return false;
            }
            
        }
        }
        return true;
    
    }

    public void tahtayiYazdir()
    {
        for(int ust=1; ust<tahtaBuyuklugu; ust++)
        {
            System.out.print(" " + ust + " ");

        }
        System.out.println();
        for (int satir = 1; satir <tahtaBuyuklugu; satir++) {
            System.out.print(satir + " ");
            for (int sutun = 1; sutun < tahtaBuyuklugu; sutun++) {
                System.out.print(tahta[satir][sutun] + " ");
            }
            System.out.println();
        }
    }
}
