package io.github.bhuwanupadhyay.core;

import java.util.Objects;

public class SimpleProblem implements Problem {

  private final String propertyPath;
  private final String message;

  public SimpleProblem(String propertyPath, String message) {
    this.message = message;
    this.propertyPath = propertyPath;
  }

  @Override public String getMessage() {
    return message;
  }

  @Override public String getPropertyPath() {
    return propertyPath;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SimpleProblem that = (SimpleProblem) o;
    return Objects.equals(propertyPath, that.propertyPath) &&
        Objects.equals(message, that.message);
  }

  @Override public int hashCode() {
    return Objects.hash(propertyPath, message);
  }
}
