package fr.sii.survival.core.domain.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

import fr.sii.survival.core.exception.MimetypeDetectionException;

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
	private String content;

	/**
	 * The mimetype of the image
	 */
	private String mimetype;

	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	public ServerImage() {
		super();
	}

	/**
	 * Initialize the image with the content. The content is encoded into base64
	 * 
	 * @param content
	 *            the content of the image
	 * @param mimetype
	 *            the mimetype of the image
	 */
	public ServerImage(byte[] content, String mimetype) {
		super();
		this.content = new String(Base64.getEncoder().encode(content));
		this.mimetype = mimetype;
	}

	/**
	 * Initialize the image with the base64 content. The mimetype of the image
	 * is automatically detected
	 * 
	 * @param content
	 *            the content of the image (base64 encoded)
	 * @throws MimetypeDetectionException
	 *             when mimetype detection failed
	 */
	public ServerImage(byte[] content) throws MimetypeDetectionException {
		this(content, getMimetype(content));
	}

	/**
	 * Reads the stream of the image and convert it to base64. The stream is
	 * automatically closed.
	 * 
	 * @param stream
	 *            the stream used to read the image content
	 * @param mimetype
	 *            the mimetype of the image
	 * @throws IOException
	 *             when the stream can't be read
	 */
	public ServerImage(InputStream stream, String mimetype) throws IOException {
		this(read(stream), mimetype);
	}

	/**
	 * Reads the stream of the image and convert it to base64. The stream is
	 * automatically closed.
	 * 
	 * @param stream
	 *            the stream used to read the image content
	 * @throws IOException
	 *             when the stream can't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype detection failed
	 */
	public ServerImage(InputStream stream) throws IOException, MimetypeDetectionException {
		this(read(stream));
	}

	/**
	 * Reads the image file and convert it to base64
	 * 
	 * @param file
	 *            the file that represents the image to read
	 * @param mimetype
	 *            the mimetype of the image
	 * @throws IOException
	 *             when the file can't be read
	 */
	public ServerImage(File file, String mimetype) throws IOException {
		this(new FileInputStream(file), mimetype);
	}

	/**
	 * Reads the image file and convert it to base64
	 * 
	 * @param file
	 *            the file that represents the image to read
	 * @throws IOException
	 *             when the file can't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype detection failed
	 */
	public ServerImage(File file) throws IOException, MimetypeDetectionException {
		this(new FileInputStream(file));
	}

	/**
	 * Reads the URL that points to the image and convert it to base64
	 * 
	 * @param url
	 *            the URL that points to the image to read
	 * @param mimetype
	 *            the mimetype of the image
	 * @throws IOException
	 *             when the URL can't be read
	 */
	public ServerImage(URL url, String mimetype) throws IOException {
		this(url.openStream(), mimetype);
	}

	/**
	 * Reads the URL that points to the image and convert it to base64
	 * 
	 * @param url
	 *            the URL that points to the image to read
	 * @throws IOException
	 *             when the URL can't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype detection failed
	 */
	public ServerImage(URL url) throws IOException, MimetypeDetectionException {
		this(url.openStream());
	}

	/**
	 * Search for image file relative to the application classpath
	 * 
	 * @param relativePath
	 *            the path of the image (relative to application classpath)
	 * @param mimetype
	 *            the mimetype of the image
	 * @throws IOException
	 *             when the image can't be read
	 */
	public ServerImage(String relativePath, String mimetype) throws IOException {
		this(ServerImage.class.getResourceAsStream("/" + relativePath), mimetype);
	}

	/**
	 * Search for image file relative to the application classpath
	 * 
	 * @param relativePath
	 *            the path of the image (relative to application classpath)
	 * @throws IOException
	 *             when the image can't be read
	 * @throws MimetypeDetectionException
	 *             when mimetype detection failed
	 */
	public ServerImage(String relativePath) throws IOException, MimetypeDetectionException {
		this(ServerImage.class.getResourceAsStream("/" + relativePath));
	}

	public String getContent() {
		return content;
	}

	public String getMimetype() {
		return mimetype;
	}

	private static String getMimetype(byte[] content) throws MimetypeDetectionException {
		try {
			return Magic.getMagicMatch(content, true).getMimeType();
		} catch(MagicParseException | MagicMatchNotFoundException | MagicException e) {
			throw new MimetypeDetectionException(e);
		}
	}

	private static byte[] read(InputStream stream) throws IOException {
		if (stream == null) {
			throw new IOException("The provided stream is null, it may points to unexisting file");
		}
		try {
			return ByteStreams.toByteArray(stream);
		} finally {
			Closeables.close(stream, true);
		}
	}

}
