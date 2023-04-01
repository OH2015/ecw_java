package sonic.ecw.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sonic.ecw.form.MessageForm;
import sonic.ecw.form.SystemSettingForm;
import sonic.ecw.service.CommonService;

/**
 * 基底Controllerクラス
 *
 * <ul>
 * <li>コントローラの基底クラス。</li>
 * </ul>
 */
@Controller
public class BaseController {

	@Autowired
	protected HttpSession session;

	@Autowired
	private CommonService commonService;

	/**
	 * システム設定一括取得
	 * @return システム設定データ（HashMap）
	 */
	protected HashMap<String, String> getCommonSystemSetting(){

		// セッションからシステム設定情報を取得
		@SuppressWarnings("unchecked")
		HashMap<String, String> common_ss = (HashMap<String, String>) session.getAttribute("common_ss");
		// セッションにシステム設定情報が存在しなければDBから取得
		if(common_ss == null) {
			ArrayList<SystemSettingForm> systemSettingList =  (ArrayList<SystemSettingForm>) commonService.getSystemSettingList();

			// ArrayListで取ってきたシステム設定情報をHashMapに変換
			common_ss = new HashMap<String, String>();
			for(SystemSettingForm form : systemSettingList) {
				String ssCd = StringUtils.defaultString(form.getSsCd(), "");
				if(!"".equals(ssCd)) {
					common_ss.put(ssCd, form.getSsVal());
				}
			}
			// セッションにシステム設定情報を格納する
			session.setAttribute("common_ss", common_ss);
		}

		return common_ss;
	}

	/**
	 * メッセージ一括取得
	 * @return メッセージデータ（HashMap）
	 */
	protected HashMap<String, String> getCommonMessage(){

		// セッションからメッセージ情報を取得
		@SuppressWarnings("unchecked")
		HashMap<String, String> common_msg = (HashMap<String, String>) session.getAttribute("common_msg");
		// セッションにメッセージ情報が存在しなければDBから取得
		if(common_msg == null) {
			ArrayList<MessageForm> messageList =  (ArrayList<MessageForm>) commonService.getMessageList();

			// ArrayListで取ってきたシステム設定情報をHashMapに変換
			common_msg = new HashMap<String, String>();
			for(MessageForm form : messageList) {
				String msgCd = StringUtils.defaultString(form.getMsgCd(), "");
				if(!"".equals(msgCd)) {
					common_msg.put(msgCd, form.getMsgVal());
				}
			}
			// セッションにシステムメッセージ情報を格納する
			session.setAttribute("common_msg", common_msg);
		}

		return common_msg;
	}

}