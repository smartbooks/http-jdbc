package com.github.smartbooks.httpjdbc.server.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * @author smartbooks@qq.com
 */
public class ServletUtil {

    public static String readPostBody(HttpServletRequest request) {
        String rs = "";
        BufferedReader br;
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                rs += str;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
