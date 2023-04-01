package sonic.ecw.entity;

import java.math.BigDecimal;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品Entityクラス
 *
 * <ul>
 * <li>商品マスタの結果セットのレコードを格納するクラス。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Item {

	/**
	 * 件数
	 */
	@Getter
	@Setter
	private Integer count;

	/**
	 * 商品コード
	 */
	@Id
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
	private BigDecimal wholesalePrice;

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

//	商品分類1名
	@Getter
	@Setter
	private String itemCategory1Name;

	/**
	 * 商品分類2コード
	 */
	@Getter
	@Setter
	private String itemCategory2Cd;

//	商品分類2名
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