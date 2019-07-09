package com.budd.java.util;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * 空判断工具类
 * @author budd
 * @since 2019年7月8日23:08:06
 */
@Component
public class NullUtil {

    /**
     * 判断单个字符串是否为空
     * @param str 字符串
     * @return
     */
    private boolean isNull(String str) {
        if (Strings.isNullOrEmpty(str)) return true;
        return false;
    }

    /**
     * 判断单个字符串是否不为空
     * @param str 字符串
     * @return
     */
    private boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * 判断字符串数组
     * @param strs 字符串数组
     * @return
     */
    private boolean isNull(String... strs) {
        for (String str : strs) {
            if (isNull(str)) return true;
        }
        return false;
    }

    private boolean isNotNull(String... strs) {
        return !isNull(strs);
    }

    private boolean isNull(Object obj) {
        if (obj == null) return true;
        return false;
    }

    private boolean isNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) return true;
        }
        return false;
    }

    private boolean isNotNull(Object... objs) {
        for (Object obj : objs) {
            if (isNull(obj)) return false;
        }
        return true;
    }


    private boolean isNull(Map<?, ?> obj) {
        return obj == null || obj.keySet().isEmpty();
    }

    private boolean isNotNull(Map<?, ?> obj) {
        return !isNull(obj);
    }

    private boolean isNull(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    private boolean isNotNull(Collection<?> col) {
        return !isNull(col);
    }

    /**
     * 任意类型判空
     */
    private boolean isNullEveryType(Object... objs) {

        if (objs == null) {
            return true;
        }

        for (Object obj : objs) {

            if (obj == null) {
                return true;
            }

            if (obj instanceof Integer || obj instanceof Short || obj instanceof Byte) {
                String str = obj.toString();
                if (Integer.parseInt(str) == 0) {
                    return true;
                }
            } else if (obj instanceof Long) {
                String str = obj.toString();
                if (Long.parseLong(str) == 0L) {
                    return true;
                }
            } else if (obj instanceof Double) {
                String str = obj.toString();
                if (Double.parseDouble(str) == 0.0D) {
                    return true;
                }
            } else if (obj instanceof Float) {
                String str = obj.toString();
                if (Float.parseFloat(str) == 0.0F) {
                    return true;
                }
            } else if (obj instanceof String) {
                String str = String.valueOf(obj);
                if (Strings.isNullOrEmpty(str.trim())) {
                    return true;
                }
            } else if (obj instanceof Character) {
                Character c = (Character) obj;
                if (Character.isWhitespace(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNotNullEveryType(Object... objs) {
        return !isNullEveryType(objs);
    }

    public boolean any(Object... objs) {

        if (objs == null) {
            return false;
        }

        for (Object obj : objs) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
