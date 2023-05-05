package model.kide;

/**
 * KideApp palveluntarjoajan tapahtuman hinnalle POJO
 */
public class KideAppEventPrice {
	private String eur;

	public String getEur() {
		return eur;
	}

	public void setEur(String eur) {
		this.eur = eur;
	}

	@Override
	public String toString() {
		return "ClassPojo [eur = " + eur + "]";
	}
}