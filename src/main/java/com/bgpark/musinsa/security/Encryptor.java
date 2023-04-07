package com.bgpark.musinsa.security;

/**
 * @author 박병길
 */
public interface Encryptor {

    String encrypt(String origin);

    String decrypt(String encrypted);
}
