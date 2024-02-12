/**
 * 2.列出指定目录中所有扩展名为.java的文件。
 */
package sy7.java2;

import java.io.File;

public class JavaLister {
    public static void main(String[] args) {
        String rootPath = "D:\\IdeaProjects\\javaSy\\src\\sy1"; // 指定要查找 .java 文件的目录
        File rootDir = new File(rootPath);

        // 如果根目录不存在或不是目录，则提示并退出程序
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("指定的根目录不存在或不是一个目录！");
            return;
        }

        // 查找所有的 .java 文件
        findJavaFiles(rootDir);
    }

    private static void findJavaFiles(File dirPath) {
        if (dirPath == null || !dirPath.isDirectory()) {
            return;
        }
        File[] filesAndDirs = dirPath.listFiles();
        for (File fileOrDir : filesAndDirs) {
            if (fileOrDir.isFile() && fileOrDir.getName().endsWith(".java")) {
                System.out.println(fileOrDir.getAbsolutePath());
            } else if (fileOrDir.isDirectory()) {
                findJavaFiles(fileOrDir);
            }
        }
    }
}

