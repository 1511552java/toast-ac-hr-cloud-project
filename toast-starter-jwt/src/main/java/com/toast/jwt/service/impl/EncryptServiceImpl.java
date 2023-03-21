package com.toast.jwt.service.impl;

import com.toast.jwt.config.EncryptConfigProperties;
import com.toast.jwt.service.IEncryptService;
import com.toast.util.encrypt.EncryptSalt;
import com.toast.util.encrypt.MD5Code;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe 加密服务
 */
public class EncryptServiceImpl implements IEncryptService {
    @Autowired
    private EncryptConfigProperties encryptConfigProperties;
    @Override
    public String getEncryptPassword(String password) {
        String newPass = EncryptSalt.encrypt(password,
                this.encryptConfigProperties.getSalt(), this.encryptConfigProperties.getRepeat());
        MD5Code md5 = new MD5Code();
        for (int x = 0; x < this.encryptConfigProperties.getRepeat(); x ++ ) {
            newPass = md5.getMD5ofStr(newPass);
        }
        return newPass;
    }
}
