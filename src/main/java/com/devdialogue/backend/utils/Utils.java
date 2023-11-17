package com.devdialogue.backend.utils;

import java.util.*;

public class Utils {
    public static Map<String, String> getAuthoritiesForUsers() {
        HashMap<String, String> authoritiesMap = new HashMap<>();
        List<String> userAuthorities = Arrays.asList("USER_SELF_INFO", "CREATE_QUESTIONS", "DELETE_SELF_QUESTIONS");
        List<String> adminAuthorities = Arrays.asList(
                "USER_INFO_BY_ADMIN", "ADMIN", "CREATE_ADMIN", "DELETE_QUESTIONS", "CREATE_USER"
        );

        String userAuthortiesAsString = String.join("::", userAuthorities);
        String adminAuthortiesAsString = String.join("::", adminAuthorities);

        authoritiesMap.put("USER", userAuthortiesAsString);
        authoritiesMap.put("ADMIN", adminAuthortiesAsString);

        return authoritiesMap;
    }
}
