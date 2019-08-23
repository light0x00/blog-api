package com.light.blog.common.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * @auther: light
 * @since: 2018/10/8 11:12
 *
 * <p>
 * 对于Number类型:
 * <pre>
 *     1. null不视为0
 *     2. null不视为小于0
 *     3. null不视为小于0
 *     </pre>
 * </p>
 */
public class CheckUtils {

    /*Number*/

    /**
     * number==null or number>0 or number<0,return true.
     *
     * @param number
     * @return
     */
    public static boolean notZero(Number number) {
        return number == null || number.doubleValue() != 0;
    }

    /**
     * >0 or <0 return true
     * null or 0 return false.
     *
     * @param number
     * @return
     */
    public static boolean notZeroOrNil(Number number) {
        return number != null && number.doubleValue() != 0;
    }

    /**
     * >0 return true
     * null 0 <0 return false.
     *
     * @param number
     * @return
     */
    public static boolean gtZero(Number number) {
        return number != null && number.doubleValue() > 0;
    }

    /**
     * 所有元素都大于0(且不为null)返回true
     * numbers为empty时返回false!!!
     * @param numbers
     * @return
     */
    public static boolean allGtZero(Number...numbers){
        return !Arrays.stream(numbers).anyMatch(i->!gtZero(i));
    }

    public static boolean allEq(Number[]numbers,Number value){
        return !Arrays.stream(numbers).anyMatch(i->!i.equals(value));
    }

    public static boolean anyEq(Number[]numbers,Number value){
        return Arrays.stream(numbers).anyMatch(i->i.equals(value));
    }

    public static boolean allGteZero(Number...numbers){
        return !Arrays.stream(numbers).anyMatch(i->!gteZero(i));
    }

    /**
     * 任何一个元素小于等于0或者null返回true,否则false
     * numbers为empty时返回false!!!
     * @param numbers
     * @return
     */
    public static boolean anyLtZeroOrNil(Number...numbers){
        return Arrays.stream(numbers).anyMatch(CheckUtils::lteZeroOrNil);
    }

    public static boolean isNil(Number number){
        return number==null;
    }

    public static boolean allNil(Number...numbers){
        return !Arrays.stream(numbers).anyMatch(i->!isNil(i));
    }

    public static boolean anyNil(Number...numbers){
        return Arrays.stream(numbers).anyMatch(i->isNil(i));
    }

    public static boolean allNotNil(Number...numbers){
        return !anyNil(numbers);
    }

    public static boolean anyGtZero(Number...numbers){
        return Arrays.stream(numbers).anyMatch(CheckUtils::gtZero);
    }

    public static boolean gteZero(Number number) {
        return number != null && number.doubleValue() >= 0;
    }

    public static boolean gteZeroOrNil(Number number) {
        return number == null || number.doubleValue() >= 0;
    }

    public static boolean notGtZero(Number number){
        return !gtZero(number);
    }

    /**
     * >0 or null return true
     * >0 or 0 return false.
     *
     * @param number
     * @return
     */
    public static boolean gtZeroOrNil(Number number) {
        return number == null || number.doubleValue() > 0;
    }


    public static boolean lteZero(Number number) {
        return number != null && number.doubleValue() <= 0;
    }

    public static boolean lteZeroOrNil(Number number) {
        return number == null || number.doubleValue() <= 0;
    }

    public static boolean ltZero(Number number) {
        return number != null && number.doubleValue() < 0;
    }

    public static boolean ltZeroOrNil(Number number) {
        return number == null || number.doubleValue() < 0;
    }

    /*Collection*/

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /*String*/

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }
    public static boolean isAnyEmpty(CharSequence... css) {
        return StringUtils.isAnyEmpty(css);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    /*Array*/
    public static boolean isEmpty(Object [] arr){
        return ArrayUtils.isEmpty(arr);
    }
    public static boolean isNotEmpty(Object [] arr){
        return ArrayUtils.isNotEmpty(arr);
    }

}
