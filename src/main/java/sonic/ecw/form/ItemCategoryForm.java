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
public class ItemCategoryForm implements Serializable {

	private static final long serialVersionUID = -2357251867543293332L;

	/**
	 * 商品分類番号
	 */
	@Getter
	@Setter
	private String itemCategoryNum;

	/**
	 * 商品分類1コード
	 */
	@Getter
	@Setter
	private String itemCategory1Cd;

	/**
	 * 商品分類1名
	 */
	@Getter
	@Setter
	private String itemCategory1Name;

	/**
	 * 商品分類2コード
	 */
	@Getter
	@Setter
	private String itemCategory2Cd;

	/**
	 * 商品分類2名
	 */
	@Getter
	@Setter
	private String itemCategory2Name;

}