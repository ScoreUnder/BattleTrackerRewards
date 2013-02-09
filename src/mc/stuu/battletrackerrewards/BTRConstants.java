package mc.stuu.battletrackerrewards;

import mc.stuu.battletrackerrewards.config.BTRConfig;

public class BTRConstants {
	//These are assigned right now, but will be changed when I get round to creating a config so all these values are changeable.
	public static int defaultCurrencyValue = BTRConfig.getConfigInt("defaultCurrencyValue");
	public static int maxCurrencyValue = BTRConfig.getConfigInt("maxCurrencyValue");
	public static int minimumCurrencyValue = BTRConfig.getConfigInt("minimumCurrencyValue");
	public static int eloCurrencyMultiplier = BTRConfig.getConfigInt("eloCurrencyMultiplier");
	public static int defaultElo = 1250;
	public static int currencyChange = BTRConfig.getConfigInt("currencyChange");;		
	public static String currencyName = BTRConfig.getConfigString("currencyName");;
	public static int killingSpree = BTRConfig.getConfigInt("killingSpree");
	public static int shutdownMultiplier = BTRConfig.getConfigInt("shutdownMultiplier");;
	public static int shutdownMax = BTRConfig.getConfigInt("shutdownMax");;
	public static String currencyOnJoin = BTRConfig.getConfigString("currencyOnJoin");
	
}
