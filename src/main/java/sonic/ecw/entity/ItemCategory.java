package sonic.ecw.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品分類Entityクラス
 *
 * <ul>
 * <li>商品分類1マスタ、商品分類2マスタの結果セットのレコードを格納するクラス。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class ItemCategory {

	/**
	 * 商品分類番号
	 */
	@Column
	@Getter
	@Setter
	private String itemCategoryNum;

	/**
	 * 商品分類1コード
	 */
	@Column
	@Getter
	@Setter
	private String itemCategory1Cd;

	/**
	 * 商品分類1名
	 */
	@Column
	@Getter
	@Setter
	private String itemCategory1Name;

	/**
	 * 商品分類2コード
	 */
	@Column
	@Getter
	@Setter
	private String itemCategory2Cd;

	/**
	 * 商品分類2名
	 */
	@Column
	@Getter
	@Setter
	private String itemCategory2Name;

}