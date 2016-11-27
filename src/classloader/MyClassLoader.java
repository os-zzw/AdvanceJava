package classloader;

import java.io.*;

/**
 * Created by john on 2016/11/26.
 */
public class MyClassLoader extends ClassLoader {

    private String ClassDir;

    //生成加密class
    public static void main(String[] args) throws IOException {
        String src = "F:\\JavaWebWorkSpace\\AdvanceJava\\out\\production\\AdvanceJava\\classloader\\ClassLoaderAttachment.class";
        String file1 = "class";
        FileInputStream inputStream = new FileInputStream(src);
        int i = src.lastIndexOf("\\");
        String substring = src.substring(i + 1);
        String destPath = file1 + "\\" + substring;
        FileOutputStream outputStream = new FileOutputStream(destPath);
        cypher(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String newName = ClassDir + "\\" + name + ".class";
        try {
            FileInputStream inputStream = new FileInputStream(newName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            cypher(inputStream, bos);
            inputStream.close();
            byte[] bytes = bos.toByteArray();
            return defineClass(bytes, 0, bytes.length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    public MyClassLoader() {
    }

    public MyClassLoader(String path) {
        this.ClassDir = path;
    }

    public static void cypher(InputStream inputStream, OutputStream outputStream) throws IOException {
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b ^ 0xff);//进行异或运算

        }
    }
}
