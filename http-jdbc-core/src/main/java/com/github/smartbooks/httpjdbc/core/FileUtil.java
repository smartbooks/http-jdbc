package com.github.smartbooks.httpjdbc.core;

import java.io.*;

/**
 * @author smartbooks@qq.com
 */
public class FileUtil {
    public static String readFile(File file) {
        StringBuilder textContent = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                textContent.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textContent.toString();
    }

    public static void writeFile(File file, String text) {
        try {
            if (file.exists()) { file.delete(); }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

            bw.write(text);

            bw.flush();

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
