package com.bgpark.musinsa.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AES256EncrypterTest {

    private AES256Properties properties = new AES256Properties("12345678901234567890123456789012", "1234567890123456");
    private AES256Encrypter encrypter = new AES256Encrypter(properties);

    @Test
    void 암호화() {
        assertThat(encrypter.encrypt("김무신사")).isNotEqualTo("김무신사");
    }

    @Test
    void 복호화() {
        String encrypted = encrypter.encrypt("김무신사");
        String decrypt = encrypter.decrypt(encrypted);
        assertThat(decrypt).isEqualTo("김무신사");
    }
}
