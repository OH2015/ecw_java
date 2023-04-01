package sonic.ecw.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Getter;
import lombok.Setter;

/**
 * システム設定Entityクラス
 *
 * <ul>
 * <li>システム設定マスタの結果セットのレコードを格納するクラス。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class SystemSetting {

	/**
	 * システム設定ID
	 */
	@Id
	@Column
	@Getter
	@Setter
	private Integer ssId;

	/**
	 * システム設定コード
	 */
	@Column
	@Getter
	@Setter
	private String ssCd;

	/**
	 * システム設定名
	 */
	@Column
	@Getter
	@Setter
	private String ssName;

	/**
	 * システム設定値
	 */
	@Column
	@Getter
	@Setter
	private String ssVal;

}