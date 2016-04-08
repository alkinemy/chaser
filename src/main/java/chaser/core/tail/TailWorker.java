package chaser.core.tail;

import chaser.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TailWorker {

	public void read(Path target, long position) {
		List<String> lines = new ArrayList<>();
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

			//TODO byteArrayOutputStream에 저장된 값 처리
			//TODO stream 처리 제대로 된건지 확인 필요
			//TODO event 넘기기
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(randomAccessFile);
		}
	}

}
