package config;

import io.github.cdimascio.dotenv.Dotenv;

/*
 *  Singleton pointing to dotenv for fetching env variables
 */
public class Config {
	private static Dotenv env = Dotenv.configure().load();

	public static String get(String key) {
		return env.get(key);
	}
}
