package com.yp.ddia.coding;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.yp.ddia.coding.PersonOuterClass.Person;
import com.yp.util.ByteReader;

public class PersonTest {

	@Test
	public void test() throws IOException {
		Person person = Person.newBuilder()
				.setUserName("Martin")
				.setFavoriteNumber(1337)
				.addInterests("daydreaming")
				.addInterests("hacking")
				.build();
		byte[] data = person.toByteArray();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream(data.length * 2);
		ByteReader.byteToHex(data, data.length, out);
		assertEquals(data.length * 2, out.size());
		System.out.println(out);
	}

}
