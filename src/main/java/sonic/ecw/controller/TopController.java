package sonic.ecw.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sonic.ecw.form.ItemCategoryForm;
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
@RequestMapping("/")
public class TopController extends BaseController {

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
	@RequestMapping("/")
	public String top(Model model) {

		// 商品分類リスト取得
		ArrayList<ItemCategoryForm>	itemCategoryList =  (ArrayList<ItemCategoryForm>) itemCategoryService.getItemCategoryList();

		//表示するバナーをランダムにするため
		Random rand = new Random();
		int rd = rand.nextInt(10);

		// セッションに商品分類リストをセット
		session.setAttribute("itemCategoryList", itemCategoryList);

		model.addAttribute("itemCategoryList", itemCategoryList);
		model.addAttribute("common_ss", super.getCommonSystemSetting());
		model.addAttribute("common_msg", super.getCommonMessage());
		model.addAttribute("rd", rd);

		return "top";
	}

	@RequestMapping("/admin")
	public String admin(@RequestParam(name="user", required = false) String user
						,@RequestParam(name="pass", required = false) String pass
						,Model model)
	{
		//正しく入力されていなければログイン画面に戻す
		if(user == null || user == "" || pass == null || pass == "") {
			return "loginadmin";

		}
		@SuppressWarnings("unchecked")
		ArrayList<OrderForm> orderList = (ArrayList<OrderForm>) session.getAttribute("orderList");

		if(orderList == null) {
		orderList =  (ArrayList<OrderForm>) itemService.getOrderList();

			session.setAttribute("orderList", orderList);
		}

		String earningsToday = orderList.get(orderList.size()-1).getEarningsDaily();
		earningsToday = earningsToday.substring(0,earningsToday.indexOf("."));

		String earningsYesterday = orderList.get(orderList.size()-2).getEarningsDaily();
		earningsYesterday = earningsYesterday.substring(0,earningsYesterday.indexOf("."));

		String earningsThisMonth = orderList.get(orderList.size()-1).getEarningsMonthly();
		earningsThisMonth = earningsThisMonth.substring(0,earningsThisMonth.indexOf("."));

		model.addAttribute("orderList",orderList);
		model.addAttribute("earningsToday",earningsToday);
		model.addAttribute("earningsThisMonth",earningsThisMonth);
		model.addAttribute("earningsYesterday", earningsYesterday);
		//正しく入力されていれば管理画面トップへ
		return "admin/topadmin";
	}

}