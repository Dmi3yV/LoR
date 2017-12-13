package app;

public class LevelofLoss {
	public int id;
	public String level;
	public String description;
	
	//конструкторы
	//с параметрами
	public LevelofLoss(int id, String level, String description) {
		this.id = id;
		this.level = level;
		this.description = description;
	}
	//по умолчанию
	public LevelofLoss() {this(0, "", "");}
	//копии
	public LevelofLoss(LevelofLoss a) {
		this.id = a.getId();
		this.level = a.getLevel();
		this.description = a.getDescription();
	}
	
	//get-методы
	public int getId() {
		return id;
	}

	public String getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}
	
	//переопределение метода
	@Override
	public String toString() {return level;}
	
}