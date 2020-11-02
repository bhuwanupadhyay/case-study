package io.github.bhuwanupadhyay.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BadRequestException extends RuntimeException {

	private final List<Problem> problems = new ArrayList<>();

	public BadRequestException(Problem problem) {
		if (problem != null) {
			problems.add(problem);
		}
	}

	public BadRequestException(List<Problem> problems) {
		if (problems != null) {
			this.problems.addAll(problems);
		}
	}

	public List<Problem> getProblems() {
		return Collections.unmodifiableList(problems);
	}

}
