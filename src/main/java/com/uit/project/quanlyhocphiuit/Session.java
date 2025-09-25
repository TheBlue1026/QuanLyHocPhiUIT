package com.uit.project.quanlyhocphiuit;

public class Session {
    private static String currentMssv;
    private static String currentMaHP;

    // --- MSSV ---
    public static String getCurrentMssv() {
        return currentMssv;
    }

    public static void setCurrentMssv(String mssv) {
        currentMssv = mssv;
    }

    // --- MaHP ---
    public static String getCurrentMaHP() {
        return currentMaHP;
    }

    public static void setCurrentMaHP(String maHP) {
        currentMaHP = maHP;
    }

    // Xóa session nếu cần
    public static void clear() {
        currentMssv = null;
        currentMaHP = null;
    }
}
