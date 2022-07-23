package main.java.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<K,V> {
    K key;
    V value;
}
