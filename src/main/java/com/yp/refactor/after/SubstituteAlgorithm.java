package com.yp.refactor.after;

import java.util.Arrays;
import java.util.List;

public class SubstituteAlgorithm {

    public String foundPerson(String[] people) {
        List<String> candidates = Arrays.asList("Don", "John", "Kent");
        for(int i = 0; i < people.length; i++) {
            if(candidates.contains(people[i])) {
                return people[i];
            }
        }
        return "";
    }
}
