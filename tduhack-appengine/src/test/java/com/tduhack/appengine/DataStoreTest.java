package com.tduhack.appengine;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.tduhack.Field;
import com.tduhack.HasId;
import com.tduhack.HasName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataStoreTest {

  private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Before
  public void setUp() {
    helper.setUp();
  }

  @After
  public void tearDown() {
    helper.tearDown();
  }

  private interface Person extends HasName, HasId {
    Field<Integer> age = new Field<>("age", Integer.class);
  }

  @Test
  public void testSaveAndUpdate() {
    final Store store = new DataStore();
    final Bean person = store.create("Person", 22L).set(HasName.name, "AAA");
    assertThat(store.find("Person", 22L)).isNull();
    store.save(person);
    final Bean saved = store.find("Person", 22L);
    assertThat(saved.id()).isEqualTo(22L);
    assertThat(saved.get(HasName.name)).isEqualTo("AAA");

    final Bean dog = store.create("Dog", 1L).set(HasName.name, "Lulu");
    store.save(dog);
    Bean savedDog = store.find("Dog", 1L);
    assertThat(savedDog.get(HasName.name)).isEqualTo("Lulu");
    store.save(dog.set(HasName.name, "BBB"));
    savedDog = store.find("Dog", 1L);
    assertThat(savedDog.get(HasName.name)).isEqualTo("BBB");
  }

  @Test
  public void testEqualQuery() {
    final Store store = new DataStore();
    assertThat(store.query("Person").toList()).isEmpty();
    store.save(store.create("Person").set(HasName.name, "A").set(Person.age, 20));
    store.save(store.create("Person").set(HasName.name, "B").set(Person.age, 30));
    store.save(store.create("Person").set(HasName.name, "C").set(Person.age, 40));
    assertThat(store.query("Person").toList()).hasSize(3);

    assertThat(store.query("Person").whereEquals(Person.age, 20).toList()).hasSize(1);
    assertThat(store.query("Person").whereEquals(Person.age, 30).first().get(Person.name)).isEqualTo("B");
    assertThat(store.query("Person").whereEquals(Person.age, 50).toList()).hasSize(0);

    final List<String> names = new ArrayList<>();
    for (final Bean person : store.query("Person")) {
      names.add(person.get(Person.name));
    }
    assertThat(names).hasSize(3).containsExactly("A", "B", "C");
  }
}