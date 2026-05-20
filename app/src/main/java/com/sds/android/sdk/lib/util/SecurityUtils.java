package com.sds.android.sdk.lib.util;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for security operations like MD5, TEA, RC4, and AES.
 * De-obfuscated version.
 */
public class SecurityUtils {

    /**
     * MD5 hashing utility.
     */
    public static class MD5Hex {
        /**
         * Returns a truncated MD5 hex string (8 characters from index 4 to 11).
         */
        public static String stringToMD5Hex(String str) {
            return bytesToString(stringToMD5Bytes(str));
        }

        /**
         * Returns the full MD5 hex string.
         */
        public static String stringToHex(String str) {
            return bytesToHex(stringToMD5Bytes(str));
        }

        private static byte[] stringToMD5Bytes(String str) {
            if (str == null) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes());
                return messageDigest.digest();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private static String bytesToString(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            // Truncated hash: uses 8 bytes from index 4 to 11
            for (int i = 4; i <= 11; i++) {
                sb.append(Integer.toHexString(bArr[i] & 255));
            }
            return sb.toString();
        }

        private static String bytesToHex(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder("");
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                int i2 = bArr[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        }
    }

    /**
     * TEA (Tiny Encryption Algorithm) implementation.
     */
    public static final class TEA {

        private static final int[] DEFAULT_KEY = {-1857714090, -47692346, -1232996205, 796674487};

        /**
         * Encrypts a string using TEA and formats it.
         */
        public static String encrypt(String str) {
            if (StringUtils.isEmpty(str)) {
                return "";
            }
            try {
                int[] encrypted = encrypt(stringToIntArray(str), DEFAULT_KEY, 16);
                String hex = String.format("%08x%08x", Integer.valueOf(encrypted[0]), Integer.valueOf(encrypted[1])).toLowerCase();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < hex.length(); i++) {
                    int intValue = Integer.valueOf(hex.substring(i, i + 1), 16).intValue();
                    if (intValue >= 10) {
                        sb.append(intValue - 10);
                    } else {
                        sb.append(intValue);
                    }
                }
                return sb.toString();
            } catch (IllegalArgumentException e) {
                return "";
            }
        }

        private static int[] stringToIntArray(String str) {
            StringBuilder sb = new StringBuilder(str.trim());
            if (sb.toString().equals("0")) {
                throw new IllegalArgumentException();
            }
            boolean isHex = false;
            for (int i = 0; i < sb.length(); i++) {
                char charAt = sb.charAt(i);
                if (charAt < '0' || charAt > ';') {
                    isHex = true;
                    break;
                }
            }
            if (sb.length() < 16) {
                while (sb.length() < 16) {
                    sb.append("0");
                }
            }
            long v0, v1;
            if (isHex) {
                v0 = Long.parseLong(sb.substring(0, 8), 16);
                v1 = Long.parseLong(sb.substring(8, 16), 16);
            } else {
                v0 = Long.parseLong(sb.substring(0, 8), 16);
                v1 = Long.parseLong(sb.substring(8, 16), 16);
            }
            return new int[]{(int) v0, (int) v1};
        }

        public static int[] encrypt(int[] v, int[] k, int rounds) {
            int v0 = v[0];
            int v1 = v[1];
            int k0 = k[0];
            int k1 = k[1];
            int k2 = k[2];
            int k3 = k[3];
            int sum = 0;
            for (int i = 0; i < rounds; i++) {
                sum -= 1640531527; // Delta
                v0 += (((v1 << 4) + k0) ^ (v1 + sum)) ^ ((v1 >>> 5) + k1);
                v1 += (((v0 << 4) + k2) ^ (v0 + sum)) ^ ((v0 >>> 5) + k3);
            }
            return new int[]{v0, v1};
        }
    }

    /**
     * RC4 (Rivest Cipher 4) implementation.
     */
    public static class RC4 {

        private static final byte[] SBOX = {99, 7, 66, 74, -81, -36, 45, -18, 106, 25, 126, 6, 71, -67, 47, 108, 116, 117, 113, 121, 78, 109, -119, 62, 29, 23, 24, 76, 125, -62, -89, 0, -72, 82, 102, 50, -51, 100, 81, 65, -105, -26, -65, 88, -74, 89, -69, 18, 87, 44, -23, -20, -63, 36, -88, 79, -32, 26, -115, -10, -127, -49, 124, -122, -6, 122, 91, 12, 70, -29, -31, 14, -2, -12, -123, 84, 27, 97, -87, -84, 98, 41, 120, 59, 39, -104, 52, 101, 33, 35, -17, 105, -86, -57, 75, -76, Byte.MIN_VALUE, 64, 3, -33, 119, 95, 57, -73, 60, -68, 1, 69, 46, 86, 42, -98, -99, -9, 118, -95, -16, -41, 54, -124, 17, -107, 10, -121, -97, -106, 55, -14, -96, -116, 110, -4, -94, 56, -21, 30, -55, -47, 31, 58, 37, -46, 83, -30, -93, 61, 123, -120, -85, -111, -66, -102, -113, -50, -110, -8, -91, 68, 107, 112, -27, -64, 90, -45, 4, -118, 115, -92, 72, -15, 67, -83, -56, -52, 51, -39, -19, -13, 114, 2, -53, -1, 16, -48, -7, -58, -117, -77, 93, 53, -101, -38, -126, -44, 11, -59, 103, 5, -80, 63, -82, -114, -5, -71, -28, -78, -42, -103, 19, 48, -43, 49, 34, -25, 22, 92, 80, -22, 32, Byte.MAX_VALUE, 104, -125, 20, 38, 15, 94, -11, -100, 8, -3, -79, 43, -24, 73, 9, -54, -108, -35, 96, -109, 111, -34, -40, -60, 77, 40, 28, 13, -70, 21, -75, -90, -37, -112, -61, 85};

        /**
         * Decrypts a hex-encoded RC4 encrypted string.
         */
        public static String decrypt(String str) {
            if (str == null) {
                return null;
            }
            return new String(crypt(hexToBytes(str)));
        }

        private static byte[] hexToBytes(String str) {
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            byte[] bytes = str.getBytes();
            for (int i = 0; i < length / 2; i++) {
                bArr[i] = hexPairToByte(bytes[i * 2], bytes[(i * 2) + 1]);
            }
            return bArr;
        }

        private static byte hexPairToByte(byte b1, byte b2) {
            return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{b1})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{b2})).byteValue()));
        }

        private static byte[] crypt(byte[] data) {
            byte[] sbox = (byte[]) SBOX.clone();
            byte[] result = new byte[data.length];
            int i = 0;
            int j = 0;
            for (int k = 0; k < data.length; k++) {
                i = (i + 1) & 255;
                j = (j + (sbox[i] & 255)) & 255;
                byte temp = sbox[i];
                sbox[i] = sbox[j];
                sbox[j] = temp;
                result[k] = (byte) (sbox[((sbox[i] & 255) + (sbox[j] & 255)) & 255] ^ data[k]);
            }
            return result;
        }
    }

    /**
     * AES encryption/decryption utility.
     */
    public static class AES {

        private static final byte[] AES_KEY = {84, 65, -33, 92, 57, 90, -104, 0, 112, 74, 69, -53, 106, -122, -107, -95};

        /**
         * Decrypts a hex-encoded AES encrypted string.
         */
        public static String decrypt(String str) throws Exception {
            return new String(decrypt(AES_KEY, hexToBytes(str)));
        }

        private static byte[] decrypt(byte[] key, byte[] data) throws Exception {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(data);
        }

        private static byte[] hexToBytes(String str) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
            }
            return bArr;
        }
    }
}
