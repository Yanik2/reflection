package org.example.section3.jsonwriter;

import org.example.section3.jsonwriter.data.Address;
import org.example.section3.jsonwriter.data.Person;
import org.example.section3.jsonwriter.mapper.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
         final var address = new Address("rydlowka", (short)42);
         final var person = new Person("john", true, 32, 102.1234f, address);

        final var json = ObjectMapper.objectToJson(person);

        System.out.println(json);
    }
}
