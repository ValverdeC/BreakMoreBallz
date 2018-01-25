package metier;

public class NormalDiffculty extends Difficulty {

	public NormalDiffculty() {
		super();
		this.setBallzLimit(7);
		this.setBallzLimitChance(50);
		this.setBonus(33);
		this.setMalus(15);
	}
}
