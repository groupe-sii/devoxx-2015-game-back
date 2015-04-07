package fr.sii.survival.config.options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game.extensions")
public class ExtensionsOptions {
	private URL url;

	private String path;

	private long reloadCheckRate;

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public long getReloadCheckRate() {
		return reloadCheckRate;
	}

	public void setReloadCheckRate(long reloadCheckRate) {
		this.reloadCheckRate = reloadCheckRate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) throws MalformedURLException {
		this.path = path;
		this.url = new File(path).toURI().toURL();
	}

}
