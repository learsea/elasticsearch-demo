package starter.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import starter.model.goods2.Goods2;

@Repository
public interface Goods2Repository extends ElasticsearchRepository<Goods2, Long> {

}
