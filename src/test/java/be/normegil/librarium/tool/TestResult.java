package be.normegil.librarium.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestResult {

	private boolean equals;

	private String message;

	private Object expected;

	private Object tested;

	private Object difference;

	private Collection<TestResult> subTestResult = new ArrayList<>();

	public TestResult(final Builder builder) {
		equals = builder.equals;
		message = builder.message;
		expected = builder.expected;
		tested = builder.tested;
		difference = builder.difference;
		subTestResult.addAll(builder.subTestResults);
	}

	public static Builder builder() {
		return new Builder();
	}

	public boolean isEquals() {
		return equals;
	}

	public String getMessage() {
		return message == null ? "" : message;
	}

	public Object getTested() {
		return tested;
	}

	public Object getDifference() {
		return difference;
	}

	public Collection<TestResult> getTestResults() {
		return new ArrayList<>(subTestResult);
	}

	public static class Builder {
		private boolean equals;
		private String message;
		private Object expected;
		private Object tested;
		private Object difference;
		private List<TestResult> subTestResults = new ArrayList<>();

		public Builder setEquals(final boolean equals) {
			this.equals = equals;
			return this;
		}

		public Builder setMessage(final String message) {
			this.message = message;
			return this;
		}

		public Builder setExpected(final Object expected) {
			this.expected = expected;
			return this;
		}

		public Builder setTested(final Object tested) {
			this.tested = tested;
			return this;
		}

		public Builder setDifference(final Object difference) {
			this.difference = difference;
			return this;
		}

		public Builder addAllSubTestResults(final Collection<TestResult> subTestResults) {
			for (TestResult subTestResult : subTestResults) {
				addSubTestResult(subTestResult);
			}
			return this;
		}

		public Builder addSubTestResult(final TestResult subTestResult) {
			subTestResults.add(subTestResult);
			return this;
		}

		public TestResult build() {
			return new TestResult(this);
		}
	}
}
