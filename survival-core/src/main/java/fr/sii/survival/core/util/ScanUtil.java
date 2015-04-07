package fr.sii.survival.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class ScanUtil {
	private ScanUtil() {
		super();
	}

	/**
	 * Utility class used for scanning available files into a folder.
	 * 
	 * @param folder
	 *            the root folder
	 * @param comparator
	 *            the comparator used to order the found files
	 * @param predicate
	 *            a condition to apply on each found file in order to filter it
	 * @param transform
	 *            apply a transformation on every found entry
	 * @return the list of found files
	 * @param <R>
	 *            The type of the result after transform
	 */
	public static <R> List<R> scan(String folder, Comparator<String> comparator, Predicate<String> predicate, Function<String, R> transform) {
		Reflections reflections = new Reflections(getBuilder(folder));
		List<String> files = new ArrayList<>(reflections.getResources(f -> true));
		Collections.sort(files, comparator);
		List<R> results = new ArrayList<>(files.size());
		for (String file : files) {
			if (isChild(folder, file) && predicate.test(file)) {
				results.add(transform.apply(file));
			}
		}
		return results;
	}

	/**
	 * Utility class used for scanning available files into a folder. No
	 * transformation is applied on elements
	 * 
	 * @param folder
	 *            the root folder
	 * @param comparator
	 *            the comparator used to order the found files
	 * @param predicate
	 *            a condition to apply on each found file in order to filter it
	 * @return the list of found files
	 */
	public static List<String> scan(String folder, Comparator<String> comparator, Predicate<String> predicate) {
		return scan(folder, comparator, predicate, f -> f);
	}

	/**
	 * Utility class used for scanning files into a folder. This is a shortcut
	 * of
	 * <code>ScanUtil.scan(folder, (a, b) -&gt; sign * a.compareToIgnoreCase(b), f -&gt; true, f -&gt; f)</code>
	 * 
	 * @param folder
	 *            the root folder
	 * @param reverse
	 *            sort found files in normal order or reverse order ?
	 * @return the list of found files
	 */
	public static List<String> scan(String folder, boolean reverse) {
		int sign = reverse ? -1 : 1;
		return scan(folder, (a, b) -> sign * a.compareToIgnoreCase(b), f -> true);
	}

	/**
	 * Utility class used for scanning files into a folder. This is a shortcut
	 * of
	 * <code>ScanUtil.scan(folder, (a, b) -&gt; sign * a.compareToIgnoreCase(b), f -&gt; true, transform)</code>
	 * 
	 * @param folder
	 *            the root folder
	 * @param reverse
	 *            sort found files in normal order or reverse order ?
	 * @param transform
	 *            apply a transformation on every found entry
	 * @return the list of found files
	 * @param <R>
	 *            The type of the result after transform
	 */
	public static <R> List<R> scan(String folder, boolean reverse, Function<String, R> transform) {
		int sign = reverse ? -1 : 1;
		return scan(folder, (a, b) -> sign * a.compareToIgnoreCase(b), f -> true, transform);
	}

	/**
	 * Utility class used for scanning files into a folder. This is a shortcut
	 * of <code>ScanUtil.scan(folder, false)</code>
	 * 
	 * @param folder
	 *            the root folder
	 * @return the list of found files
	 */
	public static List<String> scan(String folder) {
		return scan(folder, false);
	}

	/**
	 * Utility class used for scanning files into a folder. This is a shortcut
	 * of <code>ScanUtil.scan(folder, false, transform)</code>
	 * 
	 * @param folder
	 *            the root folder
	 * @param transform
	 *            apply a transformation on every found entry
	 * @return the list of found files
	 * @param <R>
	 *            The type of the result after transform
	 */
	public static <R> List<R> scan(String folder, Function<String, R> transform) {
		return scan(folder, false, transform);
	}
	
	private static ConfigurationBuilder getBuilder(String resource) {
		ConfigurationBuilder builder = ClassLoaderHelper.getReflectionsBuilder();
		builder.addUrls(ClasspathHelper.forResource(resource));
		builder.setScanners(new ResourcesScanner());
		return builder;
	}

	private static boolean isChild(String folder, String file) {
		File folderFile = new File(folder);
		File f = new File(file);
		while(f!=null && !folderFile.equals(f)) {
			f = f.getParentFile();
		}
		return folderFile.equals(f);
	}
}
