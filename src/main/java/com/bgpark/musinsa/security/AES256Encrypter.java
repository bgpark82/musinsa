package com.bgpark.musinsa.security;

import com.bgpark.musinsa.exception.BusinessException;
import com.bgpark.musinsa.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.bgpark.musinsa.domain.common.Constant.ALGORITHM;
import static com.bgpark.musinsa.domain.common.Constant.TRANSFORMATION;

@Component
@RequiredArgsConstructor
public class AES256Encrypter implements Encryptor {

    private final AES256Properties properties;

    @Override
    public  String encrypt(String plainText) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(properties.getKey().getBytes(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(properties.getIv().getBytes());
            Cipher cipher =  Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return new String(Base64.encodeBase64(encrypted));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BusinessException(ErrorCode.INVALID_ENCRYPTION, e);
        }
    }


    @Override
    public String decrypt (String encrypted) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(properties.getKey().getBytes(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(properties.getIv().getBytes());
            Cipher cipher =  Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
            return new String(decrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new BusinessException(ErrorCode.INVALID_ENCRYPTION, e);
        }
    }
}



