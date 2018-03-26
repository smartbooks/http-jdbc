package com.github.smartbooks.httpjdbc.core;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @author smartbooks@qq.com
 */
public class ConfigListener
    extends FileAlterationListenerAdaptor {
    @Override
    public void onFileChange(File file) {
        try {
            System.out.println(String.format("onFileChange %s", file.getAbsolutePath()));
            if (file.getAbsolutePath().equals(ConfigManage.filename)) {
                ConfigManage configManage = ConfigManage.load(file);
                QueryServiceManage.init(configManage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
