package com.flourimus.users.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Hashes the given password using the BCryptPasswordEncoder algorithm.
     * The result can be used as a password in a database.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    /**
     * Compares the given raw password with the hashed password using the BCryptPasswordEncoder algorithm.
     * Returns true if the passwords match, false otherwise.
     *
     * @param rawPassword The raw password to compare.
     * @param hashedPassword The hashed password to compare with.
     * @return Whether the passwords match.
     */
    public static boolean isPasswordMatch(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
