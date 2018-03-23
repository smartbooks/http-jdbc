package com.github.smartbooks.httpjdbc.core;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

public class ConfigListener
        extends FileAlterationListenerAdaptor
{
    @Override
    public void onFileChange(File file)
    {
        System.out.println(String.format("onFileChange %s", file.getAbsolutePath()));
        ConfigManage configManage = ConfigManage.load(file);
        QueryServiceManage.init(configManage);
    }
}
