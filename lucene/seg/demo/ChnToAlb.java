package seg.demo;

import java.util.HashMap;  
import java.util.LinkedHashMap;  
import java.util.Map;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class ChnToAlb {  
      
    public static void main(String[] args) {  
        String text = "ǧ������,����ʮ�ڿ�Ǯ, ���ж�ʮ����Ǯ, ���ж�ԪǮ, ��������, ���Ƕ��ٶ�, ���������ʮ��, Ҳ����ʮ��, ��ô�Ű�����, ����һǧ����ʮ��������.";  
        System.out.println(bulidTextZHToALB(text));  
    }  
      
    public static String bulidTextZHToALB(String text) {  
        Pattern p = Pattern.compile(numRegex);  
        Matcher m = p.matcher(text);  
          
        while(m.find()) {  
            String numZH = m.group();  
            if(numZH.length() !=1 || numMap.containsKey(numZH) || zhTen.equals(numZH)) {  
                String numALB = NumZHToALB(numZH);  
                text = text.replaceFirst(numZH, numALB);  
            }  
        }  
          
        return text;  
    }  
      
    private static String NumZHToALB(String numZH) {  
        int numALB = 0;  
        int formIndex = 0;  
        for(String unitNum : unitNumMap.keySet()) {  
            int index = numZH.indexOf(unitNum);  
            if(index != -1 ) {  
                numALB += NumZHToALB(numZH.substring(formIndex, index),  unitNumMap.get(unitNum));  
                formIndex = index + 1;  
            }  
        }  
          
        numALB += NumZHToALB(numZH.substring(formIndex),  1);  
        return String.valueOf(numALB);  
    }  
      
    private static int NumZHToALB(String numZH, int unitNum) {  
        int length = numZH.length();  
        int numALB = 0;  
        if(length != 0) {  
            int fromIndex = 0;  
            for(String unit : unitMap.keySet()) {  
                int index = numZH.indexOf(unit, fromIndex);  
                if(index != -1) {  
                    fromIndex = index + unit.length();  
                    String prevChar = zhOne;  
                    if(index != 0 && numMap.containsKey(prevChar)) {  
                        prevChar = String.valueOf(numZH.charAt(index - 1));  
                    }   
                    numALB += numMap.get(prevChar) * unitMap.get(unit);  
                }  
            }  
              
            String lastChar = String.valueOf(numZH.charAt(length - 1));  
            if(numMap.containsKey(lastChar)) {  
                String pChar = zhTen;  
                if(length != 1) {  
                    pChar = String.valueOf(numZH.charAt(length - 2));  
                    if(zhZero.equals(pChar)) {  
                        pChar = zhTen;  
                    }  
                }  
                numALB += numMap.get(lastChar) * unitMap.get(pChar)/10;  
            }  
        }  
          
        return numALB * unitNum;  
    }  
      
    private static String encodeUnicode(String gbString) {     
        char[] utfBytes = gbString.toCharArray();     
        String unicodeBytes = "";     
        for (int i : utfBytes) {     
            String hexB = Integer.toHexString(i);     
            if (hexB.length() <= 2) {     
                hexB = "00" + hexB;     
            }     
            unicodeBytes = unicodeBytes + "\\u" + hexB;     
        }  
        return unicodeBytes;  
    }  
      
    private static final String zhZero = "��";  
    private static final String zhOne = "һ";  
    private static final String zhTen = "ʮ";  
      
    private static final Map<String, Integer> numMap = new HashMap<String, Integer>();  
    static {  
        numMap.put("��", 0);  
        numMap.put("һ", 1);  
        numMap.put("��", 2);  
        numMap.put("��", 3);  
        numMap.put("��", 4);  
        numMap.put("��", 5);  
        numMap.put("��", 6);  
        numMap.put("��", 7);  
        numMap.put("��", 8);  
        numMap.put("��", 9);  
    }  
      
    private static final Map<String, Integer> unitNumMap = new LinkedHashMap<String, Integer>();  
    static {  
        unitNumMap.put("��", 100000000);  
        unitNumMap.put("��", 10000);  
    }  
      
    private static final Map<String, Integer> unitMap = new LinkedHashMap<String, Integer>();  
    static {  
        unitMap.put("ǧ", 1000);  
        unitMap.put("��", 100);  
        unitMap.put("ʮ", 10);  
    }  
      
    private static String numRegex;  
    static {  
        numRegex = "[";  
        for(String s : numMap.keySet()) {  
            numRegex += encodeUnicode(s);  
        }  
        for(String s : unitMap.keySet()) {  
            numRegex += encodeUnicode(s);  
        }  
        for(String s : unitNumMap.keySet()) {  
            numRegex += encodeUnicode(s);  
        }  
        numRegex += "]+";  
    }  
}  