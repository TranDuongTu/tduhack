package com.tduhack.appengine;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.tduhack.HasName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}