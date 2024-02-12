/**
 * 1.列出指定目录中所有的子文件名与所有的子目录名，要求目录名与文件名分开列出。
 */
package sy7.java1;

import java.io.File;

public class DirLister {
    private static void listFilesAndDirs(File dirPath) {
        if (dirPath == null || !dirPath.isDirectory()) {
            return;
        }
        File[] filesAndDirs = dirPath.listFiles();
        for (File fileOrDir : filesAndDirs) {
            if (fileOrDir.isFile()) {
                System.out.println("    文件：" + fileOrDir.getName());
            } else {
                System.out.println("    目录：" + fileOrDir.getName());
                listFilesAndDirs(fileOrDir);
            }
        }
    }

    public static void main(String[] args) {
        String rootPath = "D:\\IdeaProjects"; // 指定要列出子文件和子目录的根目录
        File rootDir = new File(rootPath);

        // 如果根目录不存在，则提示并退出程序
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("指定的根目录不存在或不是一个目录！");
            return;
        }

        // 列出所有的子文件和子目录
        File[] filesAndDirs = rootDir.listFiles();
        for (File fileOrDir : filesAndDirs) {
            if (fileOrDir.isFile()) { // 如果是文件，则直接输出文件名
                System.out.println("文件：" + fileOrDir.getName());
            } else { // 如果是目录，则打印输出目录名称，并递归列出子文件和子目录
                System.out.println("目录：" + fileOrDir.getName());
                listFilesAndDirs(fileOrDir); // 递归调用自己来列出该子目录下的所有子文件和子目录
            }
        }
    }


}

