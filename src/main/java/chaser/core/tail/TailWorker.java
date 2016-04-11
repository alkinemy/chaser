package chaser.core.tail;

import chaser.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class TailWorker {

	public byte[] read(Path target, long position) {
		RandomAccessFile randomAccessFile = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int streamPosition = 0;
		try {
			File targetFile = target.toFile();
			byte[] buffer = new byte[4096];

			do {
				randomAccessFile = new RandomAccessFile(targetFile, "r");
				randomAccessFile.seek(position);
				int readCount;
				while ((readCount = randomAccessFile.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, streamPosition, readCount);
					streamPosition += readCount;
					buffer = new byte[4096];
				}
			} while(targetFile.length() > randomAccessFile.length());

			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(byteArrayOutputStream);
			IOUtils.closeQuietly(randomAccessFile);
		}
	}

}
