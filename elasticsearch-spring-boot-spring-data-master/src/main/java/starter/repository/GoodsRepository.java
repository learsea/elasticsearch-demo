package starter.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import starter.model.Goods;

@Repository
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {

}
