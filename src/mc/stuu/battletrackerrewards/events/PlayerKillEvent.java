package mc.stuu.battletrackerrewards.events;

import mc.alk.tracker.events.WinStatChangeEvent;
import mc.alk.tracker.objects.Stat;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static mc.stuu.battletrackerrewards.BTRConfigValues.*;

public class PlayerKillEvent implements Listener {

	//Takes "winner" list and in succession updates each player on the lists Currency
	private void updateTeam(Stat winner, long edModifier, long shutdownBonus) {
		long incAmt = defaultCurrencyValue + currencyChange * edModifier + shutdownBonus;
		for (String winnerName : winner.getMembers()) {
			BTRPlayerConfig config = BTRPlayerConfig.getConfigFor(winnerName);
			long winnerCurrency = config.getCurrency(); // edModifier = elo difference modifier
			winnerCurrency += incAmt;
			config.setCurrency(winnerCurrency);
		}
	}

	@EventHandler
	public void onWinStatChangeEvent(WinStatChangeEvent wsce) {

		//Setting variables
		Stat winner = wsce.getWinner();
		String winnerName = winner.getName();
		int winnerRating = winner.getRating();
		int winnerStreak = winner.getStreak();
		Stat loser = wsce.getLoser();
		String loserName = loser.getName();
		int loserRating = loser.getRating();
		int loserGames = loser.getWins() + loser.getLosses();
		int loserStreak = loser.getStreak();
		int numberWinner = winner.getCount();

		//Loser killing spree is used to calculate any bonus currency
		int shutdownBonus;
		if (loserStreak >= killingSpree) {
			shutdownBonus = shutdownMultiplier * Math.min((loserStreak + 1) - killingSpree, shutdownMax);
		} else {
			shutdownBonus = 0;
		}

		if (numberWinner == 1) {
			if (loserGames <= 10) {
				BTRPlayerConfig config = BTRPlayerConfig.getConfigFor(winnerName);
				long winnerCurrency = config.getCurrency();
				winnerCurrency += defaultCurrencyValue + shutdownBonus;
				config.setCurrency(winnerCurrency);
			} else {
				long eloDifference = getEloDifference(loserRating, winnerRating);
				BTRPlayerConfig config = BTRPlayerConfig.getConfigFor(winnerName);
				long winnerCurrency = config.getCurrency() + defaultCurrencyValue + currencyChange * eloDifference + shutdownBonus;
				config.setCurrency(winnerCurrency);
			}
		} else if (numberWinner > 1) {
			if (loserGames <= 10) {
				updateTeam(winner, 0, shutdownBonus);
			}
		} else if (loserGames > 10) {
			int eloDifference = getEloDifference(loserRating, winnerRating);
			updateTeam(winner, eloDifference, shutdownBonus);
		}
	}

	private int getEloDifference(int loser, int winner) {
		int eloDifference = loser - winner;
		return Math.min(5, Math.max(-5, eloDifference / 100));
	}
}