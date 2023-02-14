package config;

import io.github.cdimascio.dotenv.Dotenv;

/*
 *  Dotenv wrapper for env variables.
 *  Create a file named .env directly under the src folder.
 *
 *	In the file variables are stored one per line and in the following syntax
 *  SOME_KEY=some value 
 */
public class Config {
	private static Dotenv env = Dotenv.configure().load();

	public static String get(String key) {
		return env.get(key);
	}
}
