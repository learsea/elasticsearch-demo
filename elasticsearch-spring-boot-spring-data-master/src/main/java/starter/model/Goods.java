package starter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "goods-test", type = "goods")
public class Goods {
    /**
     * {
     * "id": 1,
     * "title":"皇家小奶罐幼猫粮奶糕1-4月孕猫离乳期断奶猫粮BK34/400g*3袋",
     * "supplier":"皇家宠物食品旗舰店 ",
     * "property":"品牌 适用阶段 包装体积 毛重",
     * "propertyValue":"ROYAL CANIN/皇家 幼猫 0.2 1300g",
     * "properties":"品牌:ROYAL CANIN/皇家 适用阶段:幼猫 包装体积:0.2 毛重:1300g",
     * "stock":3000
     * }
     */

    /**
     * {
     * 	"id": 1,
     * 	"title": "皇家小奶罐幼猫粮奶糕1-4月孕猫离乳期断奶猫粮BK34/400g*3袋",
     * 	"supplier": "皇家宠物食品旗舰店 ",
     * 	"properties": [{
     * 			"property": "品牌",
     * 			"propertyValue": "ROYAL CANIN/皇家"
     *                },
     *        {
     * 			"property": "适用阶段",
     * 			"propertyValue": "幼猫"
     *        },
     *        {
     * 			"property": "包装体积",
     * 			"propertyValue": "0.2"
     *        },
     *        {
     * 			"property": "毛重",
     * 			"propertyValue": "300g"
     *        }
     *
     * 	],
     *
     * 	"stock": 3000
     * }
     * **/
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String property;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String propertyValue;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String properties;

    @Field(type = FieldType.Keyword)
    private String supplier;

    private Float stock;
}
