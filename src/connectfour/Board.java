/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

/**
 *
 * @author david
 */
public class Board {
    
    private Token[][] board;
    private int rows;
    private int columns;
    private int[] top;
    
    public Board() {
        board = new Token[6][7];
        top = new int[7];
        rows = 6;
        columns = 7;
        
        for (int i = 0; i < top.length; i++) {
            top[i] = 0;
        }
    }
    
    public Board(int rows, int columns) {
        board = new Token[rows][columns];
        top = new int[columns];
        this.rows = rows;
        this.columns = columns;
        
        for (int i = 0; i < top.length; i++) {
            top[i] = 0;
        }
    }
    
    public boolean drop(int column, Token token) {
        if (token == Token.NULL) {
            return false;
        }
        
        if (column >= columns || column < 0) {
            return false;
        }
        
        if (top[column] == rows - 1) {
            return false;
        }
        
        board[top[column]][column] = token;
        top[column]++;
        
        return true;
    }
    
    public boolean isFull() {
        for (int i = 0; i < columns; i++) {
            if (top[i] < rows - 1) {
                return false;
            }
        }
        
        return true;
    }
    
    public Token winner() {
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final Token cur = board[i][j];
                
                if (cur == Token.NULL) {
                    continue;
                } 
                
                int count = 1;

                // Top
                for (int row = i + 1; row < rows && row < i + 4 && count < 4; row++) {
                    if (cur == board[row][j]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
                
                // Bottom
                count = 1;
                for (int row = i - 1; row >= 0 && row > i - 4 && count < 4; row--) {
                    if (cur == board[row][j]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
                
                // Right
                count = 1;
                for (int col = j + 1; col < columns && col < j + 4 && count < 4; col++) {
                    if (cur == board[i][col]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
                
                // Left
                count = 1;
                for (int col = j - 1; col >= 0 && col > j - 4 && count < 4; col--) {
                    if (cur == board[i][col]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
                
                // Top and Right
                count = 1;
                for (int row = i + 1, col = j + 1; row < rows && row < i + 4 && col < columns && col < j + 4 && count < 4; row++, col++) {
                    if (cur == board[row][col]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
                
                // Bottom and Right
                count = 1;
                for (int row = i - 1, col = j + 1; row >= 0 && row > i - 4 && col < columns && col < j + 4 && count < 4; row--, col++) {
                    if (cur == board[row][col]) {
                        count++;
                    }
                }
                if (count == 4) {
                    return cur;
                }
            }
        }
        
        return Token.NULL;
    }
}