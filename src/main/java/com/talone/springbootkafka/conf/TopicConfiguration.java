package com.talone.springbootkafka.conf;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class TopicConfiguration {

    private List<Topic> topics;

    @Data
    public static class Topic{
        /**
         * topic名称
         */
        private String name;
        /**
         * 分区
         */
        private int partitions;
        /**
         * 副本数无法超过broker数量
         */
        private int replicas;

        /**
         * 单位: ms
         * 如果不设置默认是7天：604800000
         * 不活跃的segment的时间超过该设置的时间，将会删除
         */
        private long retentionMs = 604800000L;
        /**
         *  单位：字节
         *  默认值：-1.即不删除。只考虑时间限制
         *  如果总的segment大小超过该设置，将删除不活跃segment.
         *  在高并发场景，如果短时间内有大量的消息，那么可以考虑配置该配置
         *  注意：该配置是分区级别的设置。例如：值为1G,3个分区的情况下就是3G.
         */
        private long retentionBytes = -1;
        /**
         *  segment的活跃时间
         *  默认值：7天
         *  使用场景：在低并发量的场景下，如果活跃segment超过七天依然为达到segment.byte的值，而你又想清理日志，
         *  那么你可以调整该值，强制日志滚动。生成新的活跃segment
         */
        private long segmentMs = 604800000L;
        /**
         *  segment大小，默认是1G.
         */
        private long segmentBytes = 1073741824L;

        NewTopic newTopic(){
            Map<String,String> conf = new HashMap<>();
            conf.put("retention.ms", retentionMs == 0 ? "604800000" : String.valueOf(retentionMs));
            conf.put("retention.bytes",retentionBytes == 0 ? "-1" : String.valueOf(retentionBytes));
            conf.put("segment.ms",segmentMs == 0 ? "604800000" : String.valueOf(segmentMs));
            conf.put("segment.bytes",segmentBytes == 0 ? "1073741824" : String.valueOf(segmentBytes));
            return TopicBuilder.name(name)
                    .partitions(partitions)
                    .replicas(replicas)
                    .configs(conf)
                    .build();
        }

    }
}
