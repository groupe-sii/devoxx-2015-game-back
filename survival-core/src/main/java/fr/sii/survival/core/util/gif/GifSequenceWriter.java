// 
//  GifSequenceWriter.java
//  
//  Created by Elliot Kroo on 2009-04-25.
//
// This work is licensed under the Creative Commons Attribution 3.0 Unported
// License. To view a copy of this license, visit
// http://creativecommons.org/licenses/by/3.0/ or send a letter to Creative
// Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.

package fr.sii.survival.core.util.gif;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import com.google.common.io.Closeables;

public class GifSequenceWriter implements Closeable, AutoCloseable {
	protected ImageWriter gifWriter;
	protected ImageWriteParam imageWriteParam;
	protected IIOMetadata imageMetaData;

	/**
	 * Creates a new GifSequenceWriter
	 * 
	 * @param outputStream
	 *            the ImageOutputStream to be written to
	 * @param imageType
	 *            one of the imageTypes specified in BufferedImage
	 * @param timeBetweenFramesMS
	 *            the time between frames in milliseconds
	 * @param loopContinuously
	 *            wether the gif should loop repeatedly
	 * @throws IIOException
	 *             if no gif ImageWriters are found
	 *
	 * @author Elliot Kroo (elliot[at]kroo[dot]net)
	 */
	public GifSequenceWriter(ImageOutputStream outputStream, int imageType, long timeBetweenFramesMS, boolean loopContinuously) throws IIOException, IOException {
		// my method to create a writer
		gifWriter = getWriter();
		imageWriteParam = gifWriter.getDefaultWriteParam();
		ImageTypeSpecifier imageTypeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(imageType);

		imageMetaData = gifWriter.getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);

		String metaFormatName = imageMetaData.getNativeMetadataFormatName();

		IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);

		IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");

		graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
		graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
		graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
		graphicsControlExtensionNode.setAttribute("delayTime", Long.toString(timeBetweenFramesMS / 10));
		graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

		IIOMetadataNode commentsNode = getNode(root, "CommentExtensions");
		commentsNode.setAttribute("CommentExtension", "Created by MAH");

		IIOMetadataNode appEntensionsNode = getNode(root, "ApplicationExtensions");

		IIOMetadataNode child = new IIOMetadataNode("ApplicationExtension");

		child.setAttribute("applicationID", "NETSCAPE");
		child.setAttribute("authenticationCode", "2.0");

		int loop = loopContinuously ? 0 : 1;

		child.setUserObject(new byte[] { 0x1, (byte) (loop & 0xFF), (byte) ((loop >> 8) & 0xFF) });
		appEntensionsNode.appendChild(child);

		imageMetaData.setFromTree(metaFormatName, root);

		gifWriter.setOutput(outputStream);

		gifWriter.prepareWriteSequence(null);
	}

	public void writeToSequence(RenderedImage img) throws IOException {
		gifWriter.writeToSequence(new IIOImage(img, null, imageMetaData), imageWriteParam);
	}

	/**
	 * Close this GifSequenceWriter object. This does not close the underlying
	 * stream, just finishes off the GIF.
	 */
	public void close() throws IOException {
		gifWriter.endWriteSequence();
	}

	/**
	 * Returns the first available GIF ImageWriter using
	 * ImageIO.getImageWritersBySuffix("gif").
	 * 
	 * @return a GIF ImageWriter object
	 * @throws IIOException
	 *             if no GIF image writers are returned
	 */
	private static ImageWriter getWriter() throws IIOException {
		Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("gif");
		if (!iter.hasNext()) {
			throw new IIOException("No GIF Image Writers Exist");
		} else {
			return iter.next();
		}
	}

	/**
	 * Returns an existing child node, or creates and returns a new child node
	 * (if the requested node does not exist).
	 * 
	 * @param rootNode
	 *            the <tt>IIOMetadataNode</tt> to search for the child node.
	 * @param nodeName
	 *            the name of the child node.
	 * 
	 * @return the child node, if found or a new node created with the given
	 *         name.
	 */
	private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
		int nNodes = rootNode.getLength();
		for (int i = 0; i < nNodes; i++) {
			if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
				return ((IIOMetadataNode) rootNode.item(i));
			}
		}
		IIOMetadataNode node = new IIOMetadataNode(nodeName);
		rootNode.appendChild(node);
		return (node);
	}

	public static void generate(List<BufferedImage> images, long delayBetweenFrames, boolean infinite, ImageOutputStream output) throws IOException {
		// grab the output image type from the first image in the sequence
		BufferedImage firstImage = images.get(0);

		// create a gif sequence with the type of the first image, 1 second
		// between frames, which loops continuously
		try(GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), delayBetweenFrames, infinite)) {
			// write out the first image to our sequence...
			writer.writeToSequence(firstImage);
			for (int i = 1; i < images.size(); i++) {
				BufferedImage nextImage = images.get(i);
				writer.writeToSequence(nextImage);
			}
		} finally {
			Closeables.close(output, false);
		}
	}
}