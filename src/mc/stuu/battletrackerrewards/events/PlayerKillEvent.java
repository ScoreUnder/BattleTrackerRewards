package mc.stuu.battletrackerrewards.events;

import java.util.List;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.BattleTrackerConfig;
import mc.alk.tracker.events.WinStatChangeEvent;
import mc.alk.tracker.objects.Stat;



public class PlayerKillEvent {
	
	public Stat winner;
	public Stat loser;
	public String winnerName;
	public String loserName;
	public int loserRating;
	public int winnerRating;
	public int winnerStreak;
	public int loserStreak;
	public int loserGames;
	public int winnerCurrency;
	public float eloDifference;
	public int currencyChange;
	public int currencyDefault;
	public int numberWinner;
	@SuppressWarnings("rawtypes")
	public List winnerList; 
	public String[] winnerArray;
	public int winnerArrayLength;
	public int shutdownBonus;
	
	
	//Takes "winner" list and in succession updates each player on the lists Currency
	public void updateTeam(int edModifier){
		winnerList = winner.getMembers();
		winnerArray = (String[]) winnerList.toArray();
		winnerArrayLength = winnerArray.length;
		int i = 0;
		while ( i <= winnerArrayLength){
			winnerCurrency = BattleTrackerConfig.getCurrency(winnerArray[i]); // edModifier = elo difference modifier
			winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * edModifier ) + shutdownBonus;
			BattleTrackerConfig.updatePlayer(winnerArray[i], winnerCurrency);
		}
	}
	
	
	
	public void onWinStatChangeEvent(WinStatChangeEvent wsce){
		
		//Setting variables
		winner = wsce.getWinner();
		winnerName = winner.getName();
		winnerRating = winner.getRating();
		winnerStreak = winner.getStreak();
		loser = wsce.getLoser();
		loserName = loser.getName();
		loserRating =loser.getRating();
		loserGames = loser.getWins() + loser.getLosses();
		loserStreak = loser.getStreak();
		currencyChange = BTRConstants.currencyChange;
		currencyDefault = BTRConstants.defaultCurrencyValue;
		numberWinner = winner.getCount();
		
		
		//Loser killing spree is used to calculate any bonus currency
		if(loserStreak >= BTRConstants.killingSpree){
			shutdownBonus = ( loserStreak + 1 ) - BTRConstants.killingSpree;
			if(shutdownBonus > BTRConstants.shutdownMax){
				shutdownBonus = BTRConstants.shutdownMax * BTRConstants.shutdownMultiplier;
			}else if(shutdownBonus < BTRConstants.shutdownMax){
				shutdownBonus = shutdownBonus * BTRConstants.shutdownMultiplier;
			}
		}else{
			shutdownBonus = 0;
		}
		
		
		if(numberWinner == 1){
		
			
			if(loserGames <= 10){
				
				winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				winnerCurrency = winnerCurrency + currencyDefault + shutdownBonus;
				BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
		
			}else if(loserGames > 10){
				
				if( winnerRating > loserRating ){
				
					eloDifference = winnerRating - loserRating;
					eloDifference = eloDifference / 100;
					winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				
					if(eloDifference < 1){
						winnerCurrency = winnerCurrency + currencyDefault + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 1 && eloDifference < 2){
						winnerCurrency = winnerCurrency + currencyDefault - currencyChange + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 2 && eloDifference < 3){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 2 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 3 && eloDifference < 4){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 3 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 4 && eloDifference < 5){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 4 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 5){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 5 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}
			
				}else if( loserRating > winnerRating ){
				
					eloDifference = loserRating - winnerRating;
					eloDifference = eloDifference / 100;
					winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				
					if(eloDifference < 1){
						winnerCurrency = winnerCurrency + currencyDefault + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 1 && eloDifference < 2){
						winnerCurrency = winnerCurrency + currencyDefault + currencyChange + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 2 && eloDifference < 3){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 2 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 3 && eloDifference < 4){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 3 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 4 && eloDifference < 5){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 4 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 5){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 5 ) + shutdownBonus;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}
				}
			}
		}else if(numberWinner > 1){
			winnerList = winner.getMembers();
			winnerArray = (String[]) winnerList.toArray();
			winnerArrayLength = winnerArray.length;
			
			if(loserGames <= 10){
				updateTeam(0);
				}
				
				
		
			}else if(loserGames > 10){
			
				if( winnerRating > loserRating ){
				
					eloDifference = winnerRating - loserRating;
					eloDifference = eloDifference / 100;
					
				
					if(eloDifference < 1){
						updateTeam(0);
					}else if( eloDifference > 1 && eloDifference < 2){
						updateTeam(-1);
					}else if( eloDifference > 2 && eloDifference < 3){
						updateTeam(-2);
					}else if( eloDifference > 3 && eloDifference < 4){
						updateTeam(-3);
					}else if( eloDifference > 4 && eloDifference < 5){
						updateTeam(-4);
					}else if( eloDifference > 5){
						updateTeam(-5);
					}
			
				}else if( loserRating > winnerRating ){
				
					eloDifference = loserRating - winnerRating;
					eloDifference = eloDifference / 100;
					winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
					updateTeam(0);
					
					}else if( eloDifference > 1 && eloDifference < 2){
						updateTeam(1);
					}else if( eloDifference > 2 && eloDifference < 3){
						updateTeam(2);
					}else if( eloDifference > 3 && eloDifference < 4){
						updateTeam(3);
					}else if( eloDifference > 4 && eloDifference < 5){
						updateTeam(4);
					}else if( eloDifference > 5){
						updateTeam(5);
					}
			}
	}
}
	
