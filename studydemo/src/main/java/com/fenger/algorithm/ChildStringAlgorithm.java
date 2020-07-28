package com.fenger.algorithm;

public class ChildStringAlgorithm {
    public static void main(String[] args){
        String parentStr = "goodgooglelgoogle";
        String childStr = "google";
        System.out.println(findChildIndex(parentStr,childStr));
    }

    /**
     * 找子串的位置 常规算法
     * @param parentStr
     * @param childStr
     * @return
     */
    private static int findChildIndex(String parentStr, String childStr) {
        int result = -1;
        boolean flag = false;
        for (int i = 0; i < parentStr.length(); i++) {
            result = i;
            for (int j = 0; j < childStr.length(); j++) {
                char childChar = childStr.charAt(j);
                if(parentStr.length() == (j+i)) {
                    flag = false;
                    break;
                }
                char parentChar = parentStr.charAt(j + i);
                if (parentChar == childChar) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if(flag) {
                break;
            }
        }
        if(!flag) {
            return -1;
        }
        return result;
    }
}
