package mc.stuu.battletrackerrewards.events;

import mc.alk.tracker.events.MaxRatingChangeEvent;
import mc.alk.tracker.objects.Stat;
import mc.stuu.battletrackerrewards.BTRConfigValues;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MaxEloEvent implements Listener {

	@EventHandler
	public void onMaxRatingChangeEvent(MaxRatingChangeEvent mrce){
		
		//Currency
		String maxEloPlayer = mrce.getStat().getName();
		Stat playerStat = mrce.getStat();
		double maxElo = playerStat.getMaxRating();
		//Math modifications
		double eloCurrencyMultiplier = BTRConfigValues.eloCurrencyMultiplier;
		double defaultElo = BTRConfigValues.defaultElo;
		double increasedElo = ( maxElo + 50 ) - defaultElo;
		double eloMultiplier = increasedElo / 100;
		long battlePoints = (long) (eloMultiplier * eloCurrencyMultiplier);
		//Update player

		// TODO bug? should be adding?
		BTRPlayerConfig.getConfigFor(maxEloPlayer).setCurrency(battlePoints);
	}
}
