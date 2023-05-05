package config;

import io.github.cdimascio.dotenv.Dotenv;

/*
 * Dotenv-kääre ympäristömuuttujille.
 * Luo tiedosto nimeltä .env suoraan src-kansion alle.
 * Tiedostossa muuttujat tallennetaan yksi per rivi seuraavassa syntaksissa:
 * SOME_KEY=jokin arvo
 */
public class Config {
	private static Dotenv env = Dotenv.configure().load();

	public static String get(String key) {
		return env.get(key);
	}

	public static String get(String key, String defaultValue) {
		return env.get(key, defaultValue);
	}
}
