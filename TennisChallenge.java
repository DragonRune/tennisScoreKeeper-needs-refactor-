import java.util.Scanner;
import java.lang.*;

/**
 * Class that handles scoring tennis games, sets and matches. This class is
 * meant to be uses text output to the console and takes input from the
 * keyboard.
 *
 * @author Student Person
 */
public class TennisChallenge {

    /**
     * Shared Scanner used by all methods.
     */
    public Scanner keyboard = new Scanner(System.in);

    private int p1GameScore = 0;
    private int p2GameScore = 0;
    private int p1SetScore = 0;
    private int p2SetScore = 0;
    private int p1MatchScore = 0;
    private int p2MatchScore = 0;

    /**
     * TODO docs!
     */
    public void run()
    {
        // TODO add code...
        System.out.print("Would you like to score a (g)ame, (o)vertime game, (s)et, or a (m)atch: ");
        char gameType = keyboard.next().charAt(0);
        if(gameType=='g'){
          game();
        }else if(gameType=='s'){
          set();
        }else if(gameType=='m'){
          match();
        }else if(gameType=='o'){
          overtime();
        }
    }

    private int game(){
      while(p1GameScore<4 && p2GameScore <4 || Math.abs(p1GameScore-p2GameScore)<2){
        if(keyboard.hasNextInt()){
          String input = keyboard.nextLine();
          String integers[] = input.split(" ");
          for(String number : integers){
            try {
              int num = Integer.parseInt(number);
              if(num==1 || num==2){
                gameScoreUpdate(num);
              }else{
                System.out.println("Invalid player # entered:" + num);
              }
            } catch(NumberFormatException e){
              //System.out.println("Invalid player # entered:" + number.toString());
            }
          }
        }else if(keyboard.hasNextLine()){
          String text = keyboard.next();
          System.out.println("Invalid player # entered:" + text);
        }
      }
      int gameWin = gameScoreCompare(p1GameScore, p2GameScore);
      return gameWin;
    }

    private int set(){
      boolean continueGame=true;
      while(continueGame){
        int tempGameScore = game();
        if(tempGameScore==1){
          p1SetScore++;
          //resetScore(p1GameScore, p2GameScore);
        }else if(tempGameScore==2){
          p2SetScore++;
        }
        p1GameScore=0;
        p2GameScore=0;
        System.out.println("Set score: " + p1SetScore + "-" + p2SetScore);
        continueGame = setScore(p1SetScore, p2SetScore);
      }
      int setWin = setScoreCompare(p1SetScore, p2SetScore);
      return setWin;
    }

    private void match(){
      boolean continueSet=true;
      while(continueSet){
        int tempSetScore = set();
        if(tempSetScore==1){
          p1MatchScore++;
        }else if(tempSetScore==2){
          p2MatchScore++;
        }
        p1SetScore=0;
        p2SetScore=0;
        System.out.println("Match score: " + p1MatchScore + "-" + p2MatchScore);
        if(p1MatchScore>=2 || p2MatchScore>=2){
          continueSet=false;
        }
      }
      matchScore();
    }

    private int overtime(){
      while(Math.abs(p1GameScore-p2GameScore)<2){
        if(keyboard.hasNextInt()){
          String input = keyboard.nextLine();
          String integers[] = input.split(" ");
          for(String number : integers){
            try {
              int num = Integer.parseInt(number);
              if(num==1 || num==2){
                gameScoreUpdate(num);
              }else{
                System.out.println("Invalid player # entered:" + num);
              }
            } catch(NumberFormatException e){
              //System.out.println("Invalid player # entered:" + number.toString());
            }
          }
        }else if(keyboard.hasNextLine()){
          String text = keyboard.next();
          System.out.println("Invalid player # entered:" + text);
        }
      }
      int overTimeWin = gameScoreCompare(p1GameScore, p2GameScore);
      return overTimeWin;
    }

    private void matchScore(){
      if(p1MatchScore>=2){
        System.out.println("\nMatch over: Player1 won");
      }else if(p2MatchScore>=2){
        System.out.println("\nMatch over: Player2 won");
      }
    }

    private boolean setScore(int p1, int p2){
      if( (p1>=6 && (p1-p2)>=2) || (p2>=6 && (p2-p1)>=2) || p1==7 || p2 ==7){
        return false;
      }else{
        return true;
      }
    }

    private int setScoreCompare(int p1, int p2){
      if(p1>p2){
        System.out.println("Set over: Player 1 won");
        return 1;
      }else if(p2>p1){
        System.out.println("Set over: Player 2 won");
        return 2;
      }else if(p1==p2){
        return 3;
      }else{
        return 0;
      }
    }

    private int gameScoreCompare(int p1, int p2){
      if(p1>p2){
        System.out.println("Game over: Player 1 won");
        return 1;
      }else if(p2>p1){
        System.out.println("Game over: Player 2 won");
        return 2;
      }else if(p1==p2){
        return 3;
      }else{
        return 0;
      }
    }

    private void gameScoreUpdate(int score){
      if(score == 1){
        p1GameScore++;
        System.out.println("Game score: " + p1GameScore + "-" + p2GameScore);
      }else if(score == 2){
        p2GameScore++;
        System.out.println("Game score: " + p1GameScore + "-" + p2GameScore);
      }
    }

    private int scorePass(){
      if(keyboard.hasNextInt()){
          int score = keyboard.nextInt();
          if(score==1 || score==2){
              return score;
          }else{
              System.out.println("Invalid player # entered:" + score);
          }
      }else if(keyboard.hasNextLine()){
        String text = keyboard.next();
        System.out.println("Invalid player # entered:" + text);
      }
      return 0;
    }

}
