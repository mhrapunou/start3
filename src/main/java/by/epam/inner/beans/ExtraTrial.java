package by.epam.inner.beans;

import static by.epam.inner.constants.Constants.*;

public class ExtraTrial extends Trial {
	
	private static final int EXTRA_PASS_MARK = 80;  
	private int mark3;
	
	public ExtraTrial() {
	}

	public ExtraTrial(String account, int mark1, int mark2, int mark3) {
		super(account, mark1, mark2);
		this.mark3 = mark3;
	}
	
	public ExtraTrial(ExtraTrial trial) {
		this(trial.getAccount(), trial.getMark1(), trial.getMark2(), trial.mark3);
	}

	public ExtraTrial(Trial trial, int mark3) {
		super(trial.getAccount(), trial.getMark1(), trial.getMark2());
		this.mark3 = mark3;
	}

	public int getMark3() {
		return mark3;
	}

	public void setMark3(int mark3) {
		this.mark3 = mark3;
	}
	
	@Override
	public ExtraTrial copy() {
		return new ExtraTrial(this);
	}
	
	@Override
	public void clearMarks() {
		super.clearMarks();
		mark3 = 0;
	}

	@Override
	public boolean isPassed() {
		return super.isPassed() && mark3 >= EXTRA_PASS_MARK;
	}

	@Override
	public String getMarksToString() {
		return super.getMarksToString() + CSV_DELIMITER + mark3;
	}

}
