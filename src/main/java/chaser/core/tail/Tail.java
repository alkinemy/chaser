package chaser.core.tail;

import chaser.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Tail {
	private String lineStartPattern;

	public Tail(String lineStartPattern) {
		this.lineStartPattern = lineStartPattern;
	}

	public List<String> read(String targetFileName) {
		return read(new File(targetFileName));
	}

	public List<String> read(File target) {
		/**
		 * 1. http://www.informit.com/guides/content.aspx?g=java&seqNum=226 참고
		 * 파일 사이즈가 줄어드는경우 처리
		 * line 분리 처리
		 * tail & listener(handler?)
		 * listener처리
		 */
		List<String> lines = new ArrayList<>();
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(target, "rwd");
			randomAccessFile.seek(target.length());

			long fileLength = randomAccessFile.length();
			while(target.length() >= fileLength);

			do {
				Thread.sleep(1);

				randomAccessFile.seek(fileLength);
				StringBuilder sb = null;
				String line;
				while ((line = randomAccessFile.readLine()) != null) {
					if (line.startsWith(lineStartPattern)) {
						if (sb != null) {
							lines.add(sb.toString());
						}
						sb = new StringBuilder();
					}
					if (sb == null) {
						sb = new StringBuilder();
					}
					sb.append(line).append(System.lineSeparator());
				}
				lines.add(sb.toString());
				fileLength = randomAccessFile.length();
			} while(target.length() > fileLength);
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(randomAccessFile);
		}
	}
}
