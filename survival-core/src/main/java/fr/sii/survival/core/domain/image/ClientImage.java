package fr.sii.survival.core.domain.image;

/**
 * Image that is located on the client side. The image is identified by a name
 * and a folder. The client implementation will resolve the image using the
 * provided name and folder and generate the full URL of the image.
 * 
 * @author aurelien
 *
 */
public class ClientImage implements Image {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((folder == null) ? 0 : folder.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientImage other = (ClientImage) obj;
		if (folder == null) {
			if (other.folder != null)
				return false;
		} else if (!folder.equals(other.folder))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientImage [name=").append(name).append(", folder=").append(folder).append("]");
		return builder.toString();
	}
}
