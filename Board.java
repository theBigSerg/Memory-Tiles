/** 
 * Activity 3.3.2
 *
 * A game board of NxM board of tiles.
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
public class Board
{  
  private static String[] tileValues = {"lion", "lion",
                                        "penguin", "penguin",
                                        "dolphin", "dolphin",
                                        "fox", "fox",
                                        "monkey", "monkey",
                                        "turtle", "turtle"}; 
  private Tile[][] gameboard = new Tile[3][4];


  /**  
   * Constructor for the game. Creates the 2D gameboard
   * by populating it with card values
   * 
   */
  public Board()
  {
   
    /* your code here */
    ArrayList<Integer> numbersToChoose = new ArrayList<Integer>();
    for(int i = 0; i < (tileValues.length); i++){
      numbersToChoose.add(i);
    }
    System.out.println(numbersToChoose);
    

    
    for(String animals : tileValues){
      int rowNumber = 0;
      int colNumber = 0;
      while(true){
      int randomNumber = (int) (Math.random()*numbersToChoose.size());
      //System.out.println(randomNumber);
      int placementNumber = numbersToChoose.get(randomNumber);
      //System.out.println(placementNumber);
      numbersToChoose.remove(randomNumber);
      numbersToChoose.add(placementNumber);

      //if the number has already been used
      while(placementNumber < 0){
        randomNumber = (int) (Math.random()*numbersToChoose.size());
        placementNumber = numbersToChoose.get(randomNumber);
        numbersToChoose.remove(randomNumber);
        numbersToChoose.add(placementNumber);
      }
    
      rowNumber = placementNumber/gameboard[0].length;
      colNumber = placementNumber - (rowNumber*gameboard[0].length);
      numbersToChoose.remove(numbersToChoose.size()-1);
      //System.out.println("("+rowNumber+","+colNumber+")");
      //System.out.println(numbersToChoose);
      
      break;
      }
    

      Tile t = new Tile(animals);
      gameboard[rowNumber][colNumber] = t;
      System.out.println(gameboard[rowNumber][colNumber]);
      gameboard[rowNumber][colNumber].hide();
      
        
    }
  
  }

 /** 
   * Returns a string representation of the board, getting the state of
   * each tile. If the tile is showing, displays its value, 
   * otherwise displays it as hidden.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return a string represetation of the board
   */
  public String toString()
  {
 
    /* your code here */
    String boardShown = "[ ";
    
    for(Tile[] row : gameboard){
      boardShown+="[ ";
      for(Tile col : row){
        if(col.matched()){
          boardShown+=col + ", ";
        }
        else if(!col.isShowingValue()){
          boardShown+=col.getHidden() + ", ";
        }
        else{
          boardShown+=col + ", ";
        }
      }
      boardShown+=" ] ";
    }
    boardShown+=" ] ";

    return boardShown;
  }

  /** 
   * Determines if the board is full of tiles that have all been matched,
   * indicating the game is over.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return true if all tiles have been matched, false otherwse
   */
  public boolean allTilesMatch()
  {

    /* your code  here */
    for(Tile[] rowT : gameboard){
      for(Tile colT : rowT){
        if(!colT.matched()){
          System.out.println(colT +" " + colT.matched());
          return false;
        }
      }
    }
    
    return true;
  }

  /** 
   * Sets the tile to show its value (like a playing card face up)
   * 
   * Preconditions:
   *   gameboard is populated with tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row the row value of Tile
   * @param column the column value of Tile
   */
  public void showValue (int row, int column)
  {
   
    /* your code here */
    //String valueShown = gameboard[row][column].getValue();
    gameboard[row][column].show();
  }  

  /** 
   * Checks if the Tiles in the two locations match.
   * 
   * If Tiles match, show Tiles in matched state and return a "matched" message
   * If Tiles do not match, re-hide Tiles (turn face down).
   * 
   * Preconditions:
   *   gameboard is populated with Tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row1 the row value of Tile 1
   * @param col1 the column value of Tile 1
   * @param row2 the row value of Tile 2
   * @param col2 the column vlue of Tile 2
   * @return a message indicating whether or not a match occured
   */
  public String checkForMatch(int row1, int col1, int row2, int col2)
  {
    String msg = "";

     /* your code here */
     String value1 = gameboard[row1][col1].getValue();
     String value2 = gameboard[row2][col2].getValue();
     if(value1.equals(value2)){
      gameboard[row1][col1].foundMatch();
      gameboard[row2][col2].foundMatch();
      msg = "matched";
     }
     else{
        gameboard[row1][col1].hide();
        gameboard[row2][col2].hide();
     }
    
     return msg;
  }

  /** 
   * Checks the provided values fall within the range of the gameboard's dimension
   * and that the tile has not been previously matched
   * 
   * @param rpw the row value of Tile
   * @param col the column value of Tile
   * @return true if row and col fall on the board and the row,col tile is unmatched, false otherwise
   */
  public boolean validateSelection(int row, int col)
  {

    /* your code here */
    int totalRows = gameboard.length;
    int totalColumns = gameboard[0].length;
    if(row > totalRows || row < 0){
      return false;
    }
    if(col > totalColumns || col < 0){
      return false;
    }
    if(gameboard[row][col].matched()){
      return false;
    }

    return true;
  }

}
