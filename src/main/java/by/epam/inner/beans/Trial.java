package by.epam.inner.beans;

import static by.epam.inner.constants.Constants.*;

public class Trial {
	
		protected final static int PASS_MARK = 120;

		private String account;
		private int mark1;
		private int mark2;
		
		public Trial() {
		}

		public Trial(String account, int mark1, int mark2) {
			super();
			this.account = account;
			this.mark1 = mark1;
			this.mark2 = mark2;
		}
		
		public Trial(Trial trial) {
			this(trial.account, trial.mark1, trial.mark2);
		}
		
		public String getAccount() {
			return account;
		}

		public int getMark1() {
			return mark1;
		}

		public int getMark2() {
			return mark2;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public void setMark1(int mark1) {
			this.mark1 = mark1;
		}

		public void setMark2(int mark2) {
			this.mark2 = mark2;
		}
		
		public void clearMarks() {
			mark1 = 0;
			mark2 = 0;
		}
		
		public Trial copy() {
			return new Trial(this);
		}

		public boolean isPassed() {
			return mark1 + mark2 >= PASS_MARK;
		}

		protected String getMarksToString() {
			return mark1 + CSV_DELIMITER + mark2;
		}

		@Override
		public String toString() {
			return account + CSV_DELIMITER + getMarksToString() + CSV_DELIMITER + isPassed();
		}

}
