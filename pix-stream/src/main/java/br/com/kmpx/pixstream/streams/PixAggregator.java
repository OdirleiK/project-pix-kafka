package br.com.kmpx.pixstream.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kmpx.pixstream.serdes.PixSerdes;

@Service
public class PixAggregator {

	@Autowired
	public void aggregator(StreamsBuilder streamsBuilder) {
		KTable<String, Double> aggregateStream = streamsBuilder
				.stream("pix-topic", Consumed.with(Serdes.String(), PixSerdes.serdes()))
				.peek((key, value) -> System.out.println("Pix recebido: " + value.getChaveOrigem()))
				.filter((key, value) -> value.getValor() != null).groupBy((key, value) -> value.getChaveOrigem())
				.aggregate(() -> 0.0, (key, value, aggregate) -> (aggregate + value.getValor()),
						Materialized.with(Serdes.String(), Serdes.Double()));
		aggregateStream.toStream().print(Printed.toSysOut());
	}
}
