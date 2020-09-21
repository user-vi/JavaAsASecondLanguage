package io.github.javaasasecondlanguage.homework01.compressor;

<<<<<<< Updated upstream
public class StringCompressor {
    /**
     * Given an array of characters, compress it using the following algorithm:
     *
=======
//cntr+alt+I - formated script

public class StringCompressor {
    /**
     * Given an array of characters, compress it using the following algorithm:
     * <p>
>>>>>>> Stashed changes
     * Begin with an empty string s.
     * For each group of consecutive repeating characters in chars:
     * If the group's length is 1, append the character to s.
     * Otherwise, append the character followed by the group's length.
     * Return a compressed string.
     * </p>
     * Follow up:
     * Could you solve it using only O(1) extra space?
     * </p>
     * Examples:
     * a -> a
     * aa -> a2
     * aaa -> a3
     * aaabb -> a3b2
     * "" -> ""
     * null -> Illegal argument
     * 234 sdf -> Illegal argument
     *
     * @param str nullable array of chars to compress
     *            str may contain illegal characters
     * @return a compressed array
     * @throws IllegalArgumentException if str is null
     * @throws IllegalArgumentException if any char is not in range 'a'..'z'
     */
    public char[] compress(char[] str) {
<<<<<<< Updated upstream
        throw new RuntimeException("Not implemented");
    }
}
=======

        //null -> Illegal argument
        if (str == null) {
            throw new IllegalArgumentException();
        }

        //"" -> ""
        if (str.toString().equals("")) {
            throw new IllegalArgumentException();
        }

        //234 sdf -> Illegal argument
        //Character.isWhitespace(str.toString().charAt(i))
        for (int i = 0; i < str.length; i++) {
            char[] temp = str;
            char temp2 = str[i];
            if (Character.isWhitespace(str[i])) {
                throw new IllegalArgumentException();
            }
        }

        return stringCompressor(str);
    }

    char[] stringCompressor(char[] str) {
        String temp = "";
        int i;
        int j = 1;

        if (str.length == 1) {
            return str;
        }

        //aabbccc
        for (i = 1; i < str.length; i++) {

            if (i == 1) {
                temp = temp + str[i - 1];
            }

            if (str[i] != str[i - 1]) {
                temp = temp + str[i];
            }

            if (str[i] == str[i - 1]) {
                j++;
            }
            if (i < str.length - 1) {
                if (str[i] != str[i + 1]) {
                    if (j > 1) {
                        temp = temp + String.valueOf(j);
                        j = 1;
                    }
                }
            }
            if (i == str.length - 1) {
                if (j > 1) {
                    temp = temp + String.valueOf(j);
                    j = 1;
                }
            }
        }
        return temp.toCharArray();
    }


}
>>>>>>> Stashed changes
