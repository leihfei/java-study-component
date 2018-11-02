package com.lnlr.common.utils;

import com.lnlr.common.entity.HashPassword;

/**
 * @author:leihfei
 * @description: 密码处理工具
 * @date:Create in 11:29 2018/10/19
 * @email:leihfein@gmail.com
 */
public class PassUtils {

    /**
     * 散列次数
     */
    private static final int INTERATIONS = 1024;

    /**
     * 盐值长度
     */
    private static final int SALT_SIZE = 8;


    /**
     * @author: leihfei
     * @description: 对密码进行加密
     * @param: 密码
     * @return: hash密码
     * @date: 14:04 2018/8/7
     * @email: leihfein@gmail.com
     */
    public static HashPassword encryptPassword(String plainPassword) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = Encodes.encodeHex(salt);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, INTERATIONS);
        result.password = Encodes.encodeHex(hashPassword);
        return result;
    }

    /**
     * @param plainPassword: 铭文密码*
     * @param password       密文密码
     * @param salt           盐值
     * @author: leihfei
     * @description: 盐值密码
     * @return: 真假
     * @date: 14:05 2018/8/7
     * @email: leihfein@gmail.com
     */
    public static boolean validatePassword(String plainPassword, String password, String salt) {
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), INTERATIONS);
        String oldPassword = Encodes.encodeHex(hashPassword);
        return password.equals(oldPassword);
    }
}


