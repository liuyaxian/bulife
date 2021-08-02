package com.ruiya.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtils {


        /**
         * 获取汉字首字母的方法。如： 张三 --> ZS
         * <p>
         * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
         *
         * @param hanzi 汉子字符串
         * @return 大写汉子首字母; 如果都转换失败,那么返回null
         */

        public static String getHanziInitials(String hanzi) {

            String result = null;

            if (null != hanzi && !"".equals(hanzi)) {

                char[] charArray = hanzi.toCharArray();

                StringBuffer sb = new StringBuffer();

                for (char ch : charArray) {

                    // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）

                    String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);

                    if (null != stringArray) {

                        sb.append(stringArray[0].charAt(0));

                    }

                }

                if (sb.length() > 0) {

                    result = sb.toString().toUpperCase();

                }

            }

            return result;

        }


        /**
         * 获取汉字拼音的方法。如： 张三 --> zhangsan
         * <p>
         * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
         *
         * @param hanzi 汉子字符串
         * @return 汉字拼音; 如果都转换失败,那么返回null
         */

        public static String getHanziPinYin(String hanzi) {

            String result = null;
            if (null == hanzi || "".equals(hanzi)) {
                return "";
            }
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    // 把第几声这个数字给去掉
                    String word = captureName(stringArray[0].toString());
                    sb.append("汉字："+ ch);
                    sb.append("   拼音："+word.replaceAll("\\d", "").replaceAll(":",""));
                    sb.append("\n");
                }
            }
            if (sb.length() > 0) {
                result = sb.toString();
            }
            return result;
        }

        /**
         * 将字符串的首字母转大写
         * @param str 需要转换的字符串
         * @return
         */
        private static String captureName(String str) {
            // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
            char[] cs=str.toCharArray();
            cs[0]-=32;
            return String.valueOf(cs);
        }

        public static void main(String[] args) {
            //System.out.println(PinYinUtils.getHanziInitials("侯服绿语许少过女个处们还羊尾鸟猫吗有红没尺苹见色着参远大令相并担底假行盛薄吓她凉度跑佛父万齐"));
            System.out.println(PinYinUtils.getHanziPinYin("的 一 是 了 我 不 人 在 他 有 这 个 上 们 来 到 时 大 地 为 子 中 你 说 生 国 年 着 就 那 和 要 她 出 也 得 里 后 自 以 会 家 可 下 而 过 天 去 能 对 小 多 然 于 心 学 么 之 都 好 看 起 发 当 没 成 只 如 事 把 还 用 第 样 道 想 作 种 开 美 总 从 无 情 己 面 最 女 但 现 前 些 所 同 日 手 又 行 意 动 方 期 它 头 经 长 儿 回 位 分 爱 老 因 很 给 名 法 间 斯 知 世 什 两 次 使 身 者 被 高 已 亲 其 进 此 话 常 与 活 正 感"));

        }
    }