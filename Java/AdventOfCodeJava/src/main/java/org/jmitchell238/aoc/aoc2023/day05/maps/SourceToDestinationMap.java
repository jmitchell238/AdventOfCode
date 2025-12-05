package org.jmitchell238.aoc.aoc2023.day05.maps;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SourceToDestinationMap {
    private List<RangeMapping> rangeMappings;

    public SourceToDestinationMap() {
        this.rangeMappings = new ArrayList<>();
    }

    public void addMapping(long destination, long source, long range) {
        long destinationOffset = 0;
        if (source > destination) {
            destinationOffset = source - destination;
            destinationOffset = destinationOffset * -1;
        } else {
            destinationOffset = destination - source;
        }

        rangeMappings.add(new RangeMapping(source, source + range - 1, destinationOffset));
    }

    public Long getDestinationForSource(long source) {
        for (RangeMapping rangeMapping : rangeMappings) {
            if (rangeMapping.containsSource(source)) {
                return source + rangeMapping.getDestinationOffset();
            }
        }
        return source;
    }

    @Data
    class RangeMapping {
        private long sourceLow;
        private long sourceHigh;
        private long destinationOffset;

        public RangeMapping(long sourceLow, long sourceHigh, long destinationOffset) {
            this.sourceLow = sourceLow;
            this.sourceHigh = sourceHigh;
            this.destinationOffset = destinationOffset;
        }

        public boolean containsSource(long source) {
            return source >= sourceLow && source <= sourceHigh;
        }
    }
}
