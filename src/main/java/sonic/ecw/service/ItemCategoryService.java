package sonic.ecw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sonic.ecw.dao.ItemCategoryDao;
import sonic.ecw.entity.ItemCategory;
import sonic.ecw.form.ItemCategoryForm;

/**
 * 商品分類Serviceクラス
 *
 * <ul>
 * <li>商品分類に関する処理本体（ビジネスロジック）を記述するクラス</li>
 * </ul>
 */
@Service
public class ItemCategoryService {

	@Autowired
	private ItemCategoryDao itemCategoryDao;

	/**
	 * 商品分類リスト取得
	 * @return 商品分類リスト
	 */
	public List<ItemCategoryForm> getItemCategoryList() {

		List<ItemCategory> itemCategoryEntityList = itemCategoryDao.selectItemCategoryList();

		ArrayList<ItemCategoryForm> itemCategoryFormList = new ArrayList<ItemCategoryForm>();

		for(ItemCategory itemCategory : itemCategoryEntityList) {
			ItemCategoryForm form = new ItemCategoryForm();
			form.setItemCategoryNum(itemCategory.getItemCategoryNum());
			form.setItemCategory1Cd(itemCategory.getItemCategory1Cd());
			form.setItemCategory1Name(itemCategory.getItemCategory1Name());
			form.setItemCategory2Cd(itemCategory.getItemCategory2Cd());
			form.setItemCategory2Name(itemCategory.getItemCategory2Name());
			itemCategoryFormList.add(form);
		}

		return itemCategoryFormList;
	}

	/**
	 * 現在商品分類1情報取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @return 現在商品分類1情報
	 */
	public ItemCategoryForm getCurrentItemCategory1(String itemCategory1Cd) {

		ItemCategory itemCategoryEntity = itemCategoryDao.selectCurrentItemCategory1(itemCategory1Cd);

		ItemCategoryForm itemCategoryForm = null;
		if(itemCategoryEntity != null) {
			itemCategoryForm = new ItemCategoryForm();
			itemCategoryForm.setItemCategory1Cd(itemCategoryEntity.getItemCategory1Cd());
			itemCategoryForm.setItemCategory1Name(itemCategoryEntity.getItemCategory1Name());
		}

		return itemCategoryForm;
	}

	/**
	 * 現在商品分類2情報取得
	 * @param itemCategory1Cd 商品分類1コード
	 * @param itemCategory2Cd 商品分類2コード
	 * @return 現在商品分類2情報
	 */
	public ItemCategoryForm getCurrentItemCategory2(String itemCategory1Cd, String itemCategory2Cd) {

		ItemCategory itemCategoryEntity = itemCategoryDao.selectCurrentItemCategory2(itemCategory1Cd, itemCategory2Cd);

		ItemCategoryForm itemCategoryForm = null;
		if(itemCategoryEntity != null) {
			itemCategoryForm = new ItemCategoryForm();
			itemCategoryForm.setItemCategory2Cd(itemCategoryEntity.getItemCategory2Cd());
			itemCategoryForm.setItemCategory2Name(itemCategoryEntity.getItemCategory2Name());
		}

		return itemCategoryForm;
	}

}