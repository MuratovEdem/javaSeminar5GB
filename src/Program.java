import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {

        String location = "./from"; //File path you are getting from file chooser
        String target = "./backup"; //target place you want to patse
        File locationFile = new File(location);
        File targetFile = new File(target);
        copyDirectory(locationFile, targetFile);
    }

    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        for (String file : source.list()) {
            copy(new File(source, file), new File(target, file));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

}
