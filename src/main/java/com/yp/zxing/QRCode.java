package com.yp.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.util.Map;

public class QRCode {
    public static void main(String[] args) throws Exception {
        //String content = "http://192.168.1.7:8089/products/";
        //create(content);
        Result result = read(Path.of("F:\\data\\qrcode\\1565846930851.png"));
        System.out.println(result.getText());
    }

    static void create(String content) throws Exception {
        int with = 300;
        int heig = 300;
        String format = "png";
        Map<EncodeHintType, Object> map = Map.of(EncodeHintType.CHARACTER_SET, "UTF-8", EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M, EncodeHintType.MARGIN, 2);
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, with, heig, map);
        Path path = Path.of("F:\\data\\qrcode\\" + System.currentTimeMillis() + ".png");
        MatrixToImageWriter.writeToPath(matrix, format, path);
    }

    static Result read(Path source) throws Exception {
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(source.toFile()))));
        Map<DecodeHintType, Object> map = Map.of(DecodeHintType.CHARACTER_SET, "UTF-8");
        MultiFormatReader reader = new MultiFormatReader();
        return reader.decode(bitmap, map);
    }
}
