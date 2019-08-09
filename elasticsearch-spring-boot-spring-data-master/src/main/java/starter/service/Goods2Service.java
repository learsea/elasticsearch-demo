package starter.service;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import starter.model.goods2.Goods2;
import starter.repository.Goods2Repository;

/**
 * 商品2
 * 2019/8/9 13:54
 *
 * @author caishiyu
 */
@Service
public class Goods2Service {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Goods2Repository goods2Repository;

    /**
     * 创建索引
     * 2019-08-09 14:08:28
     *
     * @param
     * @return void
     * @author caishiyu
     */
    public void createIndex() {
        elasticsearchTemplate.deleteIndex(Goods2.class);
        elasticsearchTemplate.createIndex(Goods2.class);
        elasticsearchTemplate.putMapping(Goods2.class);
    }

    /**
     * 添加商品
     * 2019-08-09 14:08:36
     *
     * @param goods
     * @return void
     * @author caishiyu
     */
    public void addGoods(Goods2 goods) {
        goods2Repository.save(goods);
    }

    /**
     * 搜索商品
     * 2019-08-09 14:08:42
     *
     * @param goods2
     * @param page
     * @param size
     * @return org.springframework.data.domain.Page<starter.model.goods2.Goods2>
     * @author caishiyu
     */
    public Page<Goods2> search(Goods2 goods2, int page, int size) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        queryBuilder.withFilter(boolQueryBuilder);
        if (!StringUtils.isEmpty(goods2.getTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", goods2.getTitle()));
        }
        if (goods2.getPropertyList() != null && !goods2.getPropertyList().isEmpty()) {
            goods2.getPropertyList().forEach(e -> {
                if (!StringUtils.isEmpty(e.getName())) {
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("propertyList", QueryBuilders.termQuery("propertyList.name", e.getName()), ScoreMode.None));
                }
                if (!StringUtils.isEmpty(e.getValue())) {
                    boolQueryBuilder.must(QueryBuilders.nestedQuery("propertyList", QueryBuilders.termQuery("propertyList.value", e.getValue()), ScoreMode.None));
                }
            });
        }
        if (!StringUtils.isEmpty(goods2.getStock())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("stock", goods2.getStock()));
        }
        if (!StringUtils.isEmpty(goods2.getSupplier())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("supplier", goods2.getSupplier()));
        }
        // 搜索，获取结果
        Page<Goods2> goodsPage = goods2Repository.search(boolQueryBuilder, PageRequest.of(page, size));
        return goodsPage;
    }
}
