/*
 * The MIT License
 *
 * Copyright 2020 - 2024 Randall Nagy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.soft9000b.SqlDynamics;

/**
 * <p>
 A TaggedString + Value pair is the simple structure into which all external
 definitions can be parsed.</p><p>
 * Definitions are merely split apart based upon a known separating character or string.</p><p>
 * Because there is no implicit record delimiter, unless you explicitly assume
 * one (for example, a newline) on your storage medium, this class is of little value.</p><p>
 * Because both a block <i>start</i> and a block <i>stop</i> character have been
 * defined, either the <b>TagBlock</b> or the <b>TagStatement</b> are a lot more
 * useful.</p>
 *
 * @author Professor Nagy
 */
public class DynamicPair extends TaggedString implements java.io.Serializable {

    /**
     * The default separator.
     */
    public static final String DEFAULT_SEP = "=";
    public String sSep = DEFAULT_SEP;

    public DynamicPair() {
    }

    public DynamicPair(String sTag, long lValue) {
        this.setTag(sTag);
        this.setValue(Long.toString(lValue));
    }

    public DynamicPair(String sTag, int lValue) {
        this.setTag(sTag);
        this.setValue(Integer.toString(lValue));
    }

    public DynamicPair(String sTag, String sValue) {
        this.setTag(sTag);
        this.setValue(sValue);
    }

    public DynamicPair(final DynamicPair pair) {
        if (pair == null) {
            this.setTag(null);
            this.setValue(null);

        } else {
            this.setTag(pair.getTag());
            this.setValue(pair.getValue());
        }
    }

    public void trim() {
        setTag(getTag().trim());
        setValue(getValue().trim());
    }

    @Override
    public String toString() {
        // NOTE: Use TagValueLine if you have a NEWLINE delimited list -
        StringBuilder buffer = new StringBuilder();
        buffer.append(getTag());
        buffer.append(sSep);
        buffer.append(getValue());
        return buffer.toString();
    }

    public DynamicPair fromString(String str) {
        if (str == null) {
            return null;
        }
        int iPos = str.indexOf(this.sSep);
        if (iPos == -1) {
            return null;
        }
        int iStart = iPos;
        if (iStart == 0) {
            iStart++;
        }
        DynamicPair tvp = new DynamicPair(str.substring(0, iStart), str.substring(iPos + sSep.length(), str.length()));
        tvp.trim();
        return tvp;
    }

    public static DynamicPair FromString(String str) {
        if (str == null) {
            return null;
        }
        int iPos = str.indexOf(DEFAULT_SEP);
        if (iPos == -1) {
            return null;
        }
        int iStart = iPos;
        if (iStart == 0) {
            iStart++;
        }
        DynamicPair tvp = new DynamicPair(str.substring(0, iStart), str.substring(iPos + DEFAULT_SEP.length(), str.length()));
        tvp.trim();
        return tvp;
    }

}
