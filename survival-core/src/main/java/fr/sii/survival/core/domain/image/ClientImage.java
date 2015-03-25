package fr.sii.survival.core.domain.image;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Image that is located on the client side. The image is identified by a name
 * and a folder. The client implementation will resolve the image using the
 * provided name and folder and generate the full URL of the image.
 * 
 * @author aurelien
 *
 */
public class ClientImage implements Image {
	private static long counter = 0;
	
	/**
	 * The unique id of the image
	 */
	private String id;
	
	/**
	 * The name of the image (without extension)
	 */
	private String name;

	/**
	 * The folder that contains the image (optional)
	 */
	private String folder;
	
	/**
	 * Default constructor for internal use
	 * 
	 * @deprecated For technical use only, do not use it in your code
	 */
	@Deprecated
	public ClientImage() {
		super();
	}

	/**
	 * Define an image that is located in the client implementation.
	 * 
	 * @param name
	 *            the name of the image (without the extension)
	 * @param folder
	 *            the folder that contains the image
	 */
	public ClientImage(String name, String folder) {
		super();
		this.id = "ClientImage-"+(counter++);
		this.name = name;
		this.folder = folder;
	}

	/**
	 * Define an image that is located in the client implementation. No folder
	 * is specified so the default one will be used.
	 * 
	 * @param name
	 *            the name of the image (without the extension)
	 */
	public ClientImage(String name) {
		this(name, null);
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(folder).append(name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if(obj instanceof ClientImage) {
			return false;
		} else {
			ClientImage other = (ClientImage) obj;
			return new EqualsBuilder().append(folder, other.folder).append(name, other.name).isEquals();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientImage [name=").append(name).append(", folder=").append(folder).append("]");
		return builder.toString();
	}
}
