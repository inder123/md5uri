/*
 * Copyright (C) 2018 Inderjeet Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singhinderjeet.md5uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.singhinderjeet.md5uri.Md5Uri;

/**
 * Unit tests for {@link Md5Uri}
 *
 * @author Inderjeet Singh
 */
public class Md5UriTest {

    @Test
    public void testMd5ToBase66() {
        assertEquals("YrLNM591-5JhnaiKpxHrj1", Md5Uri.fromMd5("9e107d9d372bb6826bd81d3542a419d6"));
        assertEquals("kqrflQyUl9-0OeBg1S6", Md5Uri.fromMd5("0000bdd3bb56865852a632deadbc62fc"));
    }
    
    @Test
    public void testBase66ToMd5() {
        assertEquals("0000bdd3bb56865852a632deadbc62fc", Md5Uri.toMd5("kqrflQyUl9-0OeBg1S6"));
        assertEquals(32, Md5Uri.toMd5("kqrflQyUl9-0OeBg1S6").length());
    }

    @Test
    public void testMd5RoundTrip() {
        assertEquals("9e107d9d372bb6826bd81d3542a419d6", Md5Uri.toMd5(Md5Uri.fromMd5("9e107d9d372bb6826bd81d3542a419d6")));
        assertEquals("cbIKGiMVkLFTeenAa5kgO4", Md5Uri.fromMd5(Md5Uri.toMd5("cbIKGiMVkLFTeenAa5kgO4")));
    }

    @Test
    public void testMd5ToBase66RequiresValidInput() {
        try {
            Md5Uri.fromMd5("hello world");
            fail();
        } catch (NumberFormatException expected) {}
    }

    @Test
    public void testBase66ToMd5RequiresValidInput() {
        try {
            Md5Uri.toMd5("hello world");
            fail();
        } catch (NumberFormatException expected) {}
    }
}
