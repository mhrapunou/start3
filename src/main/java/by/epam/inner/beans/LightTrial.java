package by.epam.inner.beans;

public class LightTrial extends Trial {
	
	private static final int PASS_MARK1 = 80;
	private static final int PASS_MARK2 = 70;
	
	public LightTrial() {
	}

	public LightTrial(String account, int mark1, int mark2) {
		super(account, mark1, mark2);
	}
	
	public LightTrial(LightTrial trial) {
		super(trial.getAccount(), trial.getMark1(), trial.getMark2());
	}
	
	@Override
	public LightTrial copy() {
		return new LightTrial(this);
	}
	
	@Override
	public boolean isPassed() {
		return getMark1() >= PASS_MARK1 && getMark2() >= PASS_MARK2;
	}

}
