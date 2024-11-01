package com.flourimus.cas.common.utils;

import com.flourimus.cas.common.enums.TokenTypes;
import com.flourimus.cas.dto.CustomerDto;
import com.flourimus.cas.dto.TokenResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JweTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;

    /**
     * Adds organization related claims for a customer to a JWT claims map.
     *
     * @param claims   the JWT claims map
     * @param customer the customer to add claims for
     */
    private void addOrganizationClaims(final Map<String, Object> claims, final CustomerDto customer) {
        claims.put("organizationId", customer.getOrganizationId());
        claims.put("locationId", customer.getLocationId());
        claims.put("brandId", customer.getBrandId());
        claims.put("channelId", customer.getChannelId());
    }

    /**
     * Creates a JWT claims map for a customer containing common claims that are
     * used by all tokens.
     *
     * @param customer the customer to create claims for
     * @return a JWT claims map with the common claims
     */
    private Map<String, Object> getCommonClaims(final CustomerDto customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", customer.getId());
        claims.put("firstName", customer.getFirstName());
        claims.put("lastName", customer.getLastName());
        claims.put("roleId", customer.getRoleId());
        addOrganizationClaims(claims, customer);
        return claims;
    }

    /**
     * Adds claims specific to access tokens for a given customer.
     *
     * This method adds claims that are specific to access tokens, such as
     * the token type and guest status, on top of common claims retrieved
     * from the customer data.
     *
     * @param customer the customer for whom the access token claims are being added
     * @param isGuest  a boolean indicating whether the customer is a guest
     * @return a map of claims including both common and access-token-specific
     *         claims
     */
    private Map<String, Object> addAccessTokenClaims(final CustomerDto customer, final boolean isGuest) {
        Map<String, Object> claims = getCommonClaims(customer);
        claims.put("tokenType", TokenTypes.ACCESS_TOKEN);
        claims.put("isGuest", isGuest);
        return claims;
    }

    /**
     * Adds claims specific to refresh tokens for a given customer.
     *
     * This method adds claims that are specific to refresh tokens, such as
     * the token type and guest status, on top of common claims retrieved
     * from the customer data.
     *
     * @param customer the customer for whom the refresh token claims are being
     *                 added
     * @param isGuest  a boolean indicating whether the customer is a guest
     * @return a map of claims including both common and refresh-token-specific
     *         claims
     */
    private Map<String, Object> addRefreshTokenClaims(final CustomerDto customer, final boolean isGuest) {
        Map<String, Object> claims = new HashMap<>();
        addOrganizationClaims(claims, customer);
        claims.put("tokenType", TokenTypes.REFRESH_TOKEN);
        claims.put("isGuest", isGuest);
        return claims;
    }

    /**
     * Builds an encrypted JSON Web Token (JWE) with the specified claims, subject,
     * and expiration time using Nimbus JOSE+JWT.
     *
     * This method constructs a JWE by creating a JWT claims set with the provided
     * claims map, subject, and expiration time. It encrypts the JWT using a direct
     * encrypter with the AES GCM encryption method.
     *
     * @param claims         a map of claims to be included in the JWT claims set
     * @param subject        the subject of the JWT
     * @param expirationTime the expiration time in milliseconds from the current
     *                       time
     * @return a serialized JWE string
     * @throws JOSEException if there is an error during the encryption process
     */
    private String buildEncryptedToken(final Map<String, Object> claims, final String subject,
            final long expirationTime) throws JOSEException {
        // Create JWT claims
        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + expirationTime));

        // Add claims to the claims set
        claims.forEach(claimsSetBuilder::claim);

        JWTClaimsSet claimsSet = claimsSetBuilder.build();

        // Create the JWE object
        EncryptedJWT jwt = new EncryptedJWT(
                new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).build(),
                claimsSet);

        // Encrypt the JWT
        jwt.encrypt(new DirectEncrypter(jwtSecret.getBytes(StandardCharsets.UTF_8)));

        return jwt.serialize();
    }

    /**
     * Generates an encrypted access token for the given customer.
     *
     * This method constructs a JWT claims set with the customer's data, including
     * the token type and guest status, and encrypts it using a direct encrypter
     * with the AES GCM encryption method. The token is then serialized as a string
     * that can be used to authenticate the customer.
     *
     * @param customer the customer to generate the access token for
     * @param isGuest  a boolean indicating whether the customer is a guest
     * @return a serialized encrypted access token string
     * @throws JOSEException if there is an error during the encryption process
     */
    public String generateAccessToken(final CustomerDto customer, final boolean isGuest) throws JOSEException {
        Map<String, Object> claims = addAccessTokenClaims(customer, isGuest);
        return buildEncryptedToken(claims, customer.getEmail(), accessTokenExpirationMs);
    }

    /**
     * Generates an encrypted refresh token for the given customer.
     *
     * This method constructs a JWT claims set with the customer's data, including
     * the token type and guest status, and encrypts it using a direct encrypter
     * with the AES GCM encryption method. The token is then serialized as a string
     * that can be used to request a new access token when the existing one has
     * expired.
     *
     * @param customer the customer to generate the refresh token for
     * @param isGuest  a boolean indicating whether the customer is a guest
     * @return a serialized encrypted refresh token string
     * @throws JOSEException if there is an error during the encryption process
     */
    public String generateRefreshToken(final CustomerDto customer, final boolean isGuest) throws JOSEException {
        Map<String, Object> claims = addRefreshTokenClaims(customer, isGuest);
        return buildEncryptedToken(claims, customer.getEmail(), refreshTokenExpirationMs);
    }

    /**
     * Populates a TokenResponse with access and refresh tokens for the given
     * customer.
     *
     * This method generates both access and refresh tokens for the specified
     * customer,
     * populating a TokenResponse object with the tokens and the customer's email.
     *
     * @param customer the customer for whom the tokens are generated
     * @param isGuest  a boolean indicating whether the customer is a guest
     * @return a TokenResponse containing the access token, refresh token, and email
     * @throws JOSEException if there is an error during the token generation
     *                       process
     */
    public TokenResponse populateTokenResponse(final CustomerDto customer, final boolean isGuest) throws JOSEException {
        String accessToken = generateAccessToken(customer, isGuest);
        String refreshToken = generateRefreshToken(customer, isGuest);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setEmail(customer.getEmail());
        return tokenResponse;
    }
}
