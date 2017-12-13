package app;

public class LevelofLoss {
	public int id;
	public String level;
	public String description;
	
	//������������
	//� �����������
	public LevelofLoss(int id, String level, String description) {
		this.id = id;
		this.level = level;
		this.description = description;
	}
	//�� ���������
	public LevelofLoss() {this(0, "", "");}
	//�����
	public LevelofLoss(LevelofLoss a) {
		this.id = a.getId();
		this.level = a.getLevel();
		this.description = a.getDescription();
	}
	
	//get-������
	public int getId() {
		return id;
	}

	public String getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}
	
	//��������������� ������
	@Override
	public String toString() {return level;}
	
}