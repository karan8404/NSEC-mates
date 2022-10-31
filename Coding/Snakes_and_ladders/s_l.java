import java.io.*;
import java.util.Scanner;

class s_l extends player {
    int size;
    player pl[];
    int sl1[] = { 4, 13, 27, 33, 40, 43, 50, 54, 62, 66, 74, 76, 89, 99 };
    int sl2[] = { 25, 46, 5, 49, 3, 18, 69, 31, 81, 45, 92, 58, 31, 41 };

    void find(int i) {// to check if a snake or ladder is present at a position
        int j;
        boolean flag = false;
        for (j = 0; j < sl1.length; j++)
            if (pl[i - 1].pos == sl1[j]) {
                flag = true;
                break;
            }
        if (flag) {
            if (sl1[j] < sl2[j])// ladder
                System.out.println("Encountered ladder!! Teleported to square " + sl2[j]);
            else// snake
                System.out.println("Encountered snake!! Teleported to square " + sl2[j]);
            pl[i - 1].pos = sl2[j];
        }
    }

    int finishedPlayers() {// to count number of players who have finished the game.
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (pl[i].finished)
                n++;
        }
        return n;
    }

    void play(int i) throws IOException {// to make dice roll.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int roll;// number rolled on dice.
        if (pl[i - 1].finished)
            return;// to not play if the player has finished the game
        System.out.println();
        System.out.print("Player " + i + " to roll dice(press enter)");
        br.readLine();// to check if enter is pressed
        roll = (int) random.rand(1, 6);
        System.out.print("Rolled " + roll + ".");
        if (!pl[i - 1].started) {// to check if player has got a 1 to enter the game yet.
            if (roll == 1) {
                System.out.println("Started the game for player " + i + ".");
                pl[i - 1].started = true;
                pl[i - 1].pos = 1;
            } else
                System.out.println("Still at starting point.");
            return;
        }
        pl[i - 1].pos = pl[i - 1].pos + roll;
        if (pl[i - 1].pos >= 100) {// to finish the game for a player upon reaching 100.
            pl[i - 1].pos = 100;
            System.out.println("Reached 100!! Game finished for player " + i + ".");
            pl[i - 1].finished = true;
        }
        if (pl[i - 1].pos < 100)
            System.out.println("Position " + pl[i - 1].pos);
        find(i);
    }

    public static void main2(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of players");
        int size = sc.nextInt();// number of players.
        s_l ob = new s_l(size);

        int i;// player number currently moving
        for (i = 1; ob.finishedPlayers() < size - 1; i++) {
            if (i == size + 1) {// to return to player 1 if all players are exhausted.
                System.out.println();
                for (int j = 0; j < size; j++) {// to show position of each player.
                    if (ob.pl[j].pos == 100)
                        continue;
                    System.out.println("Position of player " + (j + 1) + " is " + ob.pl[j].pos);
                }
                i = 1;
            }
            ob.play(i);
            sc.close();
        }
    }

    s_l(int n) {// constructor
        size = n;
        pl = new player[size];
        for (int i = 0; i < size; i++)
            pl[i] = new player();
    }
}
