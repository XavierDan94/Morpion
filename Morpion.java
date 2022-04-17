import java.util.Scanner ;

public class Morpion {
    public static Scanner sc = new Scanner(System.in);

    public static void start() {
        System.out.println("Bienvevue dans \"Le MORPION by Xavier DAN\"");
        System.out.println("Menu");
        System.out.print("1. Nouvelle Partie (P) \n2. Quitter (Q)\nVotre choix : ");
        String choice = sc.nextLine();

        while (choice.toLowerCase().equals("p")) {
            play();
            choice = sc.nextLine();
        }

        leave();
    }

    public static void displayTicTacToe(int[][] array) {
        char display ;
        System.out.println("   1  2  3");
        for (int i = 0 ; i < array.length ; i++){
            System.out.print((i + 1) +" ");
            for (int j = 0 ; j < array[i].length ; j++){
                if (array[i][j] == 1){
                    display = 'O';
                } else if (array[i][j] == 2) {
                    display = 'X';
                } else {
                    display = ' ';
                }
                System.out.print("["+ display +"]");
            }
            System.out.println("");
        }
    }

    public static void play() {
        int[][] grille = new int[3][3];
        String player1 ;
        String player2 ;
        String player ;
        int counter = 0 ;


        //System.out.println("Quel est votre nom, Joueur 1 ?");
        player1 = "Joueur 1" ;

        //System.out.println("Quel est votre nom, Joueur 2 ?");
        player2 = "Joueur 2" ;
        System.out.println("Que le show commence !");

        player = player2 ;
        int sign = 2 ;
        char pion = 'X' ;

        do {
            // Gestion de l'échange du joueur et de son spion : Joueur 1 devient Joueur 2 et son pion change
            player = player.equals(player1) ? player2 : player1 ;
            sign = sign == 1 ? 2 : 1 ;
            pion = pion == 'O' ? 'X' : 'O' ;
            int line ;
            int col ;

            System.out.println(player + " : Veuillez insérer le pion ("+ sign +") à la position souhaitée :");
            boolean condition2 ;

            do {
                //Vérification de la valeur de la ligne
                do {
                    System.out.println("Entrez la ligne (1, 2 ou 3)");
                    line = sc.nextInt();
                } while (!((line >= 1) && (line <= 3)));

                //Vérification de la valeur de la colonne
                do {
                    System.out.println("Entrez la colonne (1, 2 ou 3)");
                    col = sc.nextInt();
                } while (!((col >= 1) && (col <= 3)));

                //Intialisation de la condition si une valeur a déjà été rentrée
                condition2 = grille[line - 1][col - 1] != 0 ;

                //vérification de la case vide
                if (condition2){
                    System.out.println("Case déjà remplie. Veuillez réessayer !");
                } else {
                    grille[line - 1][col - 1] = sign ;
                    counter++ ;
                }
            } while (condition2);

            //Affichage de la grille
            displayTicTacToe(grille);

            //Vérification de la ligne
            int resuInLine = 0, resuInColumn = 0, resu = 0 ;
            for (int i = 0 ; i < grille.length ; i++){
                for (int j = 0 ; j < grille[i].length - 1; j++){
                    if (grille[i][j] == grille[i][j + 1]){
                        resuInLine += grille[i][j] != 0 ? 1 : 0 ;
                    } else if (grille[j][i] == grille[j + 1][i]) {
                        resuInColumn += grille[j][i] != 0 ? 1 : 0;
                    } else if ((grille[0][0] == grille[1][1]) && (grille[0][0] == grille[2][2])) {
                        resu += grille[0][0] != 0 ? 2 : 0 ;
                    } else if ((grille[2][0] == grille[1][1]) && (grille[2][0] == grille[0][2])) {
                        resu += grille[2][0] != 0 ? 2 : 0 ;
                    }
                }
                //Traitement de l'éventuelle victoire
                if (resu == 2 || resuInLine == 2 || resuInColumn == 2){
                    counter = 10;
                    System.out.println(player+" a gagné avec le pion ("+pion+")");
                } else {
                    resu = 0;
                    resuInColumn = 0;
                    resuInLine = 0 ;
                }
            }
        } while (counter < 9) ;

        //Cas du match nul
        if (counter == 9){
            System.out.println("Match nul ! On rejoue.");
            play();
        }
    }

    public static void leave() {
        System.out.println("Bye");
    }

    public static void main(String[] args){
        //Initialisation de la grille de jeu
        start();
        //
    }
}