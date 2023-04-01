package sonic.ecw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sonic.ecw.dao.MessageDao;
import sonic.ecw.dao.SystemSettingDao;
import sonic.ecw.entity.Message;
import sonic.ecw.entity.SystemSetting;
import sonic.ecw.form.MessageForm;
import sonic.ecw.form.SystemSettingForm;

/**
 * 共通Serviceクラス
 *
 * <ul>
 * <li>共通のビジネスロジックを記述するクラス</li>
 * </ul>
 */
@Service
public class CommonService {

	@Autowired
	private SystemSettingDao systemSettingDao;

	@Autowired
	private MessageDao messageDao;

	/**
	 * システム設定リスト取得
	 * @return システム設定リスト
	 */
	public List<SystemSettingForm> getSystemSettingList() {

		List<SystemSetting> systemSettingEntityList = systemSettingDao.selectSystemSettingList();

		ArrayList<SystemSettingForm> systemSettingFormList = new ArrayList<SystemSettingForm>();

		for(SystemSetting systemSetting : systemSettingEntityList) {
			SystemSettingForm form = new SystemSettingForm();
			form.setSsCd(systemSetting.getSsCd());
			form.setSsVal(systemSetting.getSsVal());
			systemSettingFormList.add(form);
		}

		return systemSettingFormList;
	}

	/**
	 * メッセージリスト取得
	 * @return メッセージリスト
	 */
	public List<MessageForm> getMessageList() {

		List<Message> systemMessageEntityList = messageDao.selectMessageList();

		ArrayList<MessageForm> systemMessageFormList = new ArrayList<MessageForm>();

		for(Message message : systemMessageEntityList) {
			MessageForm form = new MessageForm();
			form.setMsgCd(message.getMsgCd());
			form.setMsgVal(message.getMsgVal());
			systemMessageFormList.add(form);
		}

		return systemMessageFormList;
	}


}