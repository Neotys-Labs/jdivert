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

package com.github.ffalcinelli.jdivert.network;

import static com.github.ffalcinelli.jdivert.Util.parseHexBinary;
import static com.github.ffalcinelli.jdivert.Consts.*;

import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by fabio on 29/10/2016.
 */
public abstract class IPHeaderTestCase {

    protected String rawDataHexString;
    protected byte[] rawData;
    protected IPHeader ipHdr;
    protected int ipVersion;
    protected String srcAddr;
    protected String dstAddr;
    protected String localhost;
    protected Protocol protocol;
    protected int ipHeaderLength;

    @Before
    public void setUp(){
        rawData = parseHexBinary(rawDataHexString);
    }

    @Test
    public void buildHeaders() {
        Header[] headers = Header.buildHeaders(rawData);
        assertEquals(ipHdr, headers[0]);
    }

    @Test
    public void version() {
        assertEquals(ipVersion, ipHdr.getVersion());
        assertEquals(ipHdr.getVersion(), IPv6Header.getVersion(ByteBuffer.wrap(rawData)));
        ipHdr.setVersion(ipVersion + 1);
        assertEquals(ipVersion + 1, ipHdr.getVersion());
    }

    @Test
    public void srcAddress() throws Exception {
        assertEquals(srcAddr, ipHdr.getSourceHostAddress());
        ipHdr.setSourceHostAddress(localhost);
        assertEquals(localhost, ipHdr.getSourceHostAddress());
        assertTrue(ipHdr.toString().contains(localhost));
    }

    @Test
    public void dstAddress() throws Exception {
        assertEquals(dstAddr, ipHdr.getDestinationHostAddress());
        ipHdr.setDestinationHostAddress(localhost);
        assertEquals(localhost, ipHdr.getDestinationHostAddress());
        assertTrue(ipHdr.toString().contains(localhost));
    }

    @Test
    public void nextHeaderProtocol() {
        assertEquals(protocol, ipHdr.getNextHeaderProtocol());
    }

    @Test
    public void headerLength() {
        assertEquals(ipHeaderLength, ipHdr.getHeaderLength());
    }

    @Test
    public void rawHeaderBytes() {
        assertArrayEquals(
                Arrays.copyOfRange(rawData, 0, ipHeaderLength),
                ipHdr.getRawHeaderBytes());
    }
}
