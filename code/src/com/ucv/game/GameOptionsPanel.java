package com.ucv.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.util.Timer;
import java.util.TimerTask;

public abstract class GameOptionsPanel extends JPanel {

	private int noOfXWins = 0;

	private int noOfOWins = 0;

	private int noOfEquals = 0;

	private JButton startGameButton;

	private int actualState;

	private int noOfSeconds = 0;

	private final Timer timer = new Timer("Timer");

	private static final int GAME_PAUSED = 0;

	private static final int GAME_STARTED = 1;

	private static final int GAME_ENDED = 2;

	public static final int RESULT_X_WIN = 0;

	public static final int RESULT_O_WIN = 1;

	public static final int RESULT_EQUALS = 2;

	private JLabel xWinsLabel = new JLabel("X Wins: 0");

	private JLabel oWinsLabel = new JLabel("O Wins: 0");

	private JLabel timeLabel = new JLabel("Time: -");

	private JLabel equalsLabel = new JLabel("Equals: 0");

	private JCheckBox checkGameDifficulty = new JCheckBox("Hard mode");

	private JRadioButton twoPlayerMode = new JRadioButton("Two players");

	private JRadioButton onePlayerMode = new JRadioButton("One player");

	protected GameOptionsPanel() {
		super(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		actualState = GAME_ENDED;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.PAGE_START;		
		this.add(timeLabel, gbc);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(actualState == GAME_STARTED) {
					noOfSeconds++;
					timeLabel.setText("Time: " + noOfSeconds + " (s)");
				}
			}
		}, 0, 1000);

		startGameButton = new JButton(new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(actualState) {
				case GAME_PAUSED -> {
					actualState = GAME_STARTED;
					startGameButton.setText("Pause");
					continueGame();
				}
				case GAME_STARTED -> {
					actualState = GAME_PAUSED;
					startGameButton.setText("Continue");
					pause();
				}
				case GAME_ENDED -> {
					actualState = GAME_STARTED;
					noOfSeconds = 0;
					startGameButton.setText("Pause");
					checkGameDifficulty.setEnabled(false);
					onePlayerMode.setEnabled(false);
					twoPlayerMode.setEnabled(false);
					startGame();
				}
				}
			}
		});
		startGameButton.setText("Play");
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy++;
		this.add(startGameButton, gbc);
		gbc.gridy++;
		this.add(xWinsLabel, gbc);
		gbc.gridy++;
		this.add(oWinsLabel, gbc);
		gbc.gridy++;
		this.add(equalsLabel, gbc);
		ButtonGroup radioButtons = new ButtonGroup();
		onePlayerMode.setSelected(true);
		radioButtons.add(onePlayerMode);
		radioButtons.add(twoPlayerMode);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy++;
		this.add(onePlayerMode, gbc);
		gbc.gridy++;
		gbc.insets.left = 5;
		this.add(checkGameDifficulty, gbc);
		gbc.gridy++;
		gbc.insets.left = 0;
		this.add(twoPlayerMode, gbc);
		onePlayerMode.setBackground(Color.WHITE);
		twoPlayerMode.setBackground(Color.WHITE);
		checkGameDifficulty.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(100, getPreferredSize().height));
		this.setBackground(Color.WHITE);
	}

	public abstract void startGame();

	public abstract void pause();

	public abstract void continueGame();

	public void endGame(final int result) {
		startGameButton.setText("Play again");
		checkGameDifficulty.setEnabled(true);
		onePlayerMode.setEnabled(true);
		twoPlayerMode.setEnabled(true);
		if(result == RESULT_X_WIN) {
			noOfXWins++;
		    xWinsLabel.setText("X Wins: " + noOfXWins);
		} else if(result == RESULT_O_WIN) {
			noOfOWins++;
		    oWinsLabel.setText("O Wins: " + noOfOWins);
		} else if(result == RESULT_EQUALS) {
			noOfEquals++;
			equalsLabel.setText("Equals: " + noOfEquals);
		}
		actualState = GAME_ENDED;
	}

	public boolean isTwoPlayerMode() {
		return twoPlayerMode.isSelected();
	}

	public boolean isHardDifficulty() {
		return checkGameDifficulty.isSelected();
	}
}
