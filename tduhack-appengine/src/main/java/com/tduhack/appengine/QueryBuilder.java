package com.tduhack.appengine;

import com.tduhack.Field;
import com.tduhack.HasFields;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface QueryBuilder<T extends HasFields> extends Iterable<T> {
  <V> QueryBuilder whereEquals(final Field<V> field, final V value);

  Iterable<T> collect();

  default List<T> toList() {
    return StreamSupport.stream(collect().spliterator(), false).collect(Collectors.toList());
  }

  default T first() {
    final Iterator<T> iterator = collect().iterator();
    return iterator.hasNext() ? iterator.next() : null;
  }
}
