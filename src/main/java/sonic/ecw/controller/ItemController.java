package sonic.ecw.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sonic.ecw.form.ItemCategoryForm;
import sonic.ecw.form.ItemForm;
import sonic.ecw.service.ItemCategoryService;
import sonic.ecw.service.ItemService;

/**
 * 商品Controllerクラス
 *
 * <ul>
 * <li>商品系画面の表示を要求するHTTPリクエストの受け口となるクラス。</li>
 * <li>MVCモデルのC(Controller)に相当します。</li>
 * </ul>
 */

// 課題#09 商品検索実行（商品一覧）に、価格帯下限と価格帯上限の引数を追加します。
@Controller
@RequestMapping("/")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemCategoryService itemCategoryService;

	/**
	 * 商品検索実行（商品一覧）
	 *
	 * @param itemCategory1Cd 商品分類1コード
	 * @param itemCategory2Cd 商品分類2コード
	 * @param keyword キーワード
	 * @param pageable ページャオブジェクト
	 * @param model View（Thymeleaf※htmlファイル）に渡すオブジェクト
	 * @return htmlファイル名（/ecw/src/main/resources/templates/ 配下のhtmlファイル名の、拡張子なし表現）
	 */

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String search(
				  @RequestParam(name="c1", required = false) String itemCategory1Cd
				, @RequestParam(name="c2", required = false) String itemCategory2Cd
				, @RequestParam(name="k", required = false) String keyword
				, @RequestParam(name="min", required = false) String min
				, @RequestParam(name="max", required = false) String max
				, Pageable pageable
				, Model model)  {

		// 商品分類リスト取得
		@SuppressWarnings("unchecked")
		ArrayList<ItemCategoryForm> itemCategoryList = (ArrayList<ItemCategoryForm>) session.getAttribute("itemCategoryList");
		// セッションに商品分類リストが存在しなければDBから取得
		if(itemCategoryList == null) {
			itemCategoryList =  (ArrayList<ItemCategoryForm>) itemCategoryService.getItemCategoryList();
			// セッションに商品分類リストをセット
			session.setAttribute("itemCategoryList", itemCategoryList);
		}

		// 選択中の商品分類1情報取得
		ItemCategoryForm currentItemCategory1 = itemCategoryService.getCurrentItemCategory1(itemCategory1Cd);

		// 選択中の商品分類2情報取得
		ItemCategoryForm currentItemCategory2 = itemCategoryService.getCurrentItemCategory2(itemCategory1Cd, itemCategory2Cd);;

		// 商品リスト取得(ここから呼び出すときは在庫数指定は必要ないので常に99999以下で取得)
		PageImpl<ItemForm> itemList =  itemService.getItemList(itemCategory1Cd, itemCategory2Cd, keyword, min, max,99999, pageable);

		//表示するバナーをランダムにするため
		Random rand = new Random();
		int rd = rand.nextInt(10);

		model.addAttribute("itemCategoryList", itemCategoryList);
		model.addAttribute("currentItemCategory1", currentItemCategory1);
		model.addAttribute("currentItemCategory2", currentItemCategory2);
		//itemForm型のデータが入ったリストの入ったPageImpl
		model.addAttribute("itemList", itemList);
		//ページャで遷移する時に利用する値を保持するためにデータをセットする
		model.addAttribute("c1", itemCategory1Cd);
		model.addAttribute("c2", itemCategory2Cd);
		model.addAttribute("k", keyword);
		model.addAttribute("min",min);
		model.addAttribute("max",max);
		model.addAttribute("common_ss", super.getCommonSystemSetting());
		model.addAttribute("common_msg", super.getCommonMessage());
		model.addAttribute("rd", rd);

		return "itemlist";
	}

	// 課題#10 商品詳細画面を追加する

	@RequestMapping(value="/detail")
	public String detail(@RequestParam("item_cd") String item_cd, Model model)  {

		// 商品分類リスト取得
		@SuppressWarnings("unchecked")
		ArrayList<ItemCategoryForm> itemCategoryList = (ArrayList<ItemCategoryForm>) session.getAttribute("itemCategoryList");

		if(itemCategoryList == null) {
			itemCategoryList =  (ArrayList<ItemCategoryForm>) itemCategoryService.getItemCategoryList();

			session.setAttribute("itemCategoryList", itemCategoryList);
		}

		// 商品詳細情報取得
		ItemForm itemdetail =  itemService.getItemDetailInfo(item_cd);

		// 上で取得したカテゴリコードを使用して同じカテゴリのお勧め商品のリストを取得
		String category1Cd = itemdetail.getItemCategory1Cd();
		ArrayList<ItemForm> recommendList = itemService.getRecommendationList(item_cd,category1Cd);

		//表示するバナーをランダムにするため
		Random rand = new Random();
		int rd = rand.nextInt(10);

		model.addAttribute("itemCategoryList", itemCategoryList);
		model.addAttribute("itemdetail",itemdetail);
		model.addAttribute("recommendList",recommendList);
		model.addAttribute("common_msg", super.getCommonMessage());
		model.addAttribute("rd", rd);

		return "itemdetail";
	}


}