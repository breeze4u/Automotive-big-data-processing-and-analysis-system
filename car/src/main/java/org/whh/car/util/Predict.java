package org.whh.car.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Predict {
    public static String runPythonScript(String arg1, String arg2, String arg3, String arg4, String arg5)  {
        // 创建一个进程构造器
        ProcessBuilder pb = new ProcessBuilder("python", "predict.py", arg1, arg2, arg3, arg4, arg5);
        // 设置工作目录为D:\mywork\predict\src\main\java\model
        pb.directory(new File("D:\\py"));
        // 启动进程
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 读取进程的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            output.append(line).append("\n");
        }
        // 等待进程结束
        int exitCode = 0;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (exitCode != 0) {
            try {
                throw new IOException("Python script exited with non-zero exit code: " + exitCode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return output.toString().trim(); // 返回输出结果，并去除首尾空格和换行符
    }

}
