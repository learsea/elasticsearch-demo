package starter.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import starter.model.goods1.Goods1;

@Repository
public interface Goods1Repository extends ElasticsearchRepository<Goods1, Long> {

}
