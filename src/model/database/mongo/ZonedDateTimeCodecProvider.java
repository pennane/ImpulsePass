package model.database.mongo;

import java.time.ZonedDateTime;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class ZonedDateTimeCodecProvider implements CodecProvider {
	@Override
	@SuppressWarnings("unchecked")
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		if (clazz == ZonedDateTime.class) {
			return (Codec<T>) new ZonedDateTimeCodec();
		}
		return null;
	}
}