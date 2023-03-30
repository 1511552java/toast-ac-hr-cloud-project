package com.toast.util;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public class MemberStore {
    private static final ThreadLocal<String> MID = new ThreadLocal<>();
    private static final ThreadLocal<String> NAME = new ThreadLocal<>();
    public static void setMid(String mid) {
        MID.set(mid);
    }
    public static void setName(String name) {
        NAME.set(name);
    }
    public static String getMid() {
        return MID.get();
    }
    public static String getName() {
        return NAME.get();
    }
    public static void removeMid() {
        MID.remove();
    }
    public static void removeName() {
        NAME.remove();
    }
    public static void set(String mid, String name) {
        setMid(mid);
        setName(name);
    }
    public static void remove() {
        MID.remove();
        NAME.remove();
    }
}
