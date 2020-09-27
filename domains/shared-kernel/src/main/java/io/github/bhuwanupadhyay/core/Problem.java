package io.github.bhuwanupadhyay.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface Problem {

  static void notNull(Object value) {
    if (value == null) {
      throw new IllegalArgumentException("Null value is not allowed!");
    }
  }

  static void raiseIfNull(Object value, Supplier<String> message) {
    notNull(message);
    if (value == null) {
      throw new BadRequestException(new SimpleProblem(message.get(), message.get()));
    }
  }

  static void raiseIfEmpty(String value, Supplier<String> message) {
    notNull(message);
    raiseIfNull(value, message);
    if (value.isBlank()) {
      throw new BadRequestException(new SimpleProblem(message.get(), message.get()));
    }
  }

  static void raiseIfNotEmpty(List<Problem> problems) {
    if (problems != null && !problems.isEmpty()) {
      throw new BadRequestException(new ArrayList<>(problems));
    }
  }

  String getMessage();

  String getPropertyPath();
}
