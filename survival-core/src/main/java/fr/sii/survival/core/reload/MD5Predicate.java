package fr.sii.survival.core.reload;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import fr.sii.survival.core.util.ClassLoaderHelper;

/**
 * Predicate that generates a MD5 of the content of either a jar file or a class
 * folder. The generated MD5 is then compared to the previous generated value.
 * 
 * @author Aurélien Baudet
 *
 */
public class MD5Predicate implements Predicate<Object> {
	private static final Logger LOG = LoggerFactory.getLogger(MD5Predicate.class);

	private Hash currentMd5;

	@Override
	public boolean test(Object t) {
		try {
			Hash newMd5 = md5();
			LOG.debug("Class loader MD5: old={}, new={}", currentMd5, newMd5);
			if (!newMd5.equals(currentMd5)) {
				currentMd5 = newMd5;
				return true;
			}
		} catch (IOException | URISyntaxException e) {
			LOG.error("Failed to compute MD5 of extensions", e);
		}
		return false;
	}

	private Hash md5() throws IOException, URISyntaxException {
		URL url = ClassLoaderHelper.getExtensionsURL();
		// if ends with / => the URL points to a folder as how class loader works
		// if not => the URL points to a file (jar)
		if (url.toString().endsWith("/")) {
			return new FolderHash(url);
		} else {
			return new UrlHash(url);
		}
	}

	private interface Hash {
	}

	/**
	 * The hash can't be a string because the files could be ordered differently
	 * on every execution
	 * 
	 * @author Aurélien Baudet
	 *
	 */
	private static class FolderHash implements Hash {
		private Map<String, String> map;

		public FolderHash(URL url) throws URISyntaxException, IOException {
			map = generate(url);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj == null || !(obj instanceof FolderHash)) {
				return false;
			}
			FolderHash other = (FolderHash) obj;
			return new EqualsBuilder().append(map, other.map).isEquals();
		}

		public Map<String, String> generate(URL url) throws URISyntaxException, IOException {
			Map<String, String> map = new HashMap<>();
			for (File f : Files.fileTreeTraverser().breadthFirstTraversal(new File(url.toURI()))) {
				String hash;
				if (f.isDirectory()) {
					hash = Hashing.md5().hashString(f.getAbsolutePath(), Charsets.UTF_8).toString();
				} else {
					hash = Files.hash(f, Hashing.md5()).toString();
				}
				map.put(f.getAbsolutePath(), hash);
			}
			return map;
		}

		@Override
		public String toString() {
			return map.toString();
		}

	}

	private static class UrlHash implements Hash {
		private String hash;

		public UrlHash(URL url) throws IOException {
			hash = generate(url);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj == null || !(obj instanceof UrlHash)) {
				return false;
			}
			UrlHash other = (UrlHash) obj;
			return new EqualsBuilder().append(hash, other.hash).isEquals();
		}

		public String generate(URL url) throws IOException {
			return Hashing.md5().hashBytes(Resources.toByteArray(url)).toString();
		}

		@Override
		public String toString() {
			return hash;
		}

	}
}
