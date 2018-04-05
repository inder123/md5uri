/*
 * Copyright (C) 2018 Inderjeet Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singhinderjeet.md5uri;

import java.math.BigInteger;

/**
 * A utility class to convert MD5 values to shorter Base 66 values. The base 66 values are
 * limited to characters abcdefghiklmopqrstuvwABCDEFGHIKLMOPQRSTUVWXYZ0123456789-_.~
 *
 * @author Inderjeet Singh
 */
public class Md5Uri {

    private static final char[] BASE_66_DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.~".toCharArray();
    private static final BigInteger SIXTY_SIX = BigInteger.valueOf(66L);

    /**
     * Returns a base 66 string for an md5 string
     * @param md5 A valid md5 string of size 32
     * @return a string that represent the value of the string as a base 66 number
     */
    public static String fromMd5(String md5) {
        BigInteger value = new BigInteger(md5, 16);
        return fromMd5(value);
    }

    /**
     * Converts a base66 string into an MD5 string
     *
     * @param base66 The string representing a number in base 62
     * @return the string (padded with enough leading zeros, if needed)
     * @throws if base66 is not in base 66 format
     */
    public static String toMd5(String base66) {
        BigInteger value = toMd5AsInteger(base66);
        String str = value.toString(16);
        int len = str.length();
        if (len < 32) {
            StringBuilder sb = new StringBuilder(32);
            int leadingZeroesCount = 32-len;
            for (int i = 0; i < leadingZeroesCount; ++i) {
                sb.append('0');
            }
            sb.append(str);
            str = sb.toString();
        }
        return str;
    }

    private static String fromMd5(BigInteger value) {
        StringBuilder base66 = new StringBuilder();
        if ((value.signum() < 0)) base66.append('-');
        BigInteger bal = value.abs();
        while (bal.compareTo(SIXTY_SIX) > 0) {
            int digit = bal.mod(SIXTY_SIX).intValue();
            base66.append(BASE_66_DIGITS[digit]);
            bal = bal.divide(SIXTY_SIX);
        }
        int digit = bal.mod(SIXTY_SIX).intValue();
        base66.append(BASE_66_DIGITS[digit]);
        return base66.toString();
    }

    private static BigInteger toMd5AsInteger(String base66) {
        BigInteger value = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int i = 0; i < base66.length(); ++i) {
            char c = base66.charAt(i);
            BigInteger digit = digitValue(c);
            value = value.add(multiplier.multiply(digit));
            multiplier = multiplier.multiply(SIXTY_SIX);
        }
        return value;
    }

    private static BigInteger digitValue(char c) {
        for (int i = 0; i < BASE_66_DIGITS.length; ++i) {
            if (BASE_66_DIGITS[i] == c) {
                return BigInteger.valueOf(i);
            }
        }
        throw new NumberFormatException("Invalid base 66 character: " + c);
    }
}
