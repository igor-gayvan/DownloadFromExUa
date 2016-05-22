/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadfromexua;

/**
 *
 * @author Igor Gayvan
 */
public class Utils {

    public static final long COUNT_BYTES_IN_MEGABYTE = 1024 * 1024;

//    public static String padl(String in, int size, char padChar) {
//        if (in.length() <= size) {
//            char[] temp = new char[size];
//            /* Llenado Array con el padChar*/
//            for (int i = 0; i < size; i++) {
//                temp[i] = padChar;
//            }
//            int posIniTemp = size - in.length();
//            for (int i = 0; i < in.length(); i++) {
//                temp[posIniTemp] = in.charAt(i);
//                posIniTemp++;
//            }
//            return new String(temp);
//        }
//        return "";
//    }
    public static String padl(String str, Integer length, char car) {
        return str
                + String.format("%" + (length - str.length()) + "s", "")
                .replace(" ", String.valueOf(car));
    }

    public static String padr(String str, Integer length, char car) {
        return String.format("%" + (length - str.length()) + "s", "")
                .replace(" ", String.valueOf(car))
                + str;
    }

    public static String padc(String str, Integer length, char car) {
        int padSize = length - str.length();
        int padStart = str.length() + padSize / 2;

        str = String.format("%" + padStart + "s", str);
        str = String.format("%-" + length + "s", str);

        return str.replace(" ", String.valueOf(car));
    }

}
