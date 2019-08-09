package starter.service;


import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import starter.api.GoodsVo;
import starter.model.Goods;
import starter.model.Goods2;
import starter.repository.GoodsRepository;

@Service
public class GoddsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void addGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    public void deleteGoods(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goodsRepository.delete(goods);
    }

    public Page<Goods> search(GoodsVo goodsVo, int page, int size) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        queryBuilder.withFilter(boolQueryBuilder);
        if (!StringUtils.isEmpty(goodsVo.getTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", goodsVo.getTitle()));
        }
        if (goodsVo.getPropertyList() != null && !goodsVo.getPropertyList().isEmpty()) {
            goodsVo.getPropertyList().forEach(e -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("property", e)));
        }
        if (goodsVo.getPropertyValueList() != null && !goodsVo.getPropertyValueList().isEmpty()) {
            goodsVo.getPropertyValueList().forEach(e -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("propertyValue", e)));
        }
        if (goodsVo.getPropertiesList() != null && !goodsVo.getPropertiesList().isEmpty()) {
            goodsVo.getPropertiesList().forEach(e -> boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("properties", e)));
        }
        if (!StringUtils.isEmpty(goodsVo.getStock())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("stock", goodsVo.getStock()));
        }
        if (!StringUtils.isEmpty(goodsVo.getSupplier())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("supplier", goodsVo.getSupplier()));
        }
        // 搜索，获取结果
        Page<Goods> goodsPage = goodsRepository.search(boolQueryBuilder, PageRequest.of(page, size));
        return goodsPage;
    }

    public void createIndex() {
        elasticsearchTemplate.createIndex(Goods.class);
        elasticsearchTemplate.putMapping(Goods.class);
        elasticsearchTemplate.createIndex(Goods2.class);
        elasticsearchTemplate.putMapping(Goods2.class);
    }

    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Goods.class);
    }

    public Iterable<Goods> getById(String[] ids) {
        return goodsRepository.search(QueryBuilders.idsQuery().addIds(ids));
    }
}
