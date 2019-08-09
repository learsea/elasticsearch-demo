package starter.api;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 2019/8/9 10:37
 *
 * @author caishiyu
 */
@Data
public class GoodsVo {
    private String title;

    private List<String> propertyList;

    private List<String> propertyValueList;

    private List<String> propertiesList;

    private String supplier;

    private Float stock;
}
