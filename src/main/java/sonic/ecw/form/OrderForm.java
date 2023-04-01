package sonic.ecw.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品分類Formクラス
 *
 * <ul>
 * <li>Model-Controller間で商品分類データを媒介するためのDTOクラス。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
public class OrderForm implements Serializable {

	private static final long serialVersionUID = -2357251867543293332L;

	/**
	 * 件数
	 */
	@Getter
	@Setter
	private Integer count;

	@Getter
	@Setter
	private String orderDate;

	@Getter
	@Setter
	private String orderMonth;


	@Getter
	@Setter
	private String orderQuantityDaily;

	@Getter
	@Setter
	private String earningsDaily;

	@Getter
	@Setter
	private String orderQuantityMonthly;

	@Getter
	@Setter
	private String earningsMonthly;

}