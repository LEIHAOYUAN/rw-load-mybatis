package com.lhy.base;

public class DynamicDataSourceHolder {

    public static final String DB_MASTER = "master";

    public static final String DB_SLAVE = "slave";

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 获取线程的dbType
     *
     * @return
     */
    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     * 设置线程的dbType
     *
     * @param str
     */
    public static void setDbType(String str) {
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDbType() {
        contextHolder.remove();
    }

}