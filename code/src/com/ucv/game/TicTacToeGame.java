package com.ucv.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class TicTacToeGame extends JFrame implements ActionListener {

	private static final Random RANDOM = new Random();

	private final JPanel titlePanel = new JPanel();

	private final JPanel cellsPanel = new JPanel();

	private JLabel titleLabel = new JLabel();

	private JButton[] cells = new JButton[9];

	boolean isPlayerOneTurn = false;

	/**
	 * <code>true</code> if is on mode with two player.
	 */
	boolean isTwoPlayerMode = false;
	
	boolean isHardDifficulty = false;
	
	boolean isGamePlaying = false;

	private GameOptionsPanel optionsPanel; 

	public TicTacToeGame(){
		//Setting the frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800,800);
		this.getContentPane().setBackground(new Color(50,50,50));
		this.setLayout(new BorderLayout());

		//Setting labels
		titleLabel.setBackground(new Color(25,25,25));
		titleLabel.setForeground(new Color(25,255,0));
		titleLabel.setFont(new Font("Ink Free",Font.BOLD,75));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setText("Tic-Tac-Toe");
		titleLabel.setOpaque(true);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0,0,800,100);

		cellsPanel.setLayout(new GridLayout(3,3));
		cellsPanel.setBackground(new Color(150,150,150));


		for(int i=0;i<9;i++) {
			cells[i] = new JButton();
			cellsPanel.add(cells[i]);
			cells[i].setFont(new Font("MV Boli",Font.BOLD,120));
			cells[i].setFocusable(false);
			cells[i].setEnabled(false);
		}

		titlePanel.add(titleLabel);
		this.add(titlePanel,BorderLayout.NORTH);
		optionsPanel = new GameOptionsPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void startGame() {
				isGamePlaying = true;
				cellsPanel.removeAll();
				for(int i = 0; i < 9; i++) {
					cells[i] = new JButton();
					cellsPanel.add(cells[i]);
					cells[i].setFont(new Font("MV Boli",Font.BOLD,120));
					cells[i].setFocusable(false);
					cells[i].setEnabled(true);
					cells[i].addActionListener(TicTacToeGame.this);
				}
				isTwoPlayerMode = this.isTwoPlayerMode();
				isHardDifficulty = this.isHardDifficulty();
				firstTurn();
			}

			@Override
			public void pause() {
				isGamePlaying = false;
				setCellsEnabled(false);
			}

			@Override
			public void continueGame() {
				isGamePlaying = true;
				setCellsEnabled(true);
			}
		};
		this.add(optionsPanel,BorderLayout.EAST);
		titleLabel.setText("Welcome to TicTacToe!");
		this.add(cellsPanel);

	}


	private void botTurn() {
		
		if(isHardDifficulty && doBetterMove("O")) {
			return; 
		}
		
		while(isGamePlaying) {
			final int index = RANDOM.nextInt(0, 9);
			if(cells[index].getText().isEmpty()) {
				cells[index].doClick();
				break;
			}
		}
	}


	private boolean doBetterMove(String symbol) {
		boolean toReturn = false;
		if(cells[0].getText().equals(symbol) && cells[1].getText().equals(symbol) && cells[2].getText().isEmpty()) {
			cells[2].doClick();
			toReturn = true;
		} else if(cells[0].getText().equals(symbol) && cells[2].getText().equals(symbol) && cells[1].getText().isEmpty()) {
			cells[1].doClick();
			toReturn = true;
		} else if(cells[1].getText().equals(symbol) && cells[2].getText().equals(symbol) && cells[0].getText().isEmpty()) {
			cells[0].doClick();
			toReturn = true;
		} else if(cells[3].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[5].getText().isEmpty()) {
			cells[5].doClick();
			toReturn = true;
		} else if(cells[5].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[3].getText().isEmpty()) {
			cells[3].doClick();
			toReturn = true;
		} else if(cells[3].getText().equals(symbol) && cells[5].getText().equals(symbol) && cells[4].getText().isEmpty()) {
			cells[4].doClick();
			toReturn = true;
		} else if(cells[6].getText().equals(symbol) && cells[7].getText().equals(symbol) && cells[8].getText().isEmpty()) {
			cells[8].doClick();
			toReturn = true;
		} else if(cells[8].getText().equals(symbol) && cells[7].getText().equals(symbol) && cells[6].getText().isEmpty()) {
			cells[6].doClick();
			toReturn = true;
		} else if(cells[6].getText().equals(symbol) && cells[8].getText().equals(symbol) && cells[7].getText().isEmpty()) {
			cells[7].doClick();
			toReturn = true;
		} else if(cells[0].getText().equals(symbol) && cells[3].getText().equals(symbol) && cells[6].getText().isEmpty()) {
			cells[6].doClick();
			toReturn = true;
		} else if(cells[6].getText().equals(symbol) && cells[3].getText().equals(symbol) && cells[0].getText().isEmpty()) {
			cells[0].doClick();
			toReturn = true;
		} else if(cells[0].getText().equals(symbol) && cells[6].getText().equals(symbol) && cells[3].getText().isEmpty()) {
			cells[3].doClick();
			toReturn = true;
		} else if(cells[1].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[7].getText().isEmpty()) {
			cells[7].doClick();
			toReturn = true;
		} else if(cells[7].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[1].getText().isEmpty()) {
			cells[1].doClick();
			toReturn = true;
		} else if(cells[1].getText().equals(symbol) && cells[7].getText().equals(symbol) && cells[4].getText().isEmpty()) {
			cells[4].doClick();
			toReturn = true;
		} else if(cells[2].getText().equals(symbol) && cells[5].getText().equals(symbol) && cells[8].getText().isEmpty()) {
			cells[8].doClick();
			toReturn = true;
		} else if(cells[8].getText().equals(symbol) && cells[5].getText().equals(symbol) && cells[2].getText().isEmpty()) {
			cells[2].doClick();
			toReturn = true;
		} else if(cells[2].getText().equals(symbol) && cells[8].getText().equals(symbol) && cells[5].getText().isEmpty()) {
			cells[5].doClick();
			toReturn = true;
		} else if(cells[0].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[8].getText().isEmpty()) {
			cells[8].doClick();
			toReturn = true;
		} else if(cells[8].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[0].getText().isEmpty()) {
			cells[0].doClick();
			toReturn = true;
		} else if(cells[0].getText().equals(symbol) && cells[8].getText().equals(symbol) && cells[4].getText().isEmpty()) {
			cells[4].doClick();
			toReturn = true;
		}  else if(cells[2].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[6].getText().isEmpty()) {
			cells[6].doClick();
			toReturn = true;
		}  else if(cells[6].getText().equals(symbol) && cells[4].getText().equals(symbol) && cells[2].getText().isEmpty()) {
			cells[2].doClick();
			toReturn = true;
		} else if(cells[2].getText().equals(symbol) && cells[6].getText().equals(symbol) && cells[4].getText().isEmpty()) {
			cells[4].doClick();
			toReturn = true;
		}
		
		return "O".equals(symbol) ? doBetterMove("X") : toReturn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!isGamePlaying) {
			return;
		}
		if(!isTwoPlayerMode) {
			for(int i = 0; i < 9; i++) {
				if(e.getSource() == cells[i] && cells[i].getText().isEmpty()) {
					if(isPlayerOneTurn) {
						cells[i].setForeground(new Color(255, 0, 0));
						cells[i].setText("X");
						isPlayerOneTurn = false;
						titleLabel.setText("O turn");
					} else {
						cells[i].setForeground(new Color(24, 0, 255));
						cells[i].setText("O");
						isPlayerOneTurn = true;
						titleLabel.setText("X turn");
					}
					check();
					if(!isPlayerOneTurn) {
						SwingUtilities.invokeLater(() -> {
							try {
								Thread.sleep(500);
								botTurn();
							} catch(Exception ex) {
                                ex.printStackTrace();
							}
						});
					}
				}
			}
		} else {
			for(int i = 0;i < 9; i++) {
				if(e.getSource()==cells[i]) {
					if(isPlayerOneTurn) {
						if(cells[i].getText().isEmpty()) {
							cells[i].setForeground(new Color(255,0,0));
							cells[i].setText("X");
							isPlayerOneTurn = false;
							titleLabel.setText("O turn");
							check();
						}
					}
					else {
						if(cells[i].getText().isEmpty()) {
							cells[i].setForeground(new Color(0,0,255));
							cells[i].setText("O");
							isPlayerOneTurn = true;
							titleLabel.setText("X turn");
							check();
						}
					}
				}			
			}

		}
	}
	
	private void firstTurn() {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(RANDOM.nextInt(2) == 0) {
			isPlayerOneTurn=true;
			titleLabel.setText("X turn");
		}
		else {
			isPlayerOneTurn = false;
			titleLabel.setText("O turn");
			if(!isTwoPlayerMode) {
				botTurn();
			}

		}

	}
	
	/**
	 * Checks if the game is over(X wins, O wins or equal match).
	 */
	private void check() {
	    //check X win conditions
	    if (checkAllEquals("X", cells[0].getText(), cells[1].getText(), cells[2].getText())) {
	        xWins(0, 1, 2);
	    } else if (checkAllEquals("X", cells[3].getText(), cells[4].getText(), cells[5].getText())) {
	        xWins(3, 4, 5);
	    } else if (checkAllEquals("X", cells[6].getText(), cells[7].getText(), cells[8].getText())) {
	        xWins(6, 7, 8);
	    } else if (checkAllEquals("X", cells[0].getText(), cells[3].getText(), cells[6].getText())) {
	        xWins(0, 3, 6);
	    } else if (checkAllEquals("X", cells[1].getText(), cells[4].getText(), cells[7].getText())) {
	        xWins(1, 4, 7);
	    } else if (checkAllEquals("X", cells[2].getText(), cells[5].getText(), cells[8].getText())) {
	        xWins(2, 5, 8);
	    } else if (checkAllEquals("X", cells[0].getText(), cells[4].getText(), cells[8].getText())) {
	        xWins(0, 4, 8);
	    } else if (checkAllEquals("X", cells[2].getText(), cells[4].getText(), cells[6].getText())) {
	        xWins(2, 4, 6);
	    }
	    //check O win conditions
	    else if (checkAllEquals("O", cells[0].getText(), cells[1].getText(), cells[2].getText())) {
	        oWins(0, 1, 2);
	    } else if (checkAllEquals("O", cells[3].getText(), cells[4].getText(), cells[5].getText())) {
	        oWins(3, 4, 5);
	    } else if (checkAllEquals("O", cells[6].getText(), cells[7].getText(), cells[8].getText())) {
	        oWins(6, 7, 8);
	    } else if (checkAllEquals("O", cells[0].getText(), cells[3].getText(), cells[6].getText())) {
	        oWins(0, 3, 6);
	    } else if (checkAllEquals("O", cells[1].getText(), cells[4].getText(), cells[7].getText())) {
	        oWins(1, 4, 7);
	    } else if (checkAllEquals("O", cells[2].getText(), cells[5].getText(), cells[8].getText())) {
	        oWins(2, 5, 8);
	    } else if (checkAllEquals("O", cells[0].getText(), cells[4].getText(), cells[8].getText())) {
	        oWins(0, 4, 8);
	    } else if (checkAllEquals("O", cells[2].getText(), cells[4].getText(), cells[6].getText())) {
	        oWins(2, 4, 6);
	    } else if(Arrays.stream(cells).noneMatch(cell -> !"O".equals(cell.getText()) && !"X".equals(cell.getText()))) {
				equalGame();
		}

	}

	
	private void equalGame() {
		setCellsEnabled(false);
		titleLabel.setText("It's a tie!");
		isGamePlaying = false;
		optionsPanel.endGame(GameOptionsPanel.RESULT_EQUALS);
	}

	private  void xWins(int a,int b,int c) {
		cells[a].setBackground(Color.GREEN);
		cells[b].setBackground(Color.GREEN);
		cells[c].setBackground(Color.GREEN);

		setCellsEnabled(false);
		titleLabel.setText("X wins");
		isGamePlaying = false;
		optionsPanel.endGame(GameOptionsPanel.RESULT_X_WIN);
	}

	private void oWins(int a,int b,int c) {
		cells[a].setBackground(Color.GREEN);
		cells[b].setBackground(Color.GREEN);
		cells[c].setBackground(Color.GREEN);

		setCellsEnabled(false);
		titleLabel.setText("O wins");
		isGamePlaying = false;
		optionsPanel.endGame(GameOptionsPanel.RESULT_O_WIN);
	}
	
	private void setCellsEnabled(boolean enableCells) {
		Arrays.stream(cells).forEach(cell -> cell.setEnabled(enableCells));
	}

	
	private boolean checkAllEquals(String symbol, String...cells) {
		return Arrays.stream(cells).allMatch(symbol::equals);
	}
}



