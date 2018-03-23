package com.github.smartbooks.httpjdbc.core;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.util.concurrent.TimeUnit;

public class ConfigWatch
{
    private FileAlterationMonitor monitor;
    private long interval = 5;

    public ConfigWatch()
    {
        FileAlterationObserver observer = new FileAlterationObserver(ConfigManage.HTTPJDBC_CONF);
        observer.addListener(new ConfigListener());
        monitor = new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(interval), observer);
    }

    public void start()
    {
        try {
            monitor.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        try {
            monitor.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
