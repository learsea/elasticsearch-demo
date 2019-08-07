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
import starter.model.Goods;
import starter.repository.GoodsRepository;

import java.util.List;

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

    public Page<Goods> search(Goods goods, int page, int size) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        queryBuilder.withFilter(boolQueryBuilder);
        if (!StringUtils.isEmpty(goods.getTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", goods.getTitle()));
        }
        if (!StringUtils.isEmpty(goods.getProperty())) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("property", goods.getProperty()));
        }
        if (!StringUtils.isEmpty(goods.getPropertyValue())) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("propertyValue", goods.getPropertyValue()));
        }
        if (!StringUtils.isEmpty(goods.getProperties())) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("properties", goods.getProperties()));
        }
        if (!StringUtils.isEmpty(goods.getStock())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("stock", goods.getStock()));
        }
        if (!StringUtils.isEmpty(goods.getSupplier())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("supplier", goods.getSupplier()));
        }
        // 搜索，获取结果
        Page<Goods> goodsPage = goodsRepository.search(boolQueryBuilder, PageRequest.of(page, size));
        return goodsPage;
    }

    public void createIndex() {
        if (!elasticsearchTemplate.indexExists(Goods.class)) {
            elasticsearchTemplate.createIndex(Goods.class);
        }
    }

    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Goods.class);
    }

    public Iterable<Goods> getById(String[] ids) {
        return goodsRepository.search(QueryBuilders.idsQuery().addIds(ids));
    }
}
