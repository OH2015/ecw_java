package sonic.ecw.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import sonic.ecw.entity.ItemCategory;

/**
 * 商品分類Dao(Data Access Object)クラス
 *
 * <ul>
 * <li>商品分類1,2マスタへのDBアクセスを担当するクラス。</li>
 * <li>/ecw/src/main/resources/META-INF/sonic/ecw/dao/ItemCategoryDao 配下の外部SQLを実行し、結果を受け取ります。</li>
 * <li>Doma（Java DBアクセスF/W）を使用しています。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * </ul>
 */
@Dao
@ConfigAutowireable
public interface ItemCategoryDao {

	/**
	 * 商品分類1＋商品分類2の結合データを取得
	 * @return 商品分類1＋商品分類2の結合データ
	 */
	@Select
	public List<ItemCategory> selectItemCategoryList();

	/**
	 * 商品分類1情報取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @return 商品分類1情報
	 */
	@Select
	public ItemCategory selectCurrentItemCategory1(String itemCategory1Cd);

	/**
	 * 商品分類2情報取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @param itemCategory2Cd 商品分類2コード
	 * @return 商品分類2情報
	 */
	@Select
	public ItemCategory selectCurrentItemCategory2(String itemCategory1Cd, String itemCategory2Cd);
}