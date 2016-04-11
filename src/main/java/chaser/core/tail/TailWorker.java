package chaser.core.tail;

import chaser.core.target.ChaseFile;
import chaser.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TailWorker {

	public byte[] read(ChaseFile target) {
		RandomAccessFile randomAccessFile = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int streamPosition = 0;
		try {
			File targetFile = target.getPath().toFile();
			byte[] buffer = newBuffer();

			do {
				randomAccessFile = new RandomAccessFile(targetFile, "r");
				randomAccessFile.seek(target.getPosition());
				int readCount;
				while ((readCount = randomAccessFile.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, streamPosition, readCount);
					streamPosition += readCount;
					buffer = newBuffer();
				}
				target.setPosition(randomAccessFile.getFilePointer());
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

	private byte[] newBuffer() {
		return new byte[4096];
	}

}
