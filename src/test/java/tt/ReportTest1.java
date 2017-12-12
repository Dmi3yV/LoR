package tt;

import static org.junit.Assert.*;

import org.junit.Test;

import app.LevelofLoss;
import app.LevelofProbabilityAttack;
import app.Main;

public class ReportTest1 {

	@Test
	public void testReport1() {
		Main m = new Main();
		LevelofLoss a = new LevelofLoss(1,"�����","�������� � �������������� ������� ������������ �������, ������� ������ �����������������, ��� � ��������������� ������� �� ��������� ��������");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(1,"����� ������","����� ����������� ������� �� ����� ���������. ����������� ����� - [0,0.15]");
		assertEquals(1,m.test(a, b));
	}

	@Test
	public void testReport2() {
		Main m = new Main();
		LevelofLoss a = new LevelofLoss(3,"C������","�������� � ������������ ������� ������������ ������� ��� ������������� ����� ��������� ��������");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(3,"�������","����������� ���������� ����� - (0.35,0.65]");
		assertEquals(2,m.test(a, b));
	}
	
	@Test
	public void testReport3() {
		Main m = new Main();
		LevelofLoss a = new LevelofLoss(5,"�����������","�������� � ����������� ������� ������������ ������� ��� � ������ ������ ��������� �������� �� �����");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(5,"����� �������","����� ����� ��������� ����� ���������. ����������� ����� - (0.85,1]");
		assertEquals(3,m.test(a, b));
	}
}
