package mc.stuu.battletrackerrewards.events;

import mc.alk.tracker.events.MaxRatingChangeEvent;
import mc.alk.tracker.objects.Stat;
import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

public class MaxEloEvent {
	
	public static String maxEloPlayer;
	public Stat playerStat;
	public static double maxElo;
	
	public void onMaxRatingChangeEvent(MaxRatingChangeEvent mrce){
		
		//Currency
		maxEloPlayer = mrce.getStat().getName();
		playerStat = mrce.getStat();
		maxElo = playerStat.getMaxRating();
		//Math modifications
		double eloCurrencyMultiplier = BTRConstants.eloCurrencyMultiplier;
		double defaultElo = BTRConstants.defaultElo;
		double increasedElo = ( maxElo + 50 ) - defaultElo;
		double eloMultiplier = increasedElo / 100;
		double battlePoints = eloMultiplier * eloCurrencyMultiplier;
		//Update player
		BTRPlayerConfig.updatePlayer(maxEloPlayer, battlePoints);
	}
}
