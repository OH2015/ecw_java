package sonic.ecw.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * メッセージFormクラス
 *
 * <ul>
 * <li>Model-Controller間でメッセージデータを媒介するためのDTOクラス。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
public class MessageForm implements Serializable {

	private static final long serialVersionUID = -604420400613558657L;

	/**
	 * メッセージコード
	 */
	@Getter
	@Setter
	private String msgCd;

	/**
	 * 文言
	 */
	@Getter
	@Setter
	private String msgVal;

}