import java.util.Random;
import java.util.Scanner;

public class Play {

     int boardSize;
     int[]gemiBoyutlari = {1,2,3,4};
     String cevapSeviye;
     String[][] board;
     Scanner answ = new Scanner(System.in);
     boolean kuralDisiMi;

     public void oyunaGiris()
     {
       System.out.println("Amiral Battı Oyunu'na Hoş Geldiniz! :)");
       System.out.println("Oynamak istediğiniz zorluk seviyesini giriniz: ");
       System.out.println("Kolay - Orta - Zor");
      
       
       do{ //doğru string girilene kadar döngüde
       kuralDisiMi = false;
       
       try {
       cevapSeviye=answ.next();
       if(cevapSeviye.equalsIgnoreCase("kolay"))
       {
         boardSize = 6; //5*5lik tahta
         kuralDisiMi = false;
       }

       else if(cevapSeviye.equalsIgnoreCase("orta"))
       {
        boardSize = 8; //7*7lik tahta
        kuralDisiMi = false;
       }
       else if(cevapSeviye.equalsIgnoreCase("zor"))
       {
        boardSize = 11; //10*10luk tahta
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
            cevapKimle = answ.nextInt();
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
            answ.next();
            kuralDisiMi = true;
        }
            }while(kuralDisiMi);

        tahtayiDoldur();
        gemileriYerlestir();
        tahtayiYazdir();


    }

     public void tahtayiDoldur()//ortak tahta
    {
        board = new String[boardSize][boardSize];
        for(int i=1; i<boardSize;i++)
        {
            for(int j=1; j<boardSize;j++)
            {
                board[i][j]= "~ ";
            }
        }
    }

    public void gemileriYerlestir()//Bilgisayarın gemileri
    {
        
        Random rndGemi = new Random();
        for (int i = 0; i < gemiBoyutlari.length; i++) {//gemiyi rastgele yerleştir.
            int gemiBoyutu = gemiBoyutlari[i]; 
            int randomRow, randomCol;
            do {
                randomRow = rndGemi.nextInt(boardSize);
                randomCol = rndGemi.nextInt(boardSize);
            } while (!gemininKonumuDogruMu(randomRow, randomCol, gemiBoyutu));

            for (int j = 0; j < gemiBoyutu; j++) {
                board[randomRow + j][randomCol] = "G ";//geminin dikey yerleştirilmesi
            }
            
        }
    }

    private boolean gemininKonumuDogruMu(int row, int col, int gemiBoyutu) {
        if (row + gemiBoyutu > boardSize) {
            return false;
        }

        for (int i = 0; i < gemiBoyutu; i++) {//her birimi tek tek kontrol.
            if (board[row + i][col] != "~ ") {//dalga yoksa false dönecek.
                return false;
            }
            
        }
        return true;
    }

    public void tahtayiYazdir()
    {
        for(int ust=1; ust<boardSize; ust++)
        {
            System.out.print(" " + ust + " ");

        }
        System.out.println();
        for (int satir = 1; satir <boardSize; satir++) {
            System.out.print(satir + " ");
            for (int sutun = 1; sutun < boardSize; sutun++) {
                System.out.print(board[satir][sutun] + " ");
            }
            System.out.println();
        }
    }
}