
package com.gmpsystems.logcleaner.Services.GZip;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipFile {

    public static void compressGZIP(File input, File output) throws IOException {
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(output))) {
            try (FileInputStream in = new FileInputStream(input)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }

    public static void decompressGzip(File input, File output) throws IOException {
        try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input))) {
            try (FileOutputStream out = new FileOutputStream(output)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }
}