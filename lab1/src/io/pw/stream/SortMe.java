package io.pw.stream;

import io.pw.data.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SortMe {

    static List<User> users = List.of(
            new User("John", "john@gmail.com", 22, "asdf".getBytes(StandardCharsets.UTF_8)),
            new User("Jack", "jack@wp.com", 34, "qwerty".getBytes(StandardCharsets.UTF_8)),
            new User("Anna", "anna@fb.com", 31, "123fsdf".getBytes(StandardCharsets.UTF_8)),
            new User("Alice", "alice@interia.com", 28, "asgrg3df".getBytes(StandardCharsets.UTF_8)),
            new User("Chris", "chris@interia.com", 44, "fwefefw".getBytes(StandardCharsets.UTF_8)),
            new User("Jinks", "jinks@ol.com", 53, "f3fq23f".getBytes(StandardCharsets.UTF_8)),
            new User("Heather", "heather@interia.com", 27, "frqq3fdsa".getBytes(StandardCharsets.UTF_8)),
            new User("Bob", "bob@gmail.com", 26, "a3242fwsdf".getBytes(StandardCharsets.UTF_8))
    );

    public static void main(String[] args) {

    }

}
