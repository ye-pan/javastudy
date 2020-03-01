package com.yp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字节
 * @author Administrator
 *
 */
public class BytePrint {
	
	private static final Logger log = LoggerFactory.getLogger(BytePrint.class);
	
	private static final String[] HEX_TABLE =  {
			"0", "1", "2", "3", "4", "5", "6", "7", 
			"8", "9", "A", "B", "C", "D", "E", "F"
	};
	
	public static void byteToHex(Path src, OutputStream out) {
		try (InputStream in = Files.newInputStream(src, StandardOpenOption.READ);) {
			byte[] buf = new byte[1024];
			int n = -1;
			while((n = in.read(buf)) > 0) {
				byteToHex(buf, n, out);
			}
		} catch(IOException e) {
			log.error("byte to hex error", e);
		}
	}

	public static void byteToHex(byte[] buf, int n, OutputStream out) throws IOException {
		for (int i = 0; i < n; i++) {
			out.write(HEX_TABLE[(buf[i] >> 4 & 0xF)].getBytes());
			out.write(HEX_TABLE[buf[i] & 0xF].getBytes());
		}
		out.flush();
	}
	
	public static void main(String[] args) {
		byteToHex(Paths.get("F:\\data\\foo.txt"), System.out);
	}
}
