package fr.sii.survival.core.domain.image;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import fr.sii.survival.core.util.ClassLoaderHelper;

public class UriImage implements ServerImage {
	private static long counter = 0;

	/**
	 * The id of the image
	 */
	private String id;
	
	/**
	 * The URI of the image
	 */
	private URI uri;

	public UriImage(String path) throws IOException, URISyntaxException {
		this(check(ClassLoaderHelper.getResource(path), path));
	}

	public UriImage(URL url) throws IOException, URISyntaxException {
		this(url.toURI());
	}

	public UriImage(File file) throws IOException {
		this(check(file).toURI());
	}

	public UriImage(URI uri) {
		super();
		this.id = "UrlImage"+(counter++);
		this.uri = uri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	private static URL check(URL resource, String path) throws IOException {
		if(resource==null) {
			throw new IOException("The file "+path+" doesn't exists");
		}
		return resource;
	}

	private static File check(File file) throws IOException {
		if(!file.exists()) {
			throw new IOException("The file "+file.getPath()+" doesn't exists (absolute path: "+file.getAbsolutePath()+")");
		}
		return file;
	}
}
