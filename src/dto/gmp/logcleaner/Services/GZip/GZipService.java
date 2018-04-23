package dto.gmp.logcleaner.Services.GZip;

import java.io.File;
import java.io.IOException;

public class GZipService {
    public void DecompressFile(String input, String output) {
        try {
            GZipFile.decompressGzip(new File(input), new File(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DecompressFile(File input, File output) {
        try {
            GZipFile.decompressGzip(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CompressFile(String input, String output) {
        try {
            GZipFile.compressGZIP(new File(input), new File(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CompressFile(File input, File output) {
        try {
            GZipFile.compressGZIP(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
