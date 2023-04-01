package sonic.ecw.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sonic.ecw.form.ItemForm;
import sonic.ecw.form.OrderForm;
import sonic.ecw.service.ItemCategoryService;
import sonic.ecw.service.ItemService;

/**
 * Top画面のControllerクラス
 *
 * <ul>
 * <li>Top画面の表示を要求するHTTPリクエストの受け口となるクラス。</li>
 * <li>MVCモデルのC(Controller)に相当します。</li>
 * </ul>
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@Autowired
	private ItemCategoryService itemCategoryService;

	@Autowired
	private ItemService itemService;


	/**
	 * TOP画面表示
	 *
	 * @param model View（Thymeleaf※htmlファイル）に渡すオブジェクト
	 * @return htmlファイル名（/ecw/src/main/resources/templates/ 配下のhtmlファイル名の、拡張子なし表現）
	 */
	@RequestMapping("top")
	public String top(Model model) {
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");
		ArrayList<ItemForm> noStockItemList =  (ArrayList<ItemForm>) itemService.getItemListByStock(0);
		ArrayList<ItemForm> itemList =  (ArrayList<ItemForm>) itemService.getItemList(null,null,null,9999999);

		if(orderList == null) {
			orderList =  itemService.getOrderList();

			session.setAttribute("orderList", orderList);
		}

		// 今日の売上
		String earningsToday = orderList.get(orderList.size()-1).getEarningsDaily();
		earningsToday = earningsToday.substring(0,earningsToday.indexOf("."));
		// 昨日の売上
		String earningsYesterday = orderList.get(orderList.size()-2).getEarningsDaily();
		earningsYesterday = earningsYesterday.substring(0,earningsYesterday.indexOf("."));
		// 今月の売上
		String earningsThisMonth = orderList.get(orderList.size()-1).getEarningsMonthly();
		earningsThisMonth = earningsThisMonth.substring(0,earningsThisMonth.indexOf("."));

		model.addAttribute("orderList",orderList);
		model.addAttribute("earningsToday",earningsToday);
		model.addAttribute("earningsThisMonth",earningsThisMonth);
		model.addAttribute("earningsYesterday", earningsYesterday);
		model.addAttribute("noStock",noStockItemList.get(0).getCount());
		model.addAttribute("itemTotalNum",itemList.get(0).getCount());

		return "admin/topadmin";
	}


	@RequestMapping("detail")
	public String detail(
			@RequestParam("item_cd") String item_cd
			,Model model) {

		 ItemForm itemdetail = itemService.getItemDetailInfo(item_cd);

		 model.addAttribute("itemdetail", itemdetail);

		return "admin/itemdetail_admin";
	}


	@RequestMapping("itemlist")
	public String itemlist(Model model) {
		ArrayList<ItemForm> itemList =  (ArrayList<ItemForm>) itemService.getItemList(null,null,null,9999999);
		model.addAttribute("itemList",itemList);


		return "admin/itemlist_admin";
	}

	@RequestMapping("registration")
	public String registration(Model model) {

		return "admin/itemregistration";
	}

	@RequestMapping("basicsetting")
	public String basicsetting(Model model) {

		return "admin/basicsetting";
	}



}