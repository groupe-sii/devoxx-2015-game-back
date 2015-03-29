package fr.sii.survival.util;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import fr.sii.survival.config.StaticResourcesConfig;
import fr.sii.survival.core.util.FileUtil;

public class ImageUrlUtil {
	private static URI baseUrl;
	
	private ImageUrlUtil() {
		super();
	}
	
	public static void init() {
		getBaseUrl();
	}
	
	public static URI getBaseUrl() {
		if(baseUrl==null) {
			baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().encode().toUri();
		}
		return baseUrl;
	}

	/**
	 * Generate relative path by removing either the classpath folder for
	 * resources or the temporary folder used for sprite generation.
	 * 
	 * @param path
	 *            the path (may be relative to classpath or absolute on file
	 *            system)
	 * @return the relative path
	 * @throws IOException
	 *             when the temporary for sprites generation couldn't be
	 *             accessed
	 */
	public static String getRelativePath(String path) throws IOException {
		// replace classpath folder that contains images to relative URL
		String relative = path.replaceFirst("^.*" + StaticResourcesConfig.STATIC_RESOURCE_FOLDER_PATH, "");
		// replace temporary sprite folder absolute path to relative URL
		return relative.replace("file:", "").replace(FileUtil.getDirectory(StaticResourcesConfig.SPRITE_GENERATION_FOLDER).toAbsolutePath().toString(), "");
	}

	/**
	 * Generate an absolute URL for the provided path
	 * 
	 * @param builder
	 *            the spring helper used to generate the absolute URL
	 * @param relativePath
	 *            the relative path to append to the base URL provided by Spring
	 * @return the absolute URL
	 */
	public static URI absoluteUrl(UriComponentsBuilder builder, String relativePath) {
		return builder.path("/").path(StaticResourcesConfig.STATIC_RESOURCE_URL_PREFIX).path(relativePath).build().encode().toUri();
	}

	/**
	 * Generate an absolute URL for the provided path. The application base URL
	 * is determined using the provided HTTP request.
	 * 
	 * @param request
	 *            the HTTP request that will be used for identifying the base
	 *            URL of the application
	 * @param relativePath
	 *            the relative path to append to the base URL provided by Spring
	 * @return the absolute URL
	 */
	public static URI absoluteUrl(HttpServletRequest request, String relativePath) {
		return absoluteUrl(ServletUriComponentsBuilder.fromContextPath(request), relativePath);
	}

	/**
	 * Generate an absolute URL for the provided URI. The application base URL
	 * is determined using the provided HTTP request.
	 * 
	 * @param request
	 *            the HTTP request that will be used for identifying the base
	 *            URL of the application
	 * @param uri
	 *            the URI that may point to a file on the system
	 * @return the absolute URL
	 * @throws IOException
	 *             when the temporary for sprites generation couldn't be
	 *             accessed
	 */
	public static URI absoluteUrl(HttpServletRequest request, URI uri) throws IOException {
		return absoluteUrl(request, getRelativePath(uri.toString()));
	}

	/**
	 * Generate an absolute URL for the provided URI. The application base URL
	 * is determined using the current HTTP request.
	 * 
	 * @param uri
	 *            the URI that may point to a file on the system
	 * @return the absolute URL
	 * @throws IOException
	 *             when the temporary for sprites generation couldn't be
	 *             accessed
	 */
	public static URI absoluteUrl(URI uri) throws IOException {
		return absoluteUrl(UriComponentsBuilder.fromUri(getBaseUrl()), getRelativePath(uri.toString()));
	}
}
