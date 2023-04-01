package sonic.ecw.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * システム設定Formクラス
 *
 * <ul>
 * <li>Model-Controller間でシステム設定データを媒介するためのDTOクラス。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
public class SystemSettingForm implements Serializable {

	private static final long serialVersionUID = -5150437046771746233L;

	/**
	 * システム設定ID
	 */
	@Getter
	@Setter
	private Integer ssId;

	/**
	 * システム設定コード
	 */
	@Getter
	@Setter
	private String ssCd;

	/**
	 * システム設定名
	 */
	@Getter
	@Setter
	private String ssName;

	/**
	 * システム設定値
	 */
	@Getter
	@Setter
	private String ssVal;

}