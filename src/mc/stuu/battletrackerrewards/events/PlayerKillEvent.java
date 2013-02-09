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
	public List winnerList; 
	public String[] winnerArray;
	public int winnerArrayLength;
	
	public void updateTeam(int edModifier){
		winnerList = winner.getMembers();
		winnerArray = (String[]) winnerList.toArray();
		winnerArrayLength = winnerArray.length;
		int i = 0;
		while ( i <= winnerArrayLength){
			winnerCurrency = BattleTrackerConfig.getCurrency(winnerArray[i]) + ( currencyChange * edModifier );
			winnerCurrency = winnerCurrency + currencyDefault;
			BattleTrackerConfig.updatePlayer(winnerArray[i], winnerCurrency);
		}
	}
	
	public void onWinStatChangeEvent(WinStatChangeEvent wsce){
		
		winner = wsce.getWinner();
		winnerName = winner.getName();
		winnerRating = winner.getRating();
		winnerStreak = winner.getStreak();
		loser = wsce.getLoser();
		loserName = loser.getName();
		loserRating =loser.getRating();
		loserGames = loser.getWins() + loser.getLosses();
		currencyChange = BTRConstants.currencyChange;
		currencyDefault = BTRConstants.defaultCurrencyValue;
		numberWinner = winner.getCount();
		
		if(numberWinner == 1){
		
			if(loserGames <= 10){
				
				winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				winnerCurrency = winnerCurrency + currencyDefault;
				BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
		
			}else if(loserGames > 10){
			
				if( winnerRating > loserRating ){
				
					eloDifference = winnerRating - loserRating;
					eloDifference = eloDifference / 100;
					winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				
					if(eloDifference < 1){
						winnerCurrency = winnerCurrency + currencyDefault;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 1 && eloDifference < 2){
						winnerCurrency = winnerCurrency + currencyDefault - currencyChange;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 2 && eloDifference < 3){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 2 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 3 && eloDifference < 4){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 3 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 4 && eloDifference < 5){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 4 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 5){
						winnerCurrency = winnerCurrency + currencyDefault - ( currencyChange * 5 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}
			
				}else if( loserRating > winnerRating ){
				
					eloDifference = loserRating - winnerRating;
					eloDifference = eloDifference / 100;
					winnerCurrency = BattleTrackerConfig.getCurrency(winnerName);
				
					if(eloDifference < 1){
						winnerCurrency = winnerCurrency + currencyDefault;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 1 && eloDifference < 2){
						winnerCurrency = winnerCurrency + currencyDefault + currencyChange;
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 2 && eloDifference < 3){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 2 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 3 && eloDifference < 4){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 3 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 4 && eloDifference < 5){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 4 );
						BattleTrackerConfig.updatePlayer(winnerName, winnerCurrency);
					}else if( eloDifference > 5){
						winnerCurrency = winnerCurrency + currencyDefault + ( currencyChange * 5 );
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
	
