/*
 * Version 1.0
 */
package com.soft9000.SQLMate;

import java.util.Formatter;
import javax.xml.bind.DatatypeConverter;

/**
 * A simple "brace based" code-formatter. Source code comments are supported.
 * 
 * @author profnagy
 */
public class SourceCodeFormatter {
    
    public static String FormatCode(String sText) {
        SourceCodeFormatter ref = new SourceCodeFormatter();
        return ref.format(sText, "   ");
    }

    static class StringTokenizer {

        private final String sBlockOn;
        private final String codeStringReplacement;
        private final String codeString;

        public StringTokenizer(String sBlockOn, char chCode) {
            this.sBlockOn = sBlockOn;
            codeString = "" + chCode;
            Formatter ref = new java.util.Formatter();
            codeStringReplacement = ".:" + DatatypeConverter.printHexBinary(codeString.getBytes()) + ":.";
        }

        public String encode(String sSource) {
            sSource = sSource.replace(codeString, codeStringReplacement);
            return sSource.replace(sBlockOn, codeString);
        }

        public String decode(String sSource) {
            sSource.replace(codeString, sBlockOn);
            sSource = sSource.replace(codeStringReplacement, codeString);
            return sSource;
        }

        public char getCode() {
            return codeString.charAt(0);
        }

        public String getStirng() {
            return sBlockOn;
        }
    }
    StringTokenizer blockCommentOn;
    StringTokenizer blockCommentOff;
    StringTokenizer lineCommentOn;

    public SourceCodeFormatter() {
        blockCommentOn = new StringTokenizer("/*", '$');
        blockCommentOff = new StringTokenizer("*/", '&');
        lineCommentOn = new StringTokenizer("//", '#');
    }

    public String format(String sText, String sLevelPrefix) {
        if (sText == null || sLevelPrefix == null) {
            return "";
        }
        sText = this.lineCommentOn.encode(sText);
        sText = this.blockCommentOff.encode(sText);
        sText = this.blockCommentOn.encode(sText);

        int pwComment = 0;

        char chStart = '{';
        char chStop = '}';
        StringBuilder sb = new StringBuilder();
        int iLevel = 0;
        char[] chars = sText.toCharArray();
        boolean bIgnoreWS = false;
        for (char ch : chars) {
            switch (pwComment) {
                case 0: {
                    if (ch == blockCommentOn.getCode()) {
                        pwComment = 2; // flag block comment
                        sb.append(blockCommentOn.getStirng());
                        bIgnoreWS = false;
                        continue;
                    }
                    if (ch == lineCommentOn.getCode()) {
                        pwComment = 1; // flag linear comment
                        sb.append(lineCommentOn.getStirng());
                        bIgnoreWS = false;
                        continue;
                    }
                }
                case 1: {
                    if (ch == '\n') {
                        pwComment = 0; // passthru of single-line comment ends
                        bIgnoreWS = true;
                        sb.append(ch);
                        continue;
                    }
                }
                break;
                case 2: {
                    if (ch == blockCommentOff.getCode()) {
                        pwComment = 0; // passthru of block comment ends
                        bIgnoreWS = true;
                        sb.append(blockCommentOff.getStirng());
                        continue;
                    }
                }
                break;
            }
            if (pwComment == 0) {
                if (ch == '\n') {
                    bIgnoreWS = true;
                    sb.append(ch);
                    continue;
                }
                if (ch == chStart) {
                    iLevel++;
                }
                if (ch == chStop) {
                    iLevel--;
                }
                if (bIgnoreWS) {
                    boolean isWS = Character.isWhitespace(ch);
                    if (isWS) {
                        continue;
                    }
                    for (int ss = 0; ss < iLevel; ss++) {
                        sb.append(sLevelPrefix);
                    }
                }
            }
            sb.append(ch);
            bIgnoreWS = false;
        }
        sText = this.lineCommentOn.decode(sb.toString());
        sText = this.blockCommentOff.decode(sText);
        sText = this.blockCommentOn.decode(sText);
        return sText;
    }
}
