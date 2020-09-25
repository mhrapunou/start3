package by.epam.inner.beans;

public class StrongTrial extends Trial {
	
	
	public StrongTrial() {
	}

	public StrongTrial(String account, int mark1, int mark2) {
		super(account, mark1, mark2);
	}
	
	public StrongTrial(StrongTrial trial) {
		super(trial.getAccount(), trial.getMark1(), trial.getMark2());
	}
	
	@Override
	public StrongTrial copy() {
		return new StrongTrial(this);
	}

	@Override
	public boolean isPassed() {
		final int SHIFT_OF_MULT = 1;
		return getMark1() >=  PASS_MARK - getMark2() << SHIFT_OF_MULT;
	}
}
