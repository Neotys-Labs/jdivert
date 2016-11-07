/*
 * Copyright (c) Fabio Falcinelli 2016.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.ffalcinelli.jdivert;

import org.junit.Test;

import static com.github.ffalcinelli.jdivert.Util.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by fabio on 26/10/2016.
 */
public class UtilTestCase {

    @Test
    public void hexConversion(){
        assertEquals("0123456789ABCDEF", printHexBinary(parseHexBinary("0123456789ABCDEF")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hexConversionOddCharacters(){
        assertEquals("0123456789ABCDE", printHexBinary(parseHexBinary("0123456789ABCDE")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hexConversionInvalidCharacters(){
        assertEquals("0123456789ABCDEZ", printHexBinary(parseHexBinary("0123456789ABCDEZ")));
    }
}
