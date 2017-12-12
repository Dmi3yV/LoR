package app;

public class LevelofProbabilityAttack {
	public int id;
	public String level;
	public String description;

	public LevelofProbabilityAttack(int id, String level, String description) {
		this.id = id;
		this.level = level;
		this.description = description;
	}
	
	public LevelofProbabilityAttack() {this(0, "", "");}
	
	public LevelofProbabilityAttack(LevelofProbabilityAttack a) {
		this.id = a.getId();
		this.level = a.getLevel();
		this.description = a.getDescription();
	}
	
	
	public int getId() {
		return id;
	}

	public String getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {return level;}
	
}
