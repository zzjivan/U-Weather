package Util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Pattern;

/**
 * Created by sedwt on 2016/11/16.
 */
public class PinyinTool {
    public static String getpinyin(String s){
        StringBuffer pybf = new StringBuffer();
        char[] c = s.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for(int i = 0; i < c.length; i ++) {
            try {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(c[i], defaultFormat);
                for(int j = 0; j < temp.length; j ++)
                    pybf.append(temp[j]);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }
        return pybf.toString().trim();
    }

    public static String getAlpha(String s, boolean isPinyin) {
        String str = "";
        if(!isPinyin)
            str = getpinyin(s);
        else
            str = s;

        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        }
        return "#";
    }
}
