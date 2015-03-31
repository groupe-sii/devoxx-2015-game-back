package fr.sii.survival.ut.util;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;

import fr.sii.survival.core.util.ScanUtil;

public class ScanUtilTest {
	@Test
	public void scanFolder() {
		List<String> files = ScanUtil.scan("images/immobilize");
		Assert.assertEquals("should return 9 files", 9, files.size());
	}
}
