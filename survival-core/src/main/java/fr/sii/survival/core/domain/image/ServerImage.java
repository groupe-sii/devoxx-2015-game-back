package fr.sii.survival.core.domain.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

/**
 * Image that is located on the server. The image is serialized into base64 in
 * order to be usable by the client.
 * 
 * @author aurelien
 *
 */
public class ServerImage implements Image {
	/**
	 * The content of the image base64 encoded
	 */
	private byte[] content;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public ServerImage() {
		super();
	}

	/**
	 * Initialize the image with the base64 content
	 * 
	 * @param content
	 *            the content of the image (base64 encoded)
	 */
	public ServerImage(byte[] content) {
		super();
		this.content = content;
	}

	/**
	 * Reads the stream of the image and convert it to base64. The stream is automatically closed.
	 * 
	 * @param stream
	 *            the stream used to read the image content
	 * @throws IOException
	 *             when the stream can't be read
	 */
	public ServerImage(InputStream stream) throws IOException {
		this(Base64.getEncoder().encode(read(stream)));
	}

	/**
	 * Reads the image file and convert it to base64
	 * 
	 * @param file
	 *            the file that represents the image to read
	 * @throws IOException
	 *             when the file can't be read
	 */
	public ServerImage(File file) throws IOException {
		this(new FileInputStream(file));
	}

	/**
	 * Reads the URL that points to the image and convert it to base64
	 * 
	 * @param url
	 *            the URL that points to the image to read
	 * @throws IOException
	 *             when the URL can't be read
	 */
	public ServerImage(URL url) throws IOException {
		this(url.openStream());
	}

	/**
	 * Search for image file relative to the application classpath
	 * 
	 * @param relativePath
	 *            the path of the image (relative to application classpath)
	 * @throws IOException
	 *             when the image can't be read
	 */
	public ServerImage(String relativePath) throws IOException {
		this(ServerImage.class.getResourceAsStream("/" + relativePath));
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	private static byte[] read(InputStream stream) throws IOException {
		if(stream==null) {
			throw new IOException("The provided stream is null, it may points to unexisting file");
		}
		try {
			return ByteStreams.toByteArray(stream);
		} finally {
			Closeables.close(stream, true);
		}
	}

}
