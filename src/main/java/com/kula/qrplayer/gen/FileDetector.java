package com.kula.qrplayer.gen;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.xerial.snappy.Snappy;

import com.kula.qrplayer.entity.CompressType;
import com.kula.qrplayer.entity.SliceFrame;
import com.kula.qrplayer.util.Configuration;

/**
 * The Class FileDetector.
 * Detect file meta info and split file to slice.
 * 
 * @version 1.0
 */
public class FileDetector implements FileDetectable {

	/** The Constant MAX_FILE_SIZE. */
	public static final int MAX_FILE_SIZE = Configuration.getINSTANCE().getMaxFileSize();

	/** The Constant SLICE_SIZE. */
	public static final int SLICE_SIZE = Configuration.getINSTANCE().getSliceSize();

	/** The Constant COMPRESS_TYPE. */
	public static final CompressType COMPRESS_TYPE = Configuration.getINSTANCE().getCompressType();

	@Override
	public void detect(final File input) {

		// set file name
		fileName = input.getName();

		// read file
		try {
			final byte[] plainFile = FileUtils.readFileToByteArray(input);
			fileSize = plainFile.length;
			comprFile = compress(plainFile);
		} catch (IOException e) {
			throw new RuntimeException(String.format("%s is not accessible.", fileName));
		}
		if (fileSize == 0 || fileSize > MAX_FILE_SIZE * 1024) {
			throw new RuntimeException(String.format(
					"%s is too large. File size MUST less than %sKB.", input.getName(),
					MAX_FILE_SIZE));
		}

		// set initialized at last.
		initialized = true;

	}

	/**
	 * Compress.
	 * 
	 * @param plaintext the plaintext
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private byte[] compress(final byte[] plaintext) throws IOException {
		switch (COMPRESS_TYPE) {
		case snappy:
			return Snappy.compress(plaintext);
		default:
			return plaintext;
		}
	}

	@Override
	public int getFileSize() {
		checkInit();
		return fileSize;
	}

	@Override
	public int getSliceCount() {
		checkInit();
		return (int) Math.ceil(comprFile.length * 1.0 / SLICE_SIZE);
	}

	@Override
	public String getFileName() {
		checkInit();
		return fileName;
	}

	@Override
	public CompressType getCompressType() {
		checkInit();
		return COMPRESS_TYPE;
	}

	@Override
	public int getCompressedSize() {
		checkInit();
		return comprFile.length;
	}

	@Override
	public Iterator<SliceFrame> iterator() {
		checkInit();
		return new SliceIterator();
	}

	@Override
	public void cleanup() {
		initialized = false;
		fileSize = 0;
		comprFile = null;
		fileName = StringUtils.EMPTY;
	}

	/**
	 * Check init.
	 */
	private void checkInit() {
		if (!initialized) {
			throw new RuntimeException("Detector has not been initialized.");
		}
	}

	/** The initialized. */
	private boolean initialized;

	/** The file size. */
	private int fileSize;

	/** The compr file. */
	private byte[] comprFile;

	/** The file name. */
	private String fileName;

	/**
	 * The Class SliceIterator.
	 */
	class SliceIterator implements Iterator<SliceFrame> {

		/** The index. */
		private int index;

		/** The position. */
		private int position;

		@Override
		public boolean hasNext() {
			return position < comprFile.length;
		}

		@Override
		public SliceFrame next() {
			final SliceFrame result = new SliceFrame();
			result.setIndex(index);
			result.setSlice(Arrays.copyOfRange(comprFile, position,
					Math.min(position + SLICE_SIZE, comprFile.length)));
			// update
			index++;
			position += SLICE_SIZE;
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove operation is not supported.");
		}
	}

}
