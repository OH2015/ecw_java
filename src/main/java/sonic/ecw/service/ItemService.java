package sonic.ecw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sonic.ecw.dao.ItemDao;
import sonic.ecw.entity.Item;
import sonic.ecw.entity.Order;
import sonic.ecw.form.ItemForm;
import sonic.ecw.form.OrderForm;

/**
 * 商品Serviceクラス（ビジネスロジック）
 *
 * <ul>
 * <li>商品に関する処理本体（ビジネスロジック）を記述するクラス</li>
 * </ul>
 */

// 課題#02 キーワード検索で特定の文字列を入力するとエラー画面が表示される
//         500エラーの原因となるコードが、このクラスのどこかに存在します。見つけて修正してください。
//
// 課題#09 税抜下代単価の上限・下限を指定して絞り込み可能にする
//         画面から渡された価格帯上限／下限のパラメータを、ItemService.getItemListメソッドの引数に追加します。
//         引数で受け取った価格帯上限／下限のパラメータを更にItemDao.selectItemListメソッドに渡し、検索条件として使用します。

@Service
public class ItemService extends CommonService {

	@Autowired
	private ItemDao itemDao;

	/**
	 * 商品リスト取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @param itemCategory2Cd 商品分類2コード
	 * @param keyword キーワード文字列
	 * @param pageable ページャオブジェクト
	 * @return 商品リスト
	 */
	public PageImpl<ItemForm> getItemList(String itemCategory1Cd, String itemCategory2Cd, String keyword, String min_price, String max_price,int stockNum, Pageable pageable) {

		long min = (min_price == null || min_price =="") ?          0: Long.parseLong(min_price);
		long max = (max_price == null || max_price =="") ?  9999999999L:Long.parseLong(max_price);

		List<Item> itemEntityList = itemDao.selectItemList(itemCategory1Cd, itemCategory2Cd, keyword, min, max,stockNum, pageable.getOffset(), pageable.getPageSize());

		if(itemEntityList != null && itemEntityList.size() > 0) {
			int count = itemEntityList.get(0).getCount();

			ArrayList<ItemForm> itemFormList = new ArrayList<ItemForm>();

			// google検索ワード【Java 拡張for文】
			for(Item item : itemEntityList) {

				// google検索ワード【Java 三項演算子】
				String wholesalePrice = (item.getWholesalePrice() == null) ? "0" : item.getWholesalePrice().toString();

				ItemForm form = new ItemForm();
				form.setCount(item.getCount());
				form.setItemCd(item.getItemCd());
				form.setItemName1(item.getItemName1());
				form.setItemName2(item.getItemName2());
				form.setItemImage1(item.getItemImage1());
				form.setBrandName(item.getBrandName());
				form.setWholesalePrice(wholesalePrice);
				form.setStockNum(item.getStockNum());
				form.setStockStatus(item.getStockStatus());
				form.setStatusCd(item.getStatusCd());
				form.setStatusName(item.getStatusName());
				itemFormList.add(form);
			}
			return new PageImpl<ItemForm>(itemFormList, pageable, count);
		}

		return new PageImpl<ItemForm>(new ArrayList<ItemForm>(), pageable, 0);
	}


	// 課題#10 商品情報取得
	public ItemForm getItemDetailInfo(String item_cd) {
		//一致する商品詳細情報を1件だけ取ってくる
		Item item = itemDao.selectItemDetailInfo(item_cd);

		// 商品情報が取得できていなければ空のフォームを送る
		if(item==null || item.getItemCd() == "")return new ItemForm();

		String wholesalePrice = (item.getWholesalePrice() == null) ? "0" : item.getWholesalePrice().toString();

		ItemForm form = new ItemForm();

		// countは使わないので省略
		form.setItemCd(item.getItemCd());
		form.setItemName1(item.getItemName1());
		form.setItemName2(item.getItemName2());
		form.setItemImage1(item.getItemImage1());
		form.setBrandName(item.getBrandName());
		form.setWholesalePrice(wholesalePrice);
		form.setStockNum(item.getStockNum());
		form.setStockStatus(item.getStockStatus());
		form.setStatusCd(item.getStatusCd());
		form.setStatusName(item.getStatusName());

		// #10 非共通取得項目
		form.setItemCategory1Cd(item.getItemCategory1Cd());
		form.setItemCategory1Name(item.getItemCategory1Name());
		form.setItemCategory2Cd(item.getItemCategory2Cd());
		form.setItemCategory2Name(item.getItemCategory2Name());
		form.setModelNumber(item.getModelNumber());
		form.setJanCd(item.getJanCd());
		form.setDescription(item.getDescription());

		return form;
	}

	// 課題#10 おすすめ商品情報取得
	public ArrayList<ItemForm> getRecommendationList(String item_cd,String category1){
		// お勧め商品のEntityリストを取得
		List<Item> itemEntityList = itemDao.selectRecommendationList(item_cd,category1);

		if(itemEntityList != null && itemEntityList.size() > 0) {

			ArrayList<ItemForm> itemFormList = new ArrayList<ItemForm>();


			for(Item item : itemEntityList) {

				String wholesalePrice = (item.getWholesalePrice() == null) ? "0" : item.getWholesalePrice().toString();

				ItemForm form = new ItemForm();

				form.setItemCd(item.getItemCd());
				form.setItemName1(item.getItemName1());
				form.setItemName2(item.getItemName2());
				form.setItemImage1(item.getItemImage1());
				form.setBrandName(item.getBrandName());
				form.setWholesalePrice(wholesalePrice);
				form.setStockNum(item.getStockNum());
				form.setStockStatus(item.getStockStatus());
				form.setStatusCd(item.getStatusCd());
				form.setStatusName(item.getStatusName());
				itemFormList.add(form);
			}
			return itemFormList;
		}
		return new ArrayList<ItemForm>();
	}

	public ArrayList<OrderForm> getOrderList(){

		List<Order> orderEntityList = itemDao.selectOrderList();
		if(orderEntityList != null && orderEntityList.size() > 0) {

			ArrayList<OrderForm> orderFormList = new ArrayList<OrderForm>();

			for(Order order : orderEntityList) {
				OrderForm form = new OrderForm();

				form.setCount(order.getCount());
				form.setOrderQuantityDaily(order.getOrderQuantityDaily());
				form.setOrderQuantityMonthly(order.getOrderQuantityMonthly());
				form.setEarningsDaily(order.getEarningsDaily());
				form.setEarningsMonthly(order.getEarningsMonthly());
				form.setOrderDate(order.getOrderDate());
				form.setOrderMonth(order.getOrderMonth());
				orderFormList.add(form);
			}
			return orderFormList;
		}
		return new ArrayList<OrderForm>();
	}

	public ArrayList<ItemForm> getItemListByStock(int stockNum){

		List<Item> itemEntityList = itemDao.selectItemList(null, null, null, 0L, 9999999999L,stockNum, 0L,100);

		if(itemEntityList != null && itemEntityList.size() > 0) {

			ArrayList<ItemForm> itemFormList = new ArrayList<ItemForm>();


			for(Item item : itemEntityList) {

				String wholesalePrice = (item.getWholesalePrice() == null) ? "0" : item.getWholesalePrice().toString();

				ItemForm form = new ItemForm();
				form.setCount(item.getCount());
				form.setItemCd(item.getItemCd());
				form.setItemName1(item.getItemName1());
				form.setItemName2(item.getItemName2());
				form.setItemImage1(item.getItemImage1());
				form.setBrandName(item.getBrandName());
				form.setWholesalePrice(wholesalePrice);
				form.setStockNum(item.getStockNum());
				form.setStockStatus(item.getStockStatus());
				form.setStatusCd(item.getStatusCd());
				form.setStatusName(item.getStatusName());
				itemFormList.add(form);
			}
			return itemFormList;
		}
		return new ArrayList<ItemForm>();
	}

	// getItemListのoverload。pagerの受け渡しを必要としない場合の商品取得に使用
	public ArrayList<ItemForm> getItemList(String itemCategory1Cd, String itemCategory2Cd, String keyword,int stockNum){

		List<Item> itemEntityList = itemDao.selectItemList(itemCategory1Cd, itemCategory2Cd, keyword, 0L, 9999999999L,stockNum, 0L,999999);

		if(itemEntityList != null && itemEntityList.size() > 0) {

			ArrayList<ItemForm> itemFormList = new ArrayList<ItemForm>();


			for(Item item : itemEntityList) {

				String wholesalePrice = (item.getWholesalePrice() == null) ? "0" : item.getWholesalePrice().toString();

				ItemForm form = new ItemForm();
				form.setCount(item.getCount());
				form.setItemCd(item.getItemCd());
				form.setItemName1(item.getItemName1());
				form.setItemName2(item.getItemName2());
				form.setItemImage1(item.getItemImage1());
				form.setBrandName(item.getBrandName());
				form.setWholesalePrice(wholesalePrice);
				form.setStockNum(item.getStockNum());
				form.setStockStatus(item.getStockStatus());
				form.setStatusCd(item.getStatusCd());
				form.setStatusName(item.getStatusName());
				itemFormList.add(form);
			}
			return itemFormList;
		}
		return new ArrayList<ItemForm>();
	}

}