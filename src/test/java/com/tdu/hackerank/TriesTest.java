package com.tdu.hackerank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TriesTest {

  private static final Tries.Alphabet ALPHABET = new Tries.Alphabet() {

    private final Map<Character, Integer> map = new HashMap<>();

    {
      final List<Character> characterList = new ArrayList<>();
      IntStream.range(97, 123).forEach(value -> characterList.add((char) value));
      for (int i = 0; i < characterList.size(); i++) {
        map.put(characterList.get(i), i);
      }
    }

    @Override
    public int R() {
      return map.size();
    }

    @Override
    public int index(char character) {
      return map.get(character);
    }
  };

  @Test
  public void testInsertAndQuery() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    assertThat(tries.isEmpty()).isTrue();
    tries.put("abc", 1);
    tries.put("acb", 2);
    tries.put("abcd", 3);
    tries.put("abd", 4);
    assertThat(tries.isEmpty()).isFalse();
    assertThat(tries.contains("abcd")).isTrue();
    assertThat(tries.contains("abce")).isFalse();
    assertThat(tries.contains("abc")).isTrue();
    tries.delete("abc");
    assertThat(tries.contains("abc")).isFalse();
  }

  @Test
  public void testPrefixes() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    tries.put("a", 1);
    tries.put("ab", 2);
    tries.put("ac", 3);
    tries.put("abc", 4);
    tries.put("abd", 5);
    tries.put("abde", 6);
    tries.put("ace", 1);
    assertThat(tries.longestPrefixOf("abceeee")).isEqualTo("abc");
    assertThat(tries.longestPrefixOf("abdef")).isEqualTo("abde");
    assertThat(tries.keysWithPrefix("abd")).hasSize(2)
        .contains("abd", "abde");
    assertThat(tries.get("abd")).isEqualTo(5);
    assertThat(tries.get("abde")).isEqualTo(6);
    assertThat(tries.keys()).hasSize(7)
        .contains("a", "ab", "ac", "abc", "abd", "abde", "ace");
  }
}