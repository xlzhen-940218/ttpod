package com.sds.android.ttpod.media.text;

import org.junit.Test;
import static org.junit.Assert.*;

public class TTTextUtilsTest {

    @Test
    public void testDecryptLyricKey() {
        String key = TTTextUtils.decryptLyricKey("周杰伦", "青花瓷", 123456);
        assertNotNull(key);
        assertEquals(16, key.length());
        System.out.println("Generated Lyric Key: " + key);
    }

    @Test
    public void testDecryptPictureKey() {
        String key = TTTextUtils.decryptPictureKey(1, 100, 200, "周杰伦");
        assertNotNull(key);
        assertTrue(key.endsWith("周杰伦"));
        System.out.println("Generated Picture Key: " + key);
    }
}
