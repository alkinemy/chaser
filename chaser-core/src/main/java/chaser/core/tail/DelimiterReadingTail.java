package chaser.core.tail;

import chaser.core.chaser.Chaser;
import chaser.core.target.ChaseFile;
import chaser.util.ByteUtils;
import chaser.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class DelimiterReadingTail implements Tail {

	private Chaser chaser;

	private ByteArrayOutputStream byteArrayOutputStream;

	private final byte[] delimiter;

	public DelimiterReadingTail(String delimiter, Chaser chaser) {
		//TODO close처리
		//TODO 마지막에 남아있는걸 chaser가 처리하게 해야됨
		this.delimiter = delimiter.getBytes();
		this.chaser = chaser;
		byteArrayOutputStream = new ByteArrayOutputStream();
	}

	public Byte[] read(ChaseFile target) {
		RandomAccessFile randomAccessFile = null;
		int streamPosition = 0;
		try {
			File targetFile = target.getPath().toFile();
			byte[] buffer = newBuffer();

			do {
				long position = target.getPosition();
				if (targetFile.length() < target.getPosition()) {
					position = 0;
				}

				randomAccessFile = new RandomAccessFile(targetFile, "r");
				randomAccessFile.seek(position);
				int readCount;
				while ((readCount = randomAccessFile.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, streamPosition, readCount);

					byte[][] lines = ByteUtils.chop(byteArrayOutputStream.toByteArray(), delimiter);
					if (lines.length == 1) {
						streamPosition += readCount;
					} else {
						Arrays.stream(Arrays.copyOfRange(lines, 0, lines.length - 1))
							.forEach(line -> chaser.process(ByteUtils.toByteArray(line)));

						byteArrayOutputStream.reset();
						streamPosition = 0;
						byte[] last = getLast(lines);
						byteArrayOutputStream.write(last, streamPosition, last.length);
						streamPosition += last.length;
					}
					buffer = newBuffer();
				}
				target.setPosition(randomAccessFile.getFilePointer());
			} while(targetFile.length() != target.getPosition());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(randomAccessFile);
		}
	}

	private byte[] getLast(byte[][] lines) {
		return lines[lines.length - 1];
	}

	private byte[] newBuffer() {
		return new byte[4096];
	}

}
