package io.github.bhuwanupadhyay.casestudy.inventory.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface ReadOnlyRestRepository<T, ID> extends CrudRepository<T, ID> {

  @RestResource(exported = false)
  @Override <S extends T> S save(S s);

  @RestResource(exported = false)
  @Override <S extends T> Iterable<S> saveAll(Iterable<S> iterable);

  @RestResource(exported = false)
  @Override void deleteById(ID id);

  @RestResource(exported = false)
  @Override void delete(T t);

  @RestResource(exported = false)
  @Override void deleteAll(Iterable<? extends T> iterable);

  @RestResource(exported = false)
  @Override void deleteAll();
}
