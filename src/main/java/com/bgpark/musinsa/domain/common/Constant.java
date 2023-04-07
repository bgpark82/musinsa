package com.bgpark.musinsa.domain.common;

/**
 * @author 박병길
 */
public class Constant {

    public static final int INCREAMENT_UNIT = 1;
    public static final int DEFAULT_REQUEST_COUNT = 0;

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    public static final String UNCHECKED = "UNCHECKED";

    /**
     * Cipher 객체를 생성할 때 사용되는 암호화 알고리즘, 패딩 방식, 암호화 모드를 지정하는 문자열입니다.
     * - AES : 암호화 알고리즘
     * - EBS : 암호화 모드, 데이터를 블록화 -> 블록을 암호화 (전자 통신에서 사용되는 블록 암호화 모드, 간단하지만 보안성이 떨어지는 문제가 있습니다.)
     * - PKCS5Padding : 패딩 방식. 블록 크기를 맞추기 위해 블록 끝에 패딩 바이트 추가 (이 방식은 블록 암호화에서 일반적으로 사용되는 패딩 방식 중 하나입니다
     */
    public static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    public static final String ALGORITHM = "AES";
}
