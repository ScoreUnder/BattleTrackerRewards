package mc.stuu.battletrackerrewards;

import mc.stuu.battletrackerrewards.config.BTRConfig;

import java.text.MessageFormat;

public class BTRConfigValues {
	public static int defaultCurrencyValue;
	public static int maxCurrencyValue;
	public static int minimumCurrencyValue;
	public static int eloCurrencyMultiplier;
	public static int defaultElo;
	public static int currencyChange;
	public static MessageFormat currencyFormat;
	public static int killingSpree;
	public static int shutdownMultiplier;
	public static int shutdownMax;
	public static boolean currencyOnJoin;

	public static void reloadConfig() {
		defaultCurrencyValue = BTRConfig.getConfigInt("defaultCurrencyValue");
		maxCurrencyValue = BTRConfig.getConfigInt("maxCurrencyValue");
		minimumCurrencyValue = BTRConfig.getConfigInt("minimumCurrencyValue");
		eloCurrencyMultiplier = BTRConfig.getConfigInt("eloCurrencyMultiplier");
		defaultElo = 1250;
		currencyOnJoin = BTRConfig.getConfigString("currencyOnJoin").equalsIgnoreCase("yes");
		shutdownMax = BTRConfig.getConfigInt("shutdownMax");
		shutdownMultiplier = BTRConfig.getConfigInt("shutdownMultiplier");
		killingSpree = BTRConfig.getConfigInt("killingSpree");
		currencyFormat = new MessageFormat(BTRConfig.getConfigString("currencyFormat"));
		currencyChange = BTRConfig.getConfigInt("currencyChange");
	}

	public static String formatCurrency(long amount) {
		return currencyFormat.format(new Object[] {amount});
	}
}
