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
import starter.controller.goods1.GoodsVo;
import starter.model.goods1.Goods1;
import starter.repository.Goods1Repository;

@Service
public class Goods1Service {

    @Autowired
    private Goods1Repository goods1Repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引
     * 2019-08-09 14:07:15
     *
     * @param
     * @return void
     * @author caishiyu
     */
    public void createIndex() {
        elasticsearchTemplate.deleteIndex(Goods1.class);
        elasticsearchTemplate.createIndex(Goods1.class);
        elasticsearchTemplate.putMapping(Goods1.class);
    }

    /**
     * 删除索引
     * 2019-08-09 14:07:27
     *
     * @param
     * @return void
     * @author caishiyu
     */
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Goods1.class);
    }

    /**
     * 添加商品
     * 2019-08-09 14:07:37
     *
     * @param goods1
     * @return void
     * @author caishiyu
     */
    public void addGoods(Goods1 goods1) {
        goods1Repository.save(goods1);
    }

    /**
     * 删除商品
     * 2019-08-09 14:07:45
     *
     * @param id
     * @return void
     * @author caishiyu
     */
    public void deleteGoods(Long id) {
        Goods1 goods1 = new Goods1();
        goods1.setId(id);
        goods1Repository.delete(goods1);
    }

    /**
     * 根据id获取商品
     * 2019-08-09 14:07:53
     *
     * @param ids
     * @return java.lang.Iterable<starter.model.goods1.Goods1>
     * @author caishiyu
     */
    public Iterable<Goods1> getById(String[] ids) {
        return goods1Repository.search(QueryBuilders.idsQuery().addIds(ids));
    }

    /**
     * 搜索商品
     * 2019-08-09 14:08:00
     *
     * @param goodsVo
     * @param page
     * @param size
     * @return org.springframework.data.domain.Page<starter.model.goods1.Goods1>
     * @author caishiyu
     */
    public Page<Goods1> search(GoodsVo goodsVo, int page, int size) {
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
        Page<Goods1> goodsPage = goods1Repository.search(boolQueryBuilder, PageRequest.of(page, size));
        return goodsPage;
    }
}
