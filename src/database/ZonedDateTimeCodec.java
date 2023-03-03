package database;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class ZonedDateTimeCodec implements Codec<ZonedDateTime> {

	@Override
	public ZonedDateTime decode(BsonReader reader, DecoderContext decoderContext) {
		BsonType current = reader.getCurrentBsonType();
		if (current.equals(BsonType.STRING)) {
			return ZonedDateTime.parse(reader.readString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		}

		if (current.equals(BsonType.DATE_TIME)) {
			Instant instant = Instant.ofEpochMilli(reader.readDateTime());
			return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		}

		if (!current.equals(BsonType.DOCUMENT)) {
			return null;
		}

		reader.readStartDocument();


		long date = reader.readDateTime("date");
		String zoneId = reader.readString("zoneId");

		reader.readEndDocument();

		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of(zoneId));
	}

	@Override
	public void encode(BsonWriter writer, ZonedDateTime value, EncoderContext encoderContext) {
		writer.writeStartDocument();

		writer.writeDateTime("date", value.toInstant().toEpochMilli());
		writer.writeString("zoneId", value.getZone().getId());

		writer.writeEndDocument();
	}

	@Override
	public Class<ZonedDateTime> getEncoderClass() {
		return ZonedDateTime.class;
	}
}
