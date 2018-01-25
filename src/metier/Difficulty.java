package metier;

public abstract class Difficulty {
	private int malus;
	private int bonus;
	private int ballzLimit;
	private int ballzLimitChance;
	
	public int getMalus() {
		return malus;
	}
	public void setMalus(int malus) {
		this.malus = malus;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getBallzLimit() {
		return ballzLimit;
	}
	public void setBallzLimit(int ballzLimit) {
		this.ballzLimit = ballzLimit;
	}
	public int getBallzLimitChance() {
		return ballzLimitChance;
	}
	public void setBallzLimitChance(int ballzLimitChance) {
		this.ballzLimitChance = ballzLimitChance;
	}
}
