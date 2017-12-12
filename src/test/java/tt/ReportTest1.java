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
		LevelofLoss a = new LevelofLoss(1,"Малый","Приводит к незначительным потерям материальных активов, которые быстро восстанавливаются, или к незначительному влиянию на репутацию компании");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(1,"Очень низкий","Атака практически никогда не будет проведена. Вероятность атаки - [0,0.15]");
		assertEquals(1,m.test(a, b));
	}

	@Test
	public void testReport2() {
		Main m = new Main();
		LevelofLoss a = new LevelofLoss(3,"Cредний","Приводит к существенным потерям материальных активов или значительному урону репутации компании");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(3,"Средний","Вероятность проведения атаки - (0.35,0.65]");
		assertEquals(2,m.test(a, b));
	}
	
	@Test
	public void testReport3() {
		Main m = new Main();
		LevelofLoss a = new LevelofLoss(5,"Критический","Приводит к критическим потерям материальных активов или к полной потере репутации компании на рынке");
		LevelofProbabilityAttack b = new LevelofProbabilityAttack(5,"Очень высокий","Атака почти наверняка будет проведена. Вероятность атаки - (0.85,1]");
		assertEquals(3,m.test(a, b));
	}
}
