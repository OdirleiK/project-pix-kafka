package br.com.kmpx.pixstream.serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.kmpx.pixstream.dto.PixDTO;

public class PixSerdes extends Serdes.WrapperSerde<PixDTO> {

    public PixSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(PixDTO.class));
    }

    public static Serde<PixDTO> serdes() {
        JsonSerializer<PixDTO> serializer = new JsonSerializer<>();
        JsonDeserializer<PixDTO> deserializer = new JsonDeserializer<>(PixDTO.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
