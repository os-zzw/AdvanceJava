package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by john(Zhewei) on 2016/12/7.
 */
public class FileUtils {

    /**
     * 拷贝文件,将文件复制到另外一个位置
     */
    public static void fileCopy(String path, String target) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(path);
            out = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            int bytesToRead;
            while ((bytesToRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
}
