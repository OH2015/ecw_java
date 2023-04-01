package sonic.ecw.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import sonic.ecw.entity.Item;
import sonic.ecw.entity.Order;


/**
 * 商品Dao(Data Access Object)クラス
 *
 * <ul>
 * <li>商品マスタへのDBアクセスを担当するクラス。</li>
 * <li>/ecw/src/main/resources/META-INF/sonic/ecw/dao/ItemDao 配下の外部SQLを実行し、結果を受け取ります。</li>
 * <li>Doma（Java DBアクセスF/W）を使用しています。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * </ul>
 *
 */
//課題#09 税抜下代単価の上限・下限を指定して絞り込み可能にする

@Dao
@ConfigAutowireable
public interface ItemDao {

	/**
	 * 商品リスト取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @param itemCategory2Cd 商品分類2コード
	 * @param keyword キーワード文字列
	 * @param offset 取得開始位置
	 * @param limit 取得件数
	 * @return 商品リスト
	 */
	@Select
	public List<Item> selectItemList(String itemCategory1Cd, String itemCategory2Cd, String keyword,long min_price,long max_price,int stockNum, Long offset, int limit);


	// 課題#10 商品情報取得
	@Select
	public Item selectItemDetailInfo(String item_cd);

	// 課題#10 おすすめ商品情報取得
	@Select
	public List<Item> selectRecommendationList(String item_cd,String category1_cd);

	// 拡張課題 受注履歴取得
	@Select
	public List<Order> selectOrderList();



}