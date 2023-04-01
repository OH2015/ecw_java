package sonic.ecw.form;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品Formクラス
 *
 * <ul>
 * <li>Model-Controller間で商品データを媒介するためのDTOクラス。</li>
 * DTO: Data Transfer Object
 * 		基本的にDAOと合わせて、データを格納する入れ物としての機能だけを持つ。
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
public class ItemForm implements Serializable {

	private static final long serialVersionUID = -1869117848284842934L;

	/**
	 * 件数
	 */
	@Getter
	@Setter
	private Integer count;

	/**
	 * 商品コード
	 */
	@Getter
	@Setter
	private String itemCd;

	/**
	 * 商品名1
	 */
	@Getter
	@Setter
	private String itemName1;

	/**
	 * 商品名2
	 */
	@Getter
	@Setter
	private String itemName2;

	/**
	 * 商品画像1
	 */
	@Getter
	@Setter
	private String itemImage1;

	/**
	 * ブランド名
	 */
	@Getter
	@Setter
	private String brandName;

	/**
	 * 下代単価
	 */
	@Getter
	@Setter
	private String wholesalePrice;

	/**
	 * 在庫数量
	 */
	@Getter
	@Setter
	private BigDecimal stockNum;

	/**
	 * 在庫状態
	 */
	@Getter
	@Setter
	private String stockStatus;

	/**
	 * ステータスコード
	 */
	@Getter
	@Setter
	private String statusCd;

	/**
	 * ステータス名
	 */
	@Getter
	@Setter
	private String statusName;

	/**
	 * 商品分類1コード
	 */
	@Getter
	@Setter
	private String itemCategory1Cd;

	@Getter
	@Setter
	private String itemCategory1Name;

	/**
	 * 商品分類2コード
	 */
	@Getter
	@Setter
	private String itemCategory2Cd;

	@Getter
	@Setter
	private String itemCategory2Name;

	/**
	 * 型番
	 */
	@Getter
	@Setter
	private String modelNumber;

	/**
	 * JANコード
	 */
	@Getter
	@Setter
	private String janCd;

	// 課題#10 商品説明追加
	@Getter
	@Setter
	private String description;

}