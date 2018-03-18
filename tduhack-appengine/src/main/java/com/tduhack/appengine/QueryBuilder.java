package com.tduhack.appengine;

import com.tduhack.Field;
import com.tduhack.HasFields;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface QueryBuilder<T extends HasFields> extends Iterable<T> {
  <V> QueryBuilder<T> whereEquals(final Field<V> field, final V value);

  Iterable<T> collect();

  default List<T> toList() {
    return stream().collect(Collectors.toList());
  }

  default T first() {
    final Iterator<T> iterator = collect().iterator();
    return iterator.hasNext() ? iterator.next() : null;
  }

  default Stream<T> stream() {
    return StreamSupport.stream(collect().spliterator(), false);
  }
}
