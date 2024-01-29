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

import java.util.ArrayList;

/**
 *
 * @author R.A. Nagy
 */
public class TaggedString implements java.io.Serializable, java.lang.Comparable {

    /**
     * The data. Can be anything. Use convenience functions for better
     * management.
     */
    private String sTag = "";    // (LVALUE)
    /**
     * The payload. Can be anything. Use convenience functions for better
     * management.
     */
    private String sValue = "";  // (RVALUE)

    /**
     * Null constructor.
     */
    public TaggedString() {
    }

    /**
     * Copy constructor.
     *
     * @param ref Another Tag()
     */
    public TaggedString(final TaggedString ref) {
        if (ref != null) {
            sTag = ref.sTag;
            sValue = ref.sValue;
        }
    }

    /**
     * Populated construction. Will use empty-string on any null parameters.
     *
     * @param sTag The tag
     * @param sValue The value.
     */
    public TaggedString(String sTag, String sValue) {
        if (sTag != null) {
            this.sTag = sTag;
        }
        if (sValue != null) {
            this.sValue = sValue;
        }
    }

    /**
     * Get a stringified value, to be used by assign(below).
     * @return The representational string.
     */
    @Override
    public String toString() {
        DynamicPair item = new DynamicPair(this.getTag(), this.getValue());
        return item.toString();
    }

    /**
     * Populate using the stringified value, as created by .toString(above).
     * @param str
     * @return False if this instance was not changed.
     */
    public boolean assign(String str) {
        if (str == null) {
            return false;
        }
        DynamicPair tp = DynamicPair.FromString(str);
        if (tp.isNull()) {
            return false;
        }
        this.setTag(tp.getTag());
        this.setValue(tp.getValue());
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (obj instanceof TaggedString) {
            TaggedString ref = (TaggedString) obj;
            return (this.sTag.equals(ref.sTag) && this.sValue.equals(ref.sValue));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (sTag.hashCode() * 1000) + sValue.hashCode();
    }

    /**
     * Convenience function: Assign the empty string on null. Will also always
     * trim the assignment.
     *
     * @param sTag The string to assign.
     */
    public final void setTag(String sTag) {
        if (sTag == null) {
            sTag = "";
        }
        this.sTag = sTag;
    }

    /**
     * Convenience function: Will not return a null.
     *
     * @return Representational tag.
     */
    public String getTag() {
        if (sTag == null) {
            return "";
        }
        return sTag;
    }

    /**
     * Convenience function: Assign the empty string on null. <b>NO TRIM</b>
     *
     * @param sValue The value to assign.
     */
    public final void setValue(String sValue) {
        if (sValue == null) {
            sValue = "";
        }
        this.sValue = sValue;
    }

    /**
     * Convenience function: Will not return a null.
     *
     * @return Representational value
     */
    public String getValue() {
        if (sValue == null) {
            return "";
        }
        return sValue;
    }

    /**
     * 2020 DELTA: Either the tag, or the value, can be null - but not both.
     *
     * @return False if the object is usable (!null)
     */
    public boolean isNull() {
        if (sTag == null || sTag.length() == 0) {
            if (sValue == null || sValue.length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comparison of TaggedString()
     *
     * @param obj A TaggedString.
     * @return As implied by Comparable.
     */
    public int compareTo(Object obj) {
        if (obj == null) {
            if (isNull()) {
                return 1;
            }
            return 0;
        }
        if (this == obj) {
            return 0;
        }
        if (isNull()) {
            return -1;
        }
        TaggedString ref = (TaggedString) obj;
        if (ref != null) {
            int iRes = this.sTag.compareTo(ref.sTag);
            if (iRes == 0) {
                return this.sValue.compareTo(ref.sValue);
            }
            return iRes;
        }
        return -1;
    }

    /**
     * Handy little function to search an array of TaggedString(), looking for all data
 tagged with a given token.
     *
     * @param sTag The name of the tag to look for.
     * @param tags The array to search.
     * @param bIgnoreCase True if you want case-insensitivity, else false.
     * @return The array of data found that matches the tag. The array length is
     * zero on error / not found.
     */
    public static TaggedString[] FindTagged(String sTag, TaggedString[] tags, boolean bIgnoreCase) {
        TaggedString[] result = new TaggedString[0];
        if (sTag == null || tags == null) {
            return result;
        }
        if (bIgnoreCase) {
            sTag = sTag.toLowerCase();
        }
        ArrayList<TaggedString> list = new ArrayList<TaggedString>();
        for (int ss = 0; ss < tags.length; ss++) {
            TaggedString ref = tags[ss];
            if (ref != null) {
                String strB = ref.sTag;
                if (bIgnoreCase) {
                    strB = ref.sTag.toLowerCase();
                }
                if (strB.equals(sTag)) {
                    list.add(ref);
                }
            }
        }
        result = new TaggedString[list.size()];
        list.toArray(result);
        return result;
    }

    /**
     * Handy little function to search an ArrayList of TaggedString(), looking for all
 data tagged with a given token.
     *
     * @param sTag The name of the tag to look for.
     * @param list The ArrayList to search.
     * @param bIgnoreCase True if you want case-insensitivity, else false.
     * @return The array of data found that matches the tag. The array length is
     * zero on error / not found.
     */
    public static TaggedString[] FindTagged(String sTag, ArrayList<TaggedString> list, boolean bIgnoreCase) {
        TaggedString[] thunk = new TaggedString[list.size()];
        list.toArray(thunk);
        return FindTagged(sTag, thunk, bIgnoreCase);
    }
}
