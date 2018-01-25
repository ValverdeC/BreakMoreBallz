package metier;

public class HardDiffculty extends Difficulty {

	public HardDiffculty() {
		super();
		this.setBallzLimit(9);
		this.setBallzLimitChance(100);
		this.setBonus(0);
		this.setMalus(33);
	}
}
