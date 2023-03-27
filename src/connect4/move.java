/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author Rawan_Zanabeet
 */
public class move {
	int move;
	int score;
	
	public void setScore(int scoreSet) {
		score = scoreSet;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setMove(int moveSet) {
		move = moveSet;
	}
	
	public int getMove() {
		return move;
	}
}
