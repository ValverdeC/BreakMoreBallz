package metier;

public class EasyDiffculty extends Difficulty {

	public EasyDiffculty() {
		super();
		this.setBallzLimit(6);
		this.setBallzLimitChance(33);
		this.setBonus(100);
		this.setMalus(0);
	}
}
