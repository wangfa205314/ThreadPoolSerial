package com.example.threadpoolexecutordemo;

public class Utils {

    static public int isOdd(int num) {
        return num & 0x1;
    }

    static public byte HexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }

    static public byte[] HexToByteArr(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen) == 1) {//
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {//
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = HexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }


}
