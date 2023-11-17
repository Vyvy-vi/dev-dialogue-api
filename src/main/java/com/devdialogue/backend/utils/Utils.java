package com.devdialogue.backend.utils;

import java.util.*;

public class Utils {
    public static Map<String, String> getAuthoritiesForUsers() {
        HashMap<String, String> authoritiesMap = new HashMap<>();
        List<String> userAuthorities = Arrays.asList(
                "VIEW_SELF_USER",
                "UPDATE_SELF_USER",
                "DELETE_SELF_USER",
                "VIEW_QUESTIONS",
                "CREATE_QUESTIONS",
                "UPDATE_SELF_QUESTIONS",
                "DELETE_SELF_QUESTIONS");
        List<String> adminAuthorities = Arrays.asList(
                // "SUPER_ADMIN", - not passed (SUPER_ADMIN)
                "VIEW_ALL_USER",
                "CREATE_USER",
                "UPDATE_USER",
                "DELETE_USER",
                "VIEW_QUESTIONS",
                "UPDATE_QUESTIONS",
                "DELETE_QUESTIONS",
                "VIEW_ADMIN",
                "CREATE_ADMIN",
                "UPDATE_ADMIN",
                "DELETE_ADMIN"
        );

        String userAuthortiesAsString = String.join("::", userAuthorities);
        String adminAuthortiesAsString = String.join("::", adminAuthorities);

        authoritiesMap.put("USER", userAuthortiesAsString);
        authoritiesMap.put("ADMIN", adminAuthortiesAsString);

        return authoritiesMap;
    }
}
