package io.github.bhuwanupadhyay.ddd;

public final class DomainEntityNotFound extends RuntimeException {

  private final ValueObject id;

  public DomainEntityNotFound(String prior, ValueObject id) {
    super(String.format("[%s] Entity not found by id: %s", prior, id));
    this.id = id;
  }

  public ValueObject getId() {
    return id;
  }
}
