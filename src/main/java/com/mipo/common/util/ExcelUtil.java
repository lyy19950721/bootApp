package com.mipo.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ExcelUtil {

    /**
     * 模板下载
     * @param dir
     * @param title
     * @throws IOException
     */
    public static void downloadExcelTemplate(String dir, String title) throws IOException {

        HttpServletResponse response = SpringContextUtil.getHttpServletReponse();
        response.setCharacterEncoding("UTF-8");
        String attachment = "attachment; filename=" + title;
        response.setHeader("Content-disposition", attachment);
        response.setContentType("application/msexcel");

        InputStream is = new BufferedInputStream(new FileInputStream(new File(dir)));
        OutputStream os = response.getOutputStream();
        int i;
        while(-1 != (i=is.read())){
            os.write(i);
        }
        os.flush();
        if(null != os){
            os.close();
        }
        if(null != is){
            is.close();
        }
    }
}
